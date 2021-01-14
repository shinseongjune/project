package action;

import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.IntroModifyProService;
import vo.ActionForward;
import vo.Intro;
import vo.Member;

public class IntroModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		boolean isModifySuccess = false;
		int intro_num = Integer.parseInt(request.getParameter("intro_num"));
		Intro article = new Intro();
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		String password = loginMember.getPassword();
		IntroModifyProService introModifyProService = new IntroModifyProService();
		boolean isRightUser = introModifyProService.isArticleWriter(intro_num, password);
		if(!isRightUser) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('수정할 권한이 없습니다.');history.back();</script>");
		} else {
			int fileSize = 10 * 1920 * 1080;
			String savePath = "intro/upload";
			ServletContext context = session.getServletContext();
			String uploadPath = context.getRealPath(savePath);
			MultipartRequest multi = new MultipartRequest(request, uploadPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			article = new Intro(); 
			article.setContents(multi.getParameter("contents"));
			article.setIntro_num(intro_num);
			Enumeration<?> images = multi.getFileNames();
			if(images.hasMoreElements())
			article.setImg6(multi.getFilesystemName((String)images.nextElement()));
			if(images.hasMoreElements())
			article.setImg5(multi.getFilesystemName((String)images.nextElement()));
			if(images.hasMoreElements())
			article.setImg4(multi.getFilesystemName((String)images.nextElement()));
			if(images.hasMoreElements())
			article.setImg3(multi.getFilesystemName((String)images.nextElement()));
			if(images.hasMoreElements())
			article.setImg2(multi.getFilesystemName((String)images.nextElement()));
			if(images.hasMoreElements())
			article.setImg1(multi.getFilesystemName((String)images.nextElement()));
			article.setImgex1(multi.getParameter("imgex1"));
			article.setImgex2(multi.getParameter("imgex2"));
			article.setImgex3(multi.getParameter("imgex3"));
			article.setImgex4(multi.getParameter("imgex4"));
			article.setImgex5(multi.getParameter("imgex5"));
			article.setImgex6(multi.getParameter("imgex6"));
			isModifySuccess = introModifyProService.modifyArticle(article);
			
			if(!isModifySuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('수정 실패');history.back();</script>");
			} else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("introDetail.do?intro_num=" + article.getIntro_num() + "&page=" + request.getParameter("page"));
			}
		}
		return forward;
	}

}

package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.LectureDetailModifyService;
import vo.ActionForward;
import vo.Lecture_Video;
import vo.Member;

public class LectureDetailModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		Lecture_Video vid = null;
		
		if(loginMember != null) {
			forward = new ActionForward();
			vid = new Lecture_Video();
			vid.setChapter(Integer.parseInt(request.getParameter("chapter")));
			vid.setLecture_num(Integer.parseInt(request.getParameter("lecture_num")));
			vid.setChapter_title(request.getParameter("chapter_title"));
			vid.setVideo(request.getParameter("video"));
			
			LectureDetailModifyService lectureDetailModifyService = new LectureDetailModifyService();
			int result = lectureDetailModifyService.modifyVid(vid);
			if (result > 0) {
				forward.setPath("lectureDetail.do?lecture_num=" + vid.getLecture_num());
				return forward;
			} else {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('수정 실패');history.back();</script>");
				return forward;
			}
			
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}

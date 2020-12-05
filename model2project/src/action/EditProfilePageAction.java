package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.EditProfilePageService;
import vo.ActionForward;
import vo.Member;

public class EditProfilePageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession(true);
		Member loginMember = (Member) session.getAttribute("id");
		String id = loginMember.getId();
		
		EditProfilePageService editProfilePageService = new EditProfilePageService();
		Member memData = editProfilePageService.getProfileData(id);
		
		if(memData != null) {
			
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("editprofile.jsp");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('실패');history.back();</script>");
		}
		return forward;
	}

}
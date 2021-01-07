package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.EditProfileService;
import vo.ActionForward;
import vo.Member;

public class EditProfileAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		if(loginMember != null) {
			int number = Integer.parseInt(request.getParameter("number"));
			String id = request.getParameter("id");
			String pw = request.getParameter("password");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String gender = request.getParameter("gender");
			String major = request.getParameter("major");
			String education = request.getParameter("education");
			
			EditProfileService editProfileService = new EditProfileService();
			int result = editProfileService.doEdit(id, pw, name, email, gender, major, education);
			
			if(result > 0) {
				loginMember.setNumber(number);
				loginMember.setId(id);
				loginMember.setPassword(pw);
				loginMember.setName(name);
				loginMember.setEmail(email);
				loginMember.setGender(gender);
				loginMember.setMajor(major);
				loginMember.setEducation(education);
				
				session.setAttribute("loginMember", loginMember);
				
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("index.do");
			} else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('실패');history.back();</script>");
			}
			return forward;
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}

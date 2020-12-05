package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.QuitService;
import vo.ActionForward;
import vo.Member;

public class QuitAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		Member loginMember = (Member)request.getAttribute("loginMember");
		String id = loginMember.getId();
		QuitService quitService = new QuitService();
		int result = quitService.deleteMember(id);
		
		if(result > 0) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("index.jsp");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('실패');location.replace('quit.jsp');</script>");
		}
		return forward;
	}

}

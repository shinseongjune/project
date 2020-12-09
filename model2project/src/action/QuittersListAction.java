package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.QuittersLastPageService;
import svc.QuittersListService;
import vo.ActionForward;
import vo.Member;

public class QuittersListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		int nowPage = 1;
		if(request.getParameter("page") != null) {
			nowPage = Integer.parseInt(request.getParameter("page"));
		}
		Member loginMember = (Member) session.getAttribute("loginMember");
		if(loginMember != null) {
			forward = new ActionForward();
			QuittersListService quittersListService = new QuittersListService();
			ArrayList<Member> memList = quittersListService.getQuittersList(nowPage);
			QuittersLastPageService quittersLastPageService = new QuittersLastPageService();
			int lastPage = quittersLastPageService.getQuittersLastPage();
			session.setAttribute("lastPage", lastPage);
			session.setAttribute("memList", memList);
			forward.setPath("quitters.jsp?page="+nowPage);
			return forward;
		} else {
			forward = new ActionForward();
			forward.setPath("login.jsp");
			return forward;
		}
	}

}

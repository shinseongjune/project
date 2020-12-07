package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ReviewViewService;
import vo.ActionForward;
import vo.Member;

public class ReviewViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		
		if(loginMember != null) {
			forward = new ActionForward();
			ReviewViewService reviewViewService = new ReviewViewService();
			ArrayList<Object> reviewViewList = reviewViewService.getReviewView(review_num);

			session.setAttribute("reviewViewList", reviewViewList);
			forward.setRedirect(true);
			forward.setPath("reviewView.jsp?page=" + request.getParameter("page"));
			return forward;
		} else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("login.jsp");
			return forward;
		}
	}

}

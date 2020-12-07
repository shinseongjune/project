package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.ActionForward;
import vo.Lecture;
import vo.Member;
import vo.Review;

public class ReviewWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		int result = 0;
		
		if(loginMember != null) {
			forward = new ActionForward();
			Lecture lec = new Lecture();
			Member mem = new Member();
			Review re = new Review();
			
			mem.setName(request.getParameter("name"));
			re.setTitle(request.getParameter("title"));
			re.setContents(request.getParameter("contents"));
			lec.set---------------- 렉쳐 넘버 가져올 수 있으면 re에 넣고 아니면 렉쳐에 넣기----------------
			
			ReviewWriteService reviewWriteService = new ReviewWriteService();
			result = reviewWriteService.writeReview(mem, re, lec);
			
			if (result > 0) {
				forward.setRedirect(true);
				forward.setPath("review.do?page=1");
				return forward;
			} else {
				forward.setRedirect(true);
				forward.setPath("index.do");
				return forward;
			}
			
		} else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("login.jsp");
			return forward;
		}
	}

}

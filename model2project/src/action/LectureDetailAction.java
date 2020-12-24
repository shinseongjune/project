package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.IsFreeService;
import svc.LectureDetailListService;
import svc.PaidCheckService;
import vo.ActionForward;
import vo.Lecture_Video;
import vo.Member;

public class LectureDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		if(loginMember != null) {
			forward = new ActionForward();
			String id = loginMember.getId();
			int lecture_num = Integer.parseInt(request.getParameter("lecture_num"));
			
			IsFreeService isFreeService = new IsFreeService();
			boolean free = isFreeService.isFree(lecture_num);
			if(free) {
				LectureDetailListService lectureDetailListService = new LectureDetailListService();
				LinkedList<Lecture_Video> vidList = lectureDetailListService.getVid(lecture_num);
				session.setAttribute("vidList", vidList);
				forward.setPath("lecture_Detail.jsp?lecture_num=" + lecture_num);
				return forward;
			} else {
				PaidCheckService paidCheckService = new PaidCheckService();
				boolean paid = paidCheckService.paidCheck(id, lecture_num);
				if(paid) {
					LectureDetailListService lectureDetailListService = new LectureDetailListService();
					LinkedList<Lecture_Video> vidList = lectureDetailListService.getVid(lecture_num);
					session.setAttribute("vidList", vidList);
					forward.setPath("lecture_Detail.jsp?lecture_num=" + lecture_num);
					return forward;
				} else {
					forward.setPath("payPage.do?lecture_num=" + lecture_num);
					return forward;
				}
			}
			
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}

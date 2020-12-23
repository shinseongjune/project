package action;

import java.util.LinkedList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.LoginService;
import svc.MainBannerService;
import svc.MainFreeService;
import svc.MainLectureService;
import svc.MainNoticeService;
import vo.ActionForward;
import vo.Banner;
import vo.Free;
import vo.Lecture_Video;
import vo.Member;
import vo.Notice;

public class MainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		LinkedList<Notice> notList = null;
		LinkedList<Free> freeList = null;
		LinkedList<Banner> banList = null;
		LinkedList<Lecture_Video> lecList = null;
		
		String id = "";
		Member loginMember = null;
		
		Cookie[] cookieArray = request.getCookies();
		if (cookieArray != null) {
			for (int i = 0; i < cookieArray.length; i++) {
				if(cookieArray[i].getName().equals("id")) {
					id = cookieArray[i].getValue();
				}
			}
			LoginService loginService = new LoginService();
			loginMember = loginService.getAutoLogin(id);
			session.setAttribute("loginMember", loginMember);
		}
		
		MainNoticeService mainNoticeService = new MainNoticeService();
		notList = mainNoticeService.getMainNotice();
		session.setAttribute("notList", notList);
		
		MainFreeService mainFreeService = new MainFreeService();
		freeList = mainFreeService.getMainFree();
		session.setAttribute("freeList", freeList);
		
		MainBannerService mainBannerService = new MainBannerService();
		banList = mainBannerService.getBanner();
		session.setAttribute("banList", banList);
		
		MainLectureService mainLectureService = new MainLectureService();
		lecList = mainLectureService.getLectureThumbnail();
		session.setAttribute("lecList", lecList);
		
		forward = new ActionForward();
		forward.setPath("main.jsp");
		
		return forward;
		
	}

}

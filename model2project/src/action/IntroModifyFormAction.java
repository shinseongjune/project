package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.IntroDetailService;
import vo.ActionForward;
import vo.Intro;

public class IntroModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		int intro_num = Integer.parseInt(request.getParameter("intro_num"));
		IntroDetailService introDetailService = new IntroDetailService();
		Intro article = introDetailService.getArticle(intro_num);
		request.setAttribute("article", article);
		forward.setPath("/intro/intro_modify.jsp");
		
		return forward;
	}

}

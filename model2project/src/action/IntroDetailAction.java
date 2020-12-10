package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.IntroDetailService;
import vo.ActionForward;
import vo.Intro;

public class IntroDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int intro_num = Integer.parseInt(request.getParameter("intro_num"));
		String page = request.getParameter("page");
		IntroDetailService introDetailService = new IntroDetailService();
		Intro article = introDetailService.getArticle(intro_num);
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
		request.setAttribute("article", article);
		forward.setPath("/intro/intro_view.jsp");
		return forward;
	}

}

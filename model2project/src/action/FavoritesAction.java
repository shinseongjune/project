package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.FavoritesListService;
import vo.ActionForward;
import vo.Favorites;

public class FavoritesAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		ActionForward forward = new ActionForward();
		FavoritesListService favoritesListService = new FavoritesListService();
		ArrayList[] favorList = favoritesListService.getFavoritesList(id);
		HttpSession session = request.getSession();
		session.setAttribute("favoritesListr", favorList);
		forward.setPath("./favorites.jsp");
		return forward;
	}

}

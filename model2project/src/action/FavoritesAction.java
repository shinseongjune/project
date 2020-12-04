package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.FavoritesListService;
import vo.ActionForward;
import vo.Favorites;

public class FavoritesAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		ActionForward forward = new ActionForward();
		FavoritesListService favoritesListService = new FavoritesListService();
		ArrayList<Favorites> favoritesList = favoritesListService.getFavoritesList(id);
		request.setAttribute("favoritesList", favoritesList);
		forward.setPath("./favorites.jsp");
		return forward;
	}

}

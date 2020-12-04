package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.FavoritesDAO;
import vo.Favorites;

public class FavoritesListService {

	public ArrayList<Favorites> getFavoritesList(String id) {
		Connection conn = getConnection();
		FavoritesDAO favoritesDAO = FavoritesDAO.getInstance();
		favoritesDAO.setConnection(conn);
		ArrayList<Favorites> favoritesList = favoritesDAO.selectFavoritesList(id);
		close(conn);
		
		return favoritesList;
	}
}
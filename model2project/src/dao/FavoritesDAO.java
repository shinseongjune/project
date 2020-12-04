package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.Favorites;
import vo.Member;

public class FavoritesDAO {
	private static FavoritesDAO favoritesDAO;
	private Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	
	private FavoritesDAO() {
		
	}
	
	public static FavoritesDAO getInstance() {
		if(favoritesDAO == null) {
			favoritesDAO = new FavoritesDAO();
		}
		return favoritesDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public ArrayList<Favorites> selectFavoritesList(String id) {
		String sql = "SELECT lecture_num FROM favorites WHERE number = (SELECT number FROM member WHERE id = ?)";
		ArrayList<Favorites> favoritesList = new ArrayList<Favorites>();
		Favorites favor = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					favor = new Favorites();
					favor.setLecture_num(rs.getInt("lecture_num"));
					favoritesList.add(favor);
				} while(rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return favoritesList;
	}
}

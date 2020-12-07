package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.Favorites;
import vo.Lecture;
import vo.Member;

public class FavoritesDAO {
	private static FavoritesDAO favoritesDAO;
	private Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
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

	public ArrayList[] selectFavoritesList(String id) {
		String sql = "SELECT l.lecture_title, l.lecture_num, m.name FROM lecture l JOIN member m ON l.number = m.number WHERE l.lecture_num IN (SELECT lecture_num FROM favorites WHERE NUMBER = (SELECT NUMBER FROM member WHERE id = ?))";
		ArrayList<Lecture> lecList = new ArrayList<Lecture>();
		ArrayList<Member> memList = new ArrayList<Member>(); 
		ArrayList[] favorList = null;
		Lecture lec = null;
		Member mem = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					lec = new Lecture();
					mem = new Member();
					lec.setLecture_title(rs.getString("lecture_title"));
					lec.setLecture_num(rs.getInt("lecture_num"));
					mem.setName(rs.getString("name"));
					lecList.add(lec);
					memList.add(mem);
				} while(rs.next());
			}
			favorList = new ArrayList[] {lecList, memList};
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return favorList;
	}

	public int deleteFavorites(String id, String lecture_num) {
		int result = 0;
		String sql = "DELETE FROM favorites WHERE number = (SELECT number FROM member WHERE id = ?) AND lecture_num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, lecture_num);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
}

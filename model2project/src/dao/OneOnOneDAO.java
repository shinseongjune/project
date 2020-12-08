package dao;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Lecture;
import vo.Member;
import vo.One_On_One;

public class OneOnOneDAO {
	private static OneOnOneDAO oneOnOneDAO;
	private Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	private OneOnOneDAO() {
		
	}
	
	public static OneOnOneDAO getInstance() {
		if(oneOnOneDAO == null) {
			oneOnOneDAO = new OneOnOneDAO();
		}
		return oneOnOneDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public ArrayList<One_On_One> selectOneOnOneList(String id) {
		String sql = "SELECT title, contents, answer FROM one_on_one WHERE number = (SELECT number FROM member WHERE id = ?)";
		ArrayList<One_On_One> oneList = new ArrayList<One_On_One>();
		One_On_One one = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					one = new One_On_One();
					one.setTitle(rs.getString("title"));
					one.setContents(rs.getString("contents"));
					if(rs.getString("answer") != null) {
						one.setAnswer(rs.getString("answer"));
					}
					oneList.add(one);
				} while(rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return oneList;
	}

	public int sendOneOnOne(String id, One_On_One one) {
		String sql = "INSERT INTO one_on_one VALUES ((SELECT number FROM member WHERE id = ?), ?, ?, NULL, NULL)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, one.getTitle());
			pstmt.setString(3, one.getContents());
			result = pstmt.executeUpdate();
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}
}

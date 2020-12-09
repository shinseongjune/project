package dao;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Lecture;
import vo.Member;
import vo.Subject;

public class CategoryDAO {
	private static CategoryDAO categoryDAO;
	private Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	private CategoryDAO() {
		
	}
	
	public static CategoryDAO getInstance() {
		if(categoryDAO == null) {
			categoryDAO = new CategoryDAO();
		}
		return categoryDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public ArrayList<Subject> selectSubjectList() {
		String sql = "SELECT * FROM subject";
		ArrayList<Subject> subList = new ArrayList<Subject>(); 
		Subject sub = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					sub = new Subject();
					sub.setCode(rs.getInt("code"));
					sub.setSubject_name(rs.getString("subject_name"));
					subList.add(sub);
				} while(rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return subList;
	}

	public int deleteSubject(int code) {
		String sql = "DELETE FROM subject WHERE code = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);
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

	public int insertSubject(Subject sub) {
		String sql = "INSERT INTO subject VALUES (NULL, ?)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sub.getSubject_name());
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

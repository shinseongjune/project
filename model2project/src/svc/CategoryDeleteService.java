package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.CategoryDAO;

public class CategoryDeleteService {

	public int deleteSubject(int code) {
		int result = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			CategoryDAO categoryDAO = CategoryDAO.getInstance();
			categoryDAO.setConnection(conn);
			result = categoryDAO.deleteSubject(code);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					close(conn);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}

}

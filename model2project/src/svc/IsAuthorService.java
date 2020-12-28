package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.LectureDAO;

public class IsAuthorService {

	public boolean authorCheck(String id, int lecture_num) {
		Connection conn = getConnection();
		LectureDAO lectureDAO = LectureDAO.getInstance();
		lectureDAO.setConnection(conn);
		boolean free = lectureDAO.isAuthor(id, lecture_num);
		close(conn);
		
		return free;
	}

}

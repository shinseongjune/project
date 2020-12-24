package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.LectureDAO;

public class IsFreeService {

	public boolean isFree(int lecture_num) {
		Connection conn = getConnection();
		LectureDAO lectureDAO = LectureDAO.getInstance();
		lectureDAO.setConnection(conn);
		boolean free = lectureDAO.isFree(lecture_num);
		close(conn);
		
		return free;
	}

}

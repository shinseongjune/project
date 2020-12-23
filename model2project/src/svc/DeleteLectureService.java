package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.LectureDAO;

public class DeleteLectureService {

	public int deleteLecture(int lecture_num) {
		Connection conn = getConnection();
		LectureDAO lectureDAO = LectureDAO.getInstance();
		lectureDAO.setConnection(conn);
		int result = lectureDAO.deleteLecture(lecture_num);
		close(conn);
		
		return result;
	}

}

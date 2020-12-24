package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.LectureDAO;

public class PayPageService {

	public LinkedList<Object> getLecList(int lecture_num) {
		Connection conn = getConnection();
		LectureDAO lectureDAO = LectureDAO.getInstance();
		lectureDAO.setConnection(conn);
		LinkedList<Object> lectureList = lectureDAO.lecture4Pay(lecture_num);
		close(conn);
		
		return lectureList;
	}

}

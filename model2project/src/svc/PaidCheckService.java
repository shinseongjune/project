package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.LectureDAO;

public class PaidCheckService {

	public boolean paidCheck(String id, int lecture_num) {
		Connection conn = getConnection();
		LectureDAO lectureDAO = LectureDAO.getInstance();
		lectureDAO.setConnection(conn);
		boolean paid = lectureDAO.paidCheck(id, lecture_num);
		close(conn);
		
		return paid;
	}

}

package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.LectureDAO;
import vo.Lecture_Video;

public class LectureDetailListService {

	public LinkedList<Lecture_Video> getVid(int lecture_num) {
		LinkedList<Lecture_Video> vidList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			LectureDAO lectureDAO = LectureDAO.getInstance();
			lectureDAO.setConnection(conn);
			vidList = lectureDAO.getVid(lecture_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return vidList;
	}
}


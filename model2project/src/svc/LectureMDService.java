package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.LectureDAO;
import vo.Lecture;

public class LectureMDService {

	public Lecture getLecForMD(int lecture_num) {
		Connection conn = getConnection();
		LectureDAO lectureDAO = LectureDAO.getInstance();
		lectureDAO.setConnection(conn);
		Lecture lec = lectureDAO.getLecForMD(lecture_num);
		close(conn);
		
		return lec;
	}

}

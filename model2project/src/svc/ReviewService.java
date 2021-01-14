package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.ReviewDAO;

public class ReviewService {

	public LinkedList[] getReviewList(int nowPage) {
		LinkedList[] reviewList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			ReviewDAO reviewDAO = ReviewDAO.getInstance();
			reviewDAO.setConnection(conn);
			reviewList = reviewDAO.selectReviewList(nowPage);
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
		
		return reviewList;
	}

}

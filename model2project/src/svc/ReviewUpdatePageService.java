package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.ReviewDAO;
import vo.Review;

public class ReviewUpdatePageService {

	public Review updateReview(int review_num) {
		Review re = null;
		Connection conn = null;
		try {
			conn = getConnection();
			ReviewDAO reviewDAO = ReviewDAO.getInstance();
			reviewDAO.setConnection(conn);
			re = reviewDAO.updateReviewPage(review_num);
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
		
		return re;
	}

}

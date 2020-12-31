package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.ReviewDAO;
import vo.Review_Comment;

public class ReviewCommentService {

	public int reviewCommentExtra(Review_Comment rc, int number, int parent, int review_num) {
		Connection conn = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(conn);
		int result = reviewDAO.exComment(rc, number, parent, review_num);
		close(conn);
		
		return result;
	}

	public int reviewCommentDelete(int review_num, int comment_num) {
		Connection conn = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(conn);
		int result = reviewDAO.delComment(review_num, comment_num);
		close(conn);
		
		return result;
	}

	public int reviewCommentWrite(int review_num, int number, String contents) {
		Connection conn = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(conn);
		int result = reviewDAO.writeComment(review_num, number, contents);
		close(conn);
		
		return result;
	}

}

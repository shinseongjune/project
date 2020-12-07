package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Lecture;
import vo.Member;
import vo.Review;

public class ReviewDAO {
	private static ReviewDAO reviewDAO;
	private Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	int pageCount = 5;
	int range = 5;
	
	private ReviewDAO() {
		
	}
	
	public int getReviewNumber() {
		String sql = "SELECT count(*) AS c FROM review";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("c");
				if (result / 5 != 0) {
					result = (result / 5) + 1;
				} else {
					result = result / 5;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			close(pstmt);
		}
		return result;
	}

	public static ReviewDAO getInstance() {
		if(reviewDAO == null) {
			reviewDAO = new ReviewDAO();
		}
		return reviewDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public ArrayList[] selectReviewList(int nowPageNumber) {
		String sql = "SELECT r.title, r.contents, r.review_num, l.lecture_title, l.lecture_num, m.name FROM review AS r JOIN member AS m on r.number = m.number JOIN lecture AS l ON r.lecture_num = l.lecture_num ORDER BY r.review_num DESC LIMIT ?, " + pageCount;
		ArrayList<Lecture> lecList = new ArrayList<Lecture>();
		ArrayList<Member> memList = new ArrayList<Member>();
		ArrayList<Review> reviewContentList = new ArrayList<Review>();
		ArrayList[] reviewList = null;
		Lecture lec = null;
		Member mem = null;
		Review re = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (nowPageNumber - 1) * pageCount);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					lec = new Lecture();
					mem = new Member();
					re = new Review();
					lec.setLecture_title(rs.getString("lecture_title"));
					lec.setLecture_num(rs.getInt("lecture_num"));
					mem.setName(rs.getString("name"));
					re.setTitle(rs.getString("title"));
					re.setContents(rs.getString("contents"));
					re.setReview_num(rs.getInt("review_num"));
					lecList.add(lec);
					memList.add(mem);
					reviewContentList.add(re);
				} while(rs.next());
			}
			reviewList = new ArrayList[] {lecList, memList, reviewContentList};
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return reviewList;
	}

	public ArrayList<Object> selectReviewView(int review_num) {
		String sql = "SELECT r.review_num, r.title, r.contents, m.name FROM review AS r JOIN member AS m ON r.number = m.number WHERE r.review_num = ?";
		ArrayList<Object> reviewViewList = null;
		Member mem = null;
		Review re = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, review_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mem = new Member();
				re = new Review();
				mem.setName(rs.getString("name"));
				re.setReview_num(rs.getInt("review_num"));
				re.setTitle(rs.getString("title"));
				re.setContents(rs.getString("contents"));
			}
			reviewViewList = new ArrayList<Object>();
			reviewViewList.add(re);
			reviewViewList.add(mem);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return reviewViewList;
	}

	public int deleteReview(int review_num) {
		String sql = "DELETE FROM review WHERE review_num = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, review_num);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
}
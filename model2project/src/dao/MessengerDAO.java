package dao;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Member;
import vo.Message;
import vo.Review;

public class MessengerDAO {
	private static MessengerDAO messengerDAO;
	private Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	int pageCount = 5;
	int range = 5;
	
	private MessengerDAO() {
		
	}
	
	public int getMessageNumber(String id) {
		String sql = "SELECT count(*) AS c FROM message where receiver = (SELECT number FROM member WHERE id = ?)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("c");
				if (result % 5 != 0) {
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

	public static MessengerDAO getInstance() {
		if(messengerDAO == null) {
			messengerDAO = new MessengerDAO();
		}
		return messengerDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public ArrayList[] selectMessageList(int nowPageNumber) {
		String sql = "SELECT member.name, message.title, message.contents, message.time from member join message on member.number = message.sender and message.receiver = 2 ORDER BY time DESC LIMIT ?, " + pageCount;
		
		ArrayList<Member> memList = new ArrayList<Member>();
		ArrayList<Message> mesList = new ArrayList<Message>();
		ArrayList[] messageList = null;
		Member mem = null;
		Message mes = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (nowPageNumber - 1) * pageCount);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					mem = new Member();
					mes = new Message();

					mem.setName(rs.getString("name"));
					mes.setTitle(rs.getString("title"));
					mes.setContents(rs.getString("contents"));
					mes.setTime(rs.getString("time"));
					mesList.add(mes);
					memList.add(mem);
				} while(rs.next());
			}
			messageList = new ArrayList[] {mesList, memList};
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return messageList;
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
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public Review updateReviewPage(int review_num) {
		String sql = "SELECT * FROM review WHERE review_num = ?";
		Review re = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, review_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				re = new Review();
				re.setTitle(rs.getString("title"));
				re.setContents(rs.getString("contents"));
				re.setReview_num(rs.getInt("review_num"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return re;
	}

	public int updateReview(Review re) {
		String sql = "UPDATE review SET title = ?, contents = ? WHERE review_num = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, re.getTitle());
			pstmt.setString(2, re.getContents());
			pstmt.setInt(3, re.getReview_num());
			result = pstmt.executeUpdate();
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}
}

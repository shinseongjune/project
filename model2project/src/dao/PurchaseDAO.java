package dao;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import vo.Lecture;
import vo.Member;
import vo.OrderList;

public class PurchaseDAO {
	private static PurchaseDAO purchaseDAO;
	private Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	int pageCount = 5;
	int range = 5;
	
	private PurchaseDAO() {
		
	}
	public static PurchaseDAO getInstance() {
		if(purchaseDAO == null) {
			purchaseDAO = new PurchaseDAO();
		}
		return purchaseDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	public LinkedList[] selectMyPurchaseList(String id, int nowPage) {
		String sql = "SELECT l.lecture_title, m.name, l.price, o.date, o.order_num FROM orderList AS o JOIN lecture AS l ON o.lecture_num = l.lecture_num JOIN member AS m ON l.number = m.number WHERE o.number = (SELECT number FROM member WHERE id = ?) AND o.refund = 0 ORDER BY o.order_num DESC LIMIT ?, " + pageCount;
		LinkedList[] purchaseList = null;
		LinkedList<OrderList> orList = new LinkedList<>();
		LinkedList<Member> memList = new LinkedList<>();
		LinkedList<Lecture> lecList = new LinkedList<>();
		OrderList or = null;
		Member mem = null;
		Lecture lec = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, (nowPage - 1) * pageCount);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					or = new OrderList();
					mem = new Member();
					lec = new Lecture();
					or.setOrder_num(rs.getInt("order_num"));
					or.setDate(rs.getString("date"));
					mem.setName(rs.getString("name"));
					lec.setLecture_title(rs.getString("lecture_title"));
					lec.setPrice(rs.getInt("price"));
					orList.add(or);
					memList.add(mem);
					lecList.add(lec);
				} while(rs.next());
			}
			purchaseList = new LinkedList[] {orList, memList, lecList};
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return purchaseList;
	}
	public int getMyPurchaseNumber(String id) {
		String sql = "SELECT count(*) AS c FROM orderList AS o JOIN member AS m ON o.number = m.number WHERE m.id = ? AND o.refund = 0";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("c");
				if (result % pageCount != 0) {
					result = (result / pageCount) + 1;
				} else {
					result = result / pageCount;
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
	public int getRefund(int order_num) {
		String sql = "UPDATE orderList SET refund = 1 WHERE order_num = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_num);
			result = pstmt.executeUpdate();
			if (result <= 0) {
				rollback(conn);
			}
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	public LinkedList[] selectPurchaseAllList(int nowPage) {
		String sql = "SELECT l.lecture_title, m.id, m.name, l.price, o.date, o.order_num FROM orderList AS o JOIN lecture AS l ON o.lecture_num = l.lecture_num JOIN member AS m ON o.number = m.number WHERE o.refund = 0 ORDER BY o.order_num DESC LIMIT ?, " + pageCount;
		LinkedList[] purchaseList = null;
		LinkedList<OrderList> orList = new LinkedList<>();
		LinkedList<Member> memList = new LinkedList<>();
		LinkedList<Lecture> lecList = new LinkedList<>();
		OrderList or = null;
		Member mem = null;
		Lecture lec = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (nowPage - 1) * pageCount);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					or = new OrderList();
					mem = new Member();
					lec = new Lecture();
					or.setOrder_num(rs.getInt("order_num"));
					or.setDate(rs.getString("date"));
					mem.setName(rs.getString("name"));
					mem.setId(rs.getString("id"));
					lec.setLecture_title(rs.getString("lecture_title"));
					lec.setPrice(rs.getInt("price"));
					orList.add(or);
					memList.add(mem);
					lecList.add(lec);
				} while(rs.next());
			}
			purchaseList = new LinkedList[] {orList, memList, lecList};
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return purchaseList;
	}
	public int getPurchaseNumber() {
		String sql = "SELECT count(*) AS c FROM orderList WHERE refund = 0";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("c");
				if (result % pageCount != 0) {
					result = (result / pageCount) + 1;
				} else {
					result = result / pageCount;
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
	public LinkedList[] selectPurchaseRefundList(int nowPage) {
		String sql = "SELECT l.lecture_title, m.id, m.name, l.price, o.date, o.order_num FROM orderList AS o JOIN lecture AS l ON o.lecture_num = l.lecture_num JOIN member AS m ON o.number = m.number WHERE o.refund = 1 ORDER BY o.order_num DESC LIMIT ?, " + pageCount;
		LinkedList[] purchaseList = null;
		LinkedList<OrderList> orList = new LinkedList<>();
		LinkedList<Member> memList = new LinkedList<>();
		LinkedList<Lecture> lecList = new LinkedList<>();
		OrderList or = null;
		Member mem = null;
		Lecture lec = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (nowPage - 1) * pageCount);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					or = new OrderList();
					mem = new Member();
					lec = new Lecture();
					or.setOrder_num(rs.getInt("order_num"));
					or.setDate(rs.getString("date"));
					mem.setName(rs.getString("name"));
					mem.setId(rs.getString("id"));
					lec.setLecture_title(rs.getString("lecture_title"));
					lec.setPrice(rs.getInt("price"));
					orList.add(or);
					memList.add(mem);
					lecList.add(lec);
				} while(rs.next());
			}
			purchaseList = new LinkedList[] {orList, memList, lecList};
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return purchaseList;
	}
	public int getRefundNumber() {
		String sql = "SELECT count(*) AS c FROM orderList WHERE refund = 1";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
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
	public int doRefund(int order_num) {
		String sql = "SELECT o.number, l.price FROM orderlist AS o JOIN lecture AS l ON o.lecture_num = l.lecture_num WHERE order_num = ?";
		int result = 0;
		int price = 0;
		int number = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				price = rs.getInt("price");
				number = rs.getInt("number");
				sql = "DELETE FROM orderlist WHERE order_num = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, order_num);
				result = pstmt.executeUpdate();
				if(result > 0) {
					sql = "UPDATE member SET point = point + ? WHERE number = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, price);
					pstmt.setInt(2, number);
					result = pstmt.executeUpdate();
				} else {
					rollback(conn);
				}
			} else {
				rollback(conn);
			}
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return result;
	}
}

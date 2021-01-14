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
import vo.Pay;

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
		String sql = "SELECT * FROM pay AS p LEFT JOIN lecture AS l ON p.lecture_num = l.lecture_num WHERE p.number = (SELECT number FROM member WHERE id = ?) ORDER BY p.pay_number DESC LIMIT ?, " + pageCount;
		LinkedList[] payList = null;
		LinkedList<Pay> pList = new LinkedList<>();
		LinkedList<Lecture> lecList = new LinkedList<>();
		Pay pay = null;
		Lecture lec = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, (nowPage - 1) * pageCount);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					pay = new Pay();
					lec = new Lecture();
					pay.setLecture_num(rs.getInt("lecture_num"));
					pay.setPay_code(rs.getString("pay_code"));
					pay.setPay_number(rs.getInt("pay_number"));
					pay.setRefund(rs.getInt("refund"));
					pay.setDate(rs.getString("date"));
					pay.setType(rs.getString("type"));
					if(rs.getString("lecture_title") != null) {
						lec.setLecture_title(rs.getString("lecture_title"));
					} else {
						lec.setLecture_title("<삭제된 강의>");
					}
					pList.add(pay);
					lecList.add(lec);
				} while(rs.next());
				payList = new LinkedList[] {pList, lecList};
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					close(rs);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					close(pstmt);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return payList;
	}
	public int getMyPurchaseNumber(String id) {
		String sql = "SELECT count(*) AS c FROM pay AS p JOIN member AS m ON p.number = m.number WHERE m.id = ?";
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
			if(rs != null) {
				try {
					close(rs);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					close(pstmt);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	public int getRefund(int pay_number) {
		String sql = "UPDATE pay SET refund = 1 WHERE pay_number = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pay_number);
			result = pstmt.executeUpdate();
			if (result <= 0) {
				rollback(conn);
			}
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					close(rs);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					close(pstmt);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	public LinkedList[] selectPurchaseAllList(int nowPage) {
		String sql = "SELECT p.pay_number, p.lecture_num, p.number, p.type, p.pay_code, p.date, l.lecture_title, m4tea.name AS teacher, m4stu.name AS buyer, q4stu.name AS qbuyer, q4tea.name AS qteacher FROM pay AS p LEFT JOIN lecture AS l ON p.lecture_num = l.lecture_num LEFT JOIN member AS m4tea ON l.number = m4tea.number LEFT JOIN member AS m4stu ON p.number = m4stu.number LEFT JOIN quitter AS q4stu ON p.number = q4stu.number LEFT JOIN quitter AS q4tea ON l.number = q4tea.number WHERE refund = 0 ORDER BY pay_number DESC LIMIT ?, " + pageCount;
		LinkedList[] payList = null;
		LinkedList<Pay> pList = new LinkedList<>();
		LinkedList<Lecture> lecList = new LinkedList<>();
		LinkedList<Member> teaList = new LinkedList<>();
		LinkedList<Member> stuList = new LinkedList<>();
		Pay pay = null;
		Lecture lec = null;
		Member tea = null;
		Member stu = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (nowPage - 1) * pageCount);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					pay = new Pay();
					lec = new Lecture();
					tea = new Member();
					stu = new Member();
					pay.setPay_number(rs.getInt("pay_number"));
					pay.setLecture_num(rs.getInt("lecture_num"));
					pay.setNumber(rs.getInt("number"));
					pay.setType(rs.getString("type"));
					pay.setPay_code(rs.getString("pay_code"));
					pay.setDate(rs.getString("date"));
					if(rs.getString("lecture_title") != null) {
						lec.setLecture_title(rs.getString("lecture_title"));
					} else {
						lec.setLecture_title("<삭제된 강의>");
					}
					if(rs.getString("teacher") != null) {
						tea.setName(rs.getString("teacher"));
					} else {
						tea.setName(rs.getString("qteacher"));
					}
					if(rs.getString("buyer") != null) {
						stu.setName(rs.getString("buyer"));
					} else {
						stu.setName(rs.getString("qbuyer"));
					}
					pList.add(pay);
					lecList.add(lec);
					teaList.add(tea);
					stuList.add(stu);
				} while(rs.next());
			}
			payList = new LinkedList[] {pList, lecList, teaList, stuList};
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					close(rs);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					close(pstmt);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return payList;
	}
	public int getPurchaseNumber() {
		String sql = "SELECT count(*) AS c FROM pay WHERE refund = 0";
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
			if(rs != null) {
				try {
					close(rs);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					close(pstmt);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	public LinkedList[] selectPurchaseRefundList(int nowPage) {
		String sql = "SELECT p.pay_number, p.lecture_num, p.number, p.type, p.pay_code, p.date, l.lecture_title, m.id, q.id AS qid FROM pay AS p LEFT JOIN lecture AS l ON p.lecture_num = l.lecture_num LEFT JOIN member AS m ON p.number = m.number LEFT JOIN quitter AS q ON q.number = p.number WHERE p.refund = 1 ORDER BY p.pay_number DESC LIMIT ?, " + pageCount;
		LinkedList[] payList = null;
		LinkedList<Pay> pList = new LinkedList<>();
		LinkedList<Lecture> lecList = new LinkedList<>();
		LinkedList<Member> memList = new LinkedList<>();
		Pay pay = null;
		Lecture lec = null;
		Member mem = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (nowPage - 1) * pageCount);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					pay = new Pay();
					lec = new Lecture();
					mem = new Member();
					pay.setPay_number(rs.getInt("pay_number"));
					pay.setLecture_num(rs.getInt("lecture_num"));
					pay.setNumber(rs.getInt("number"));
					pay.setType(rs.getString("type"));
					pay.setPay_code(rs.getString("pay_code"));
					pay.setDate(rs.getString("date"));
					if(rs.getString("lecture_title") != null) {
						lec.setLecture_title(rs.getString("lecture_title"));
					} else {
						lec.setLecture_title("<삭제된 강의>");
					}
					if(rs.getString("id") != null) {
						mem.setId(rs.getString("id"));
					} else {
						mem.setId(rs.getString("qid"));
					}
					pList.add(pay);
					lecList.add(lec);
					memList.add(mem);
				} while(rs.next());
			}
			payList = new LinkedList[] {pList, lecList, memList};
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					close(rs);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					close(pstmt);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return payList;
	}
	public int getRefundNumber() {
		String sql = "SELECT count(*) AS c FROM pay WHERE refund = 1";
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
			if(rs != null) {
				try {
					close(rs);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					close(pstmt);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	public int doRefund(int pay_number) {
		String sql = "DELETE FROM pay WHERE pay_number = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pay_number);
			result = pstmt.executeUpdate();
			if(result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					close(rs);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					close(pstmt);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}

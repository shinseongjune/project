package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Faq;
import vo.Lecture;
import vo.Member;

public class FaqDAO {
	private static FaqDAO faqDAO;
	private Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	private FaqDAO() {
		
	}
	
	public static FaqDAO getInstance() {
		if(faqDAO == null) {
			faqDAO = new FaqDAO();
		}
		return faqDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public ArrayList<Faq> selectFaqList() {
		String sql = "SELECT question, answer FROM faq";
		ArrayList<Faq> faqList = new ArrayList<Faq>();
		Faq faq = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					faq = new Faq();
					faq.setQuestion(rs.getString("question"));
					faq.setAnswer(rs.getString("answer"));
					faqList.add(faq);
				} while(rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return faqList;
	}
}

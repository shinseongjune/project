package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.FaqDAO;
import vo.Faq;

public class FaqListService {

	public LinkedList<Faq> getFaqList() {
		Connection conn = getConnection();
		FaqDAO faqDAO = FaqDAO.getInstance();
		faqDAO.setConnection(conn);
		LinkedList<Faq> faqList = faqDAO.selectFaqList();
		close(conn);
		
		return faqList;
	}

}

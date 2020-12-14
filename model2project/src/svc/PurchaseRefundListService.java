package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.PurchaseDAO;

public class PurchaseRefundListService {

	public LinkedList[] getRefundList(int nowPage) {
		Connection conn = getConnection();
		PurchaseDAO purchaseDAO = PurchaseDAO.getInstance();
		purchaseDAO.setConnection(conn);
		LinkedList[] purchaseList = purchaseDAO.selectPurchaseRefundList(nowPage);
		close(conn);
		
		return purchaseList;
	}

}

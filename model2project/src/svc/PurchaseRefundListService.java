package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.PurchaseDAO;

public class PurchaseRefundListService {

	public LinkedList[] getRefundList(int nowPage) {
		LinkedList[] payList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			PurchaseDAO purchaseDAO = PurchaseDAO.getInstance();
			purchaseDAO.setConnection(conn);
			payList = purchaseDAO.selectPurchaseRefundList(nowPage);
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
		
		return payList;
	}

}

package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.PurchaseDAO;

public class PurchaseRefundService {

	public int getRefund(int order_num) {
		Connection conn = getConnection();
		PurchaseDAO purchaseDAO = PurchaseDAO.getInstance();
		purchaseDAO.setConnection(conn);
		int result = purchaseDAO.getRefund(order_num);
		close(conn);
		
		return result;
	}

}

package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.PurchaseDAO;

public class PurchaseDoRefundService {

	public int doRefund(int order_num) {
		Connection conn = getConnection();
		PurchaseDAO purchaseDAO = PurchaseDAO.getInstance();
		purchaseDAO.setConnection(conn);
		int result = purchaseDAO.doRefund(order_num);
		close(conn);
		
		return result;
	}

}

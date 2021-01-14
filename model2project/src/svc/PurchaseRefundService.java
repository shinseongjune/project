package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.PurchaseDAO;

public class PurchaseRefundService {

	public int getRefund(int pay_number) {
		int result = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			PurchaseDAO purchaseDAO = PurchaseDAO.getInstance();
			purchaseDAO.setConnection(conn);
			result = purchaseDAO.getRefund(pay_number);
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
		
		return result;
	}

}

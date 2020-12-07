package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MessengerDAO;

public class MessengerService {

	public ArrayList[] getMessageList(int nowPage) {
		Connection conn = getConnection();
		MessengerDAO messengerDAO = MessengerDAO.getInstance();
		messengerDAO.setConnection(conn);
		ArrayList[] messageList = messengerDAO.selectMessageList(nowPage);
		close(conn);
		
		return messageList;
	}

}

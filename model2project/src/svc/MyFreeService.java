package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.FreeDAO;

public class MyFreeService {

	public ArrayList[] getMyFreeList(String id, int nowPage) {
		Connection conn = getConnection();
		FreeDAO freeDAO = FreeDAO.getInstance();
		freeDAO.setConnection(conn);
		ArrayList[] freeList = freeDAO.selectMyFreeList(id, nowPage);
		close(conn);
		
		return freeList;
	}

}

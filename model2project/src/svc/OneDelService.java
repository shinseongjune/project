package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.OneOnOneDAO;

public class OneDelService {

	public int deleteOne(int one_on_one_num) {
		Connection conn = getConnection();
		OneOnOneDAO oneOnOneDAO = OneOnOneDAO.getInstance();
		oneOnOneDAO.setConnection(conn);
		int result = oneOnOneDAO.delOne(one_on_one_num);
		close(conn);
		
		return result;
	}

}

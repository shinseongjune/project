package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.LoginDAO;
import vo.Member;

public class LoginService {

	public Member getLoginMember(String id, String pw) {
		LoginDAO loginDAO = LoginDAO.getInstance();
		Connection conn = getConnection();
		loginDAO.setConnection(conn);
		Member loginMember = loginDAO.selectLoginMember(id, pw);
		close(conn);
		return loginMember;
	}

}

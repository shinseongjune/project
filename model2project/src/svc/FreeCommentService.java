package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.FreeDAO;
import vo.Free_Comment;

public class FreeCommentService {

	public int freeCommentExtra(Free_Comment fc, int number, int parent, int free_num) {
		Connection conn = getConnection();
		FreeDAO freeDAO = FreeDAO.getInstance();
		freeDAO.setConnection(conn);
		int result = freeDAO.exComment(fc, number, parent, free_num);
		close(conn);
		
		return result;
	}

	public int freeCommentDelete(int free_num, int comment_num) {
		Connection conn = getConnection();
		FreeDAO freeDAO = FreeDAO.getInstance();
		freeDAO.setConnection(conn);
		int result = freeDAO.delComment(free_num, comment_num);
		close(conn);
		
		return result;
	}

	public int freeCommentWrite(int free_num, int number, String contents) {
		Connection conn = getConnection();
		FreeDAO freeDAO = FreeDAO.getInstance();
		freeDAO.setConnection(conn);
		int result = freeDAO.writeComment(free_num, number, contents);
		close(conn);
		
		return result;
	}

}

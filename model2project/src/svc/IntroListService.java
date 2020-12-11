package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.LinkedList;
import dao.IntroDAO;
import vo.Intro;

public class IntroListService {

	public int getListCount() {
		int listCount=0;
		Connection conn=getConnection();
		IntroDAO introDAO=IntroDAO.getInstance();
		introDAO.setConnection(conn);
		listCount=introDAO.selectListCount();
		if(conn != null)
		close(conn);
		return listCount;
	}

	public LinkedList<Intro> getArticleList(int page, int limit) {
		LinkedList<Intro> articleList=null;
		Connection conn = getConnection();
		IntroDAO introDAO=IntroDAO.getInstance();
		introDAO.setConnection(conn);
		articleList=introDAO.selectArticleList(page, limit);
		if(conn != null)
		close(conn);
		return articleList;
	
	}
}

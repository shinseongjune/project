package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.IntroDAO;
import vo.Intro;

public class IntroDetailService {

	public Intro getArticle(int intro_num) {
		Intro article = null;
		Connection conn = getConnection();
		IntroDAO introDAO = IntroDAO.getInstance();
		introDAO.setConnection(conn);
		int updateCount = introDAO.updateReadCount(intro_num);
		
		if(updateCount > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		article = introDAO.selectArticle(intro_num);
		if(conn != null) close(conn);
		
		return article;
	}

}

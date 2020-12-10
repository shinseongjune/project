package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.NoticeDAO;
import vo.Notice;

public class NoticeService {

	public ArrayList<Notice> getNoticeList(int nowPage) {
		Connection conn = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(conn);
		ArrayList<Notice> noticeList = noticeDAO.selectNoticeList(nowPage);
		close(conn);
		
		return noticeList;
	}

}

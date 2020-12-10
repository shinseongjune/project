package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Notice;

public class NoticeDAO {
	private static NoticeDAO noticeDAO;
	private Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	int pageCount = 5;
	int range = 5;
	
	private NoticeDAO() {
		
	}

	public static NoticeDAO getInstance() {
		if(noticeDAO == null) {
			noticeDAO = new NoticeDAO();
		}
		return noticeDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public ArrayList<Notice> selectNoticeList(int nowPage) {
		String sql = "SELECT * FROM notice AS n ORDER BY n.notice_num DESC LIMIT ?, " + pageCount;
		ArrayList<Notice> noticeList = new ArrayList<Notice>();
		Notice not = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (nowPage - 1) * pageCount);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					not = new Notice();
					not.setTitle(rs.getString("title"));
					not.setContents(rs.getString("contents"));
					not.setNotice_num(rs.getInt("notice_num"));
					noticeList.add(not);
				} while(rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return noticeList;
	}

	public int getNoticeNumber() {
		String sql = "SELECT count(*) AS c FROM notice";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("c");
				if (result % 5 != 0) {
					result = (result / 5) + 1;
				} else {
					result = result / 5;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			close(pstmt);
		}
		return result;
	}
}

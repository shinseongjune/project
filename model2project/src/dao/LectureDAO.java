package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import vo.Lecture;
import vo.Lecture_Video;
import vo.Member;
import vo.Subject;

public class LectureDAO {
	private static LectureDAO lectureDAO;
	private Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	int pageCount = 8;
	int range = 5;
	
	private LectureDAO() {
		
	}

	public static LectureDAO getInstance() {
		if(lectureDAO == null) {
			lectureDAO = new LectureDAO();
		}
		return lectureDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public LinkedList[] selectLectureList(int nowPage) {
		String sql = "SELECT l.lecture_num, m.name, l.lecture_title, l.price, s.subject_name, v.video FROM member AS m JOIN lecture AS l ON m.number = l.number JOIN subject AS s ON l.subject_code = s.code JOIN lecture_video AS v ON l.lecture_num = v.lecture_num WHERE v.chapter = 1 ORDER BY lecture_num DESC LIMIT ?, " + pageCount;
		LinkedList<Lecture> lecList = new LinkedList<Lecture>();
		LinkedList<Member> memList = new LinkedList<Member>();
		LinkedList<Subject> subList = new LinkedList<Subject>();
		LinkedList<Lecture_Video> vidList = new LinkedList<>();
		LinkedList[] lectureList = null;
		Lecture lec = null;
		Member mem = null;
		Subject sub = null;
		Lecture_Video vid = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (nowPage - 1) * pageCount);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					lec = new Lecture();
					mem = new Member();
					sub = new Subject();
					vid = new Lecture_Video();
					
					lec.setLecture_num(rs.getInt("lecture_num"));
					lec.setLecture_title(rs.getString("lecture_title"));
					lec.setPrice(rs.getInt("price"));
					mem.setName(rs.getString("name"));
					sub.setSubject_name(rs.getString("subject_name"));
					vid.setVideo(rs.getString("video"));
					lecList.add(lec);
					memList.add(mem);
					subList.add(sub);
					vidList.add(vid);
				} while(rs.next());
			}
			lectureList = new LinkedList[] {lecList, memList, subList, vidList};
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return lectureList;
	}

	public int getLectureNumber() {
		String sql = "SELECT count(*) AS c FROM lecture";
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

	public LinkedList<Subject> getSubjectList() {
		String sql = "SELECT * FROM subject ORDER BY code DESC";
		LinkedList<Subject> subjectList = new LinkedList<>();
		Subject sub = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					sub = new Subject();
					sub.setCode(rs.getInt("code"));
					sub.setSubject_name(rs.getString("subject_name"));
					subjectList.add(sub);
				} while(rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return subjectList;
	}

	public LinkedList[] selectLectureList(String[] subject, int nowPage) {
		String sql = "SELECT l.lecture_num, m.name, l.lecture_title, l.price, s.subject_name, v.video FROM member AS m JOIN lecture AS l ON m.number = l.number JOIN subject AS s ON l.subject_code = s.code JOIN lecture_video AS v ON l.lecture_num = v.lecture_num WHERE v.chapter = 1 AND s.code = ? ORDER BY lecture_num DESC LIMIT ?, " + pageCount;
		LinkedList<Lecture> lecList = new LinkedList<Lecture>();
		LinkedList<Member> memList = new LinkedList<Member>();
		LinkedList<Subject> subList = new LinkedList<Subject>();
		LinkedList<Lecture_Video> vidList = new LinkedList<>();
		LinkedList[] lectureList = null;
		Lecture lec = null;
		Member mem = null;
		Subject sub = null;
		Lecture_Video vid = null;
		try {
			pstmt = conn.prepareStatement(sql);
			for(String s:subject) {
				pstmt.setString(1, s);
				pstmt.setInt(2, (nowPage - 1) * pageCount);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					do {
						lec = new Lecture();
						mem = new Member();
						sub = new Subject();
						vid = new Lecture_Video();
						
						lec.setLecture_num(rs.getInt("lecture_num"));
						lec.setLecture_title(rs.getString("lecture_title"));
						lec.setPrice(rs.getInt("price"));
						mem.setName(rs.getString("name"));
						sub.setSubject_name(rs.getString("subject_name"));
						vid.setVideo(rs.getString("video"));
						lecList.add(lec);
						memList.add(mem);
						subList.add(sub);
						vidList.add(vid);
					} while(rs.next());
				
				}
			}
			lectureList = new LinkedList[] {lecList, memList, subList, vidList};
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return lectureList;
	}

	public int getLectureNumber(String[] subject) {
		String sql = "SELECT count(*) AS c FROM lecture WHERE subject_code = ?";
		int result = 0;
		int r = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			for(String s:subject) {
				pstmt.setString(1, s);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					r = rs.getInt("c");
				}
				result += r;
			}
			
			if (result % 5 != 0) {
				result = (result / 5) + 1;
			} else {
				result = result / 5;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			close(pstmt);
		}
		return result;
	}

	public LinkedList[] selectLectureList(String id, int nowPage) {
		String sql = "SELECT l.lecture_num, m.name, l.lecture_title, l.price, s.subject_name, v.video FROM member AS m JOIN lecture AS l ON m.number = l.number JOIN subject AS s ON l.subject_code = s.code JOIN lecture_video AS v ON l.lecture_num = v.lecture_num WHERE v.chapter = 1 AND l.number = (SELECT number FROM member WHERE id = ?) ORDER BY lecture_num DESC LIMIT ?, " + pageCount;
		LinkedList<Lecture> lecList = new LinkedList<Lecture>();
		LinkedList<Member> memList = new LinkedList<Member>();
		LinkedList<Subject> subList = new LinkedList<Subject>();
		LinkedList<Lecture_Video> vidList = new LinkedList<>();
		LinkedList[] lectureList = null;
		Lecture lec = null;
		Member mem = null;
		Subject sub = null;
		Lecture_Video vid = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, (nowPage - 1) * pageCount);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					lec = new Lecture();
					mem = new Member();
					sub = new Subject();
					vid = new Lecture_Video();
					
					lec.setLecture_num(rs.getInt("lecture_num"));
					lec.setLecture_title(rs.getString("lecture_title"));
					lec.setPrice(rs.getInt("price"));
					mem.setName(rs.getString("name"));
					sub.setSubject_name(rs.getString("subject_name"));
					vid.setVideo(rs.getString("video"));
					lecList.add(lec);
					memList.add(mem);
					subList.add(sub);
					vidList.add(vid);
				} while(rs.next());
			}
			lectureList = new LinkedList[] {lecList, memList, subList, vidList};
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return lectureList;
	}

	public int getLectureNumber(String id) {
		String sql = "SELECT count(*) AS c FROM lecture AS l JOIN member AS m ON l.number = m.number WHERE m.id = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
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

	public LinkedList<Subject> selectSubList() {
		String sql = "SELECT * FROM subject ORDER BY code";
		LinkedList<Subject> subList = new LinkedList<>();
		Subject sub = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				sub = new Subject();
				sub.setCode(rs.getInt("code"));
				sub.setSubject_name(rs.getString("subject_name"));
				subList.add(sub);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return subList;
	}
}

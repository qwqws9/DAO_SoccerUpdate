package com.ejfrm.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ejfrm.VO.SoccerVO;

public class SoccerDAO {

	private SoccerDAO() {

	}

	private static SoccerDAO instance = new SoccerDAO();

	public static SoccerDAO getInstance() {
		return instance;
	}

	public Connection getConnection() {
		Connection con = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/Oracle");
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public void closeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<SoccerVO> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SoccerVO> list = new ArrayList<>();
		String sql = "select * from soccer";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SoccerVO sVo = new SoccerVO();
				sVo.setCode(rs.getInt("code"));
				sVo.setTeamname(rs.getString("teamname"));
				sVo.setCountry(rs.getString("country"));
				sVo.setHomeground(rs.getString("homeground"));
				sVo.setCoach(rs.getString("coach"));
				sVo.setPlayers(rs.getString("players"));
				sVo.setPicture(rs.getString("picture"));
				list.add(sVo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return list;
	}

	public void CreateTeam(SoccerVO sVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into soccer values(seq_soccer.nextval,?,?,?,?,?,?)";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sVo.getTeamname());
			pstmt.setString(2, sVo.getCountry());
			pstmt.setString(3, sVo.getHomeground());
			pstmt.setString(4, sVo.getCoach());
			pstmt.setString(5, sVo.getPlayers());
			pstmt.setString(6, sVo.getPicture());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	public SoccerVO select(int code) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from soccer where code = ?";
		SoccerVO sVo = new SoccerVO();
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sVo.setCode(rs.getInt("code"));
				sVo.setTeamname(rs.getString("teamname"));
				sVo.setCountry(rs.getString("country"));
				sVo.setHomeground(rs.getString("homeground"));
				sVo.setCoach(rs.getString("coach"));
				sVo.setPlayers(rs.getString("players"));
				sVo.setPicture(rs.getString("picture"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return sVo;
	}

	public void updateTeam(SoccerVO sVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update soccer set teamname=?, country=?, homeground=?, coach=?,"
				+ "players=?, picture=? where code = ?";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sVo.getTeamname());
			pstmt.setString(2, sVo.getCountry());
			pstmt.setString(3, sVo.getHomeground());
			pstmt.setString(4, sVo.getCoach());
			pstmt.setString(5, sVo.getPlayers());
			pstmt.setString(6, sVo.getPicture());
			pstmt.setInt(7, sVo.getCode());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	public void DeleteTeam(int code) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "delete soccer where code = ?";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	public int idCheck(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = -1;
		ResultSet rs = null;
		String sql = "select id from s_member where id = ?";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = -1;
			} else {
				result = 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return result;

	}

	public int adminCheck(String pw) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "select admin from admin where pw = ?";
		ResultSet rs = null;
		int result = -1;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return result;
	}

	public void signupUser(SoccerVO sVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into s_member values(?,?,?,?,?,?)";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sVo.getId());
			pstmt.setString(2, sVo.getPw());
			pstmt.setString(3, sVo.getEmail());
			pstmt.setString(4, sVo.getPhone());
			pstmt.setString(5, sVo.getProfile());
			pstmt.setInt(6, sVo.getGrade());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	public SoccerVO memberCheck(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from s_member where id = ?";
		SoccerVO sVo = new SoccerVO();
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sVo.setId(rs.getString("id"));
				sVo.setPw(rs.getString("pw"));
				sVo.setEmail(rs.getString("email"));
				sVo.setPhone(rs.getString("phone"));
				sVo.setGrade(rs.getInt("grade"));
				sVo.setProfile(rs.getString("profile"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return sVo;
	}

	public void updateUser(SoccerVO sVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update s_member set pw = ?, email = ?, " + "phone = ?, profile = ? where id = ?";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sVo.getPw());
			pstmt.setString(2, sVo.getEmail());
			pstmt.setString(3, sVo.getPhone());
			pstmt.setString(4, sVo.getProfile());
			pstmt.setString(5, sVo.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	public void updateUser1(SoccerVO sVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update s_member set pw = ?, email = ?, " + "phone = ? where id = ?";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sVo.getPw());
			pstmt.setString(2, sVo.getEmail());
			pstmt.setString(3, sVo.getPhone());
			pstmt.setString(4, sVo.getId());
			pstmt.executeUpdate();
			System.out.println(pstmt.executeUpdate());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	public void deleteUser(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "delete from s_member where id = ?";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	// 게시판 DAO

	public ArrayList<SoccerVO> selectAllboard() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SoccerVO> list = new ArrayList<>();

		String sql = "select * from s_board order by num desc";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SoccerVO sVo = new SoccerVO();
				sVo.setNum(rs.getInt("num"));
				sVo.setTitle(rs.getString("title"));
				sVo.setHit(rs.getInt("hit"));
				sVo.setMid(rs.getString("m_id"));
				sVo.setWritedate(rs.getTimestamp("writedate"));

				list.add(sVo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return list;
	}
	// ************************************************************* 임시 페이징 처리 ****************
	public ArrayList<SoccerVO> selectTest(int page) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SoccerVO> list = new ArrayList<>();
//		답글 없을때 쿼리
//		String sql = "select rnum,num,hit,title,writedate,m_id "
//				+ "from (select rownum rnum, num, hit, title, writedate, m_id  "
//				+ 		"from (select num,hit,title,writedate,m_id "
//				+ 			  "from s_board "
//				+ 			  "order by num desc)) "
//				+ "where rnum between ? and ?";
//		답글 쿼리		
		String sql = "select data.* "
				+ "from (select rownum rnum,data.* "
				+ 		"from (select data.* "
				+ 			  "from ((select num,title,content,writedate,m_id id, num pnum ,0 indent,hit, 0 step from s_board) "
				+					 "union "
				+					 "(select r_num num,r_title title, r_content content, r_time writedate,r_id id,r_pnum pnum,r_indent indent,r_hit hit, r_step step "
				+					  "from s_reply))data "
				+ 		"order by  pnum desc,step,indent) data) data "
				+ "where rnum between ? and ?";

		// 메서드이름 변경하고 답글 클릭시 글 긁어오는것 
		// 
		int start = (page - 1) * 10 + 1;
		int end = start + 9;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SoccerVO sVo = new SoccerVO();
				sVo.setNum(rs.getInt("num"));
				sVo.setTitle(rs.getString("title"));
				sVo.setHit(rs.getInt("hit"));
				sVo.setMid(rs.getString("id"));
				sVo.setWritedate(rs.getTimestamp("writedate"));
				sVo.setRindent(rs.getInt("indent"));

				list.add(sVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return list;
	}

	public void writeBoard(SoccerVO sVo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "insert into s_board(num,pass,title,content,writedate,m_id)"
				+ " values(seq_board.nextval,?,?,?,sysdate,?)";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, sVo.getPass());
			pstmt.setString(2, sVo.getTitle());
			pstmt.setString(3, sVo.getContent());
			pstmt.setString(4, sVo.getMid());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	public SoccerVO selectOneBoard(String num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SoccerVO sVo = new SoccerVO();
		String sql = "select data.* from ((select 0 pnum,num, title, content, 0 indent, (select count(*) from s_reply where r_pnum = ?) step, m_id id,  hit, pass from s_board) union (select r_pnum pnum, r_num num, r_title title, r_content content, r_indent indent, r_step step,r_id id, r_hit hit, r_pass pass from s_reply)) data where num = ?";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.setString(2, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sVo.setNum(Integer.parseInt(num));
				sVo.setMid(rs.getString("id"));
				sVo.setTitle(rs.getString("title"));
				sVo.setContent(rs.getString("content"));
				sVo.setPass(rs.getString("pass"));
				sVo.setRindent(Integer.parseInt(rs.getString("indent")));
				sVo.setRstep(Integer.parseInt(rs.getString("step")));
				sVo.setRpnum(rs.getInt("pnum"));
				
				

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return sVo;
	}

	public void updateBoard(SoccerVO sVo,int pnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		if(pnum == 0) {
			
			//원글 수정
			String sql = "update s_board set title=?, content=?, pass=? where num = ? ";
			try {
				con = getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, sVo.getTitle());
				pstmt.setString(2, sVo.getContent());
				pstmt.setString(3, sVo.getPass());
				pstmt.setInt(4, sVo.getNum());
				pstmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeConnection(con);
			}
		}else {
			//답글 수정
			String sql = "update s_reply set r_title=?, r_content=?, r_pass=? where r_num = ? ";
			try {
				con = getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, sVo.getTitle());
				pstmt.setString(2, sVo.getContent());
				pstmt.setString(3, sVo.getPass());
				pstmt.setInt(4, sVo.getNum());
				pstmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeConnection(con);
			}
		}
		
	}

	public void deleteBoard(String num,int pnum,int step,int indent) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		//지우려는글이 원글인경우
		if(pnum == 0) {
			sql = "delete from s_board where num = ?";
			try {
				con = getConnection();
				con.setAutoCommit(false);
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, num);
				pstmt.executeUpdate();
				
				if(step != 0) {
					sql = "delete from s_reply where r_pnum = ?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, num);
					pstmt.executeUpdate();
				}
				con.commit();
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeConnection(con);
			}
		//지우려는글이 답글인경우
		}else {
			
			sql = "delete from(select * from s_reply where r_pnum=? and r_step LIKE ?)";
			try {
				con = getConnection();
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, pnum);
				pstmt.setInt(2, step);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeConnection(con);
			}	
		} 
	}

	public void hitUp(String num) {
		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "update s_board set hit = hit+1 where num = ?";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, num);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}
	
	public void hitDown(String num) {
		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "update s_board set hit = hit-1 where num = ?";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, num);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	// 코멘트 DAO

	public void commentWrite(SoccerVO sVo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "insert into s_comment values(seq_comment.nextval,?,?,sysdate,?)";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sVo.getCid());
			pstmt.setString(2, sVo.getCcontent());
			pstmt.setInt(3, sVo.getParentnum());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}

	}

	/*public ArrayList<SoccerVO> commentView(String num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SoccerVO> list = new ArrayList<>();
		String sql = "select c.c_num, c.c_id, c.c_content, c.c_time from s_board b, s_comment c "
				+ "where b.num = c.parentnum and b.num = ? "
				+ "order by 4 desc";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SoccerVO sVo = new SoccerVO();
				sVo.setCnum(rs.getInt("c_num"));
				sVo.setCid(rs.getString("c_id"));
				sVo.setCcontent(rs.getString("c_content"));
				sVo.setCtime(rs.getTimestamp("c_time"));
				list.add(sVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return list;
	}*/
	
	// 댓글 페이지 처리하는 쿼리 
	public ArrayList<SoccerVO> commentView(String num,int page) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SoccerVO> list = new ArrayList<>();
		String sql = "select c.c_num, c.c_id, c.c_content, c.c_time, rnum from (select num from((select num from s_board) union (select r_num num from s_reply))) b, (select rownum rnum,d.c_num,d.c_id,d.c_content,d.c_time,d.parentnum from (select num from((select num from s_board) union (select r_num num from s_reply)))e ,(select c_num,c_id,c_content,c_time,parentnum  from s_comment order by c_num desc) d  where e.num = d.parentnum and e.num = ?) c where b.num = c.parentnum and b.num = ? and rnum between ? and ?";
		int start = (page - 1) * 5 + 1;
		int end = start + 4;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.setString(2, num);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SoccerVO sVo = new SoccerVO();
				sVo.setCnum(rs.getInt("c_num"));
				sVo.setCid(rs.getString("c_id"));
				sVo.setCcontent(rs.getString("c_content"));
				sVo.setCtime(rs.getTimestamp("c_time"));
				list.add(sVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return list;
	}

	//댓글이 존재하는지 확인하는 쿼리
	public int commentCheck(String num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "select * from s_comment where parentnum=?";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = 1;
			} else {
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return result;
	}
	

	public SoccerVO selectComment(String cnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SoccerVO sVo = new SoccerVO();
		
		String sql = "select * from s_comment where c_num = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				sVo.setCcontent(rs.getString("c_content"));
				sVo.setCid(rs.getString("c_id"));
				sVo.setParentnum(rs.getInt("parentnum"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
		return sVo;
	}
	
	public void commentUpdate(String cnum, String ccontent) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "update s_comment set c_content=? where c_num=?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ccontent);
			pstmt.setString(2, cnum);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
	}
	
	public void commentDelete(String cnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from s_comment where c_num = ?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cnum);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
		
	}
	
	//댓글수 조회
	public ArrayList<SoccerVO> commentCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SoccerVO> clist = new ArrayList<>();
		String sql = "select count(c.parentnum) c_count,b.num num from s_board b, s_comment c where b.num = c.parentnum(+) group by c.parentnum,b.num union select count(c.parentnum) c_count,r.r_num num from s_reply r, s_comment c where r.r_num = c.parentnum(+) group by c.parentnum,r.r_num";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SoccerVO sVo = new SoccerVO();
				sVo.setNum(rs.getInt("num"));
				sVo.setCcount(rs.getInt("c_count"));
				clist.add(sVo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
		return clist;
	}
	
	//게시물 페이징처리 쿼리
	
	public int selectTotalCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalCount = 0;
		
		String sql = "select count(num) total from ((select num from s_board) union (select r_num num from s_reply))";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalCount = rs.getInt("total");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
		return totalCount;
	}
	
	//댓글 페이징처리 쿼리
	
		public int commentTotalCount(String num) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int totalCount = 0;
			
			String sql = "select count(*) from s_comment where parentnum=?";
			try {
				con = getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, num);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					totalCount = rs.getInt("count(*)");
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				closeConnection(con);
			}
			return totalCount;
		}
		
		//답글 
		
		public void replyInsert(SoccerVO sVo) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			String sql = "insert into s_reply values(seq_board.nextval,?,?,?,sysdate,?,?,?,?,?)";
			
			try {
				con = getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, sVo.getRtitle());
				pstmt.setString(2, sVo.getRpass());
				pstmt.setString(3, sVo.getRcontent());
				pstmt.setInt(4, sVo.getRindent());
				pstmt.setInt(5, sVo.getRstep());
				pstmt.setInt(6, sVo.getRpnum());
				pstmt.setString(7, sVo.getRid());
				pstmt.setInt(8, 0);
				
				pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				closeConnection(con);
			}
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

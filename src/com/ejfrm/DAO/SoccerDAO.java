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

		String sql = "select * from s_board";

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
		String sql = "select * from s_board where num = ?";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sVo.setNum(Integer.parseInt(num));
				sVo.setMid(rs.getString("m_id"));
				sVo.setTitle(rs.getString("title"));
				sVo.setContent(rs.getString("content"));
				sVo.setPass(rs.getString("pass"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return sVo;
	}

	public void updateBoard(SoccerVO sVo) {
		Connection con = null;
		PreparedStatement pstmt = null;

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
	}

	public void deleteBoard(String num) {
		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "delete from s_board where num = ?";

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

}

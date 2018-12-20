package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Bean.UserInfo;
import Bean.user;

public class UDAOConcrete extends DAOBase implements UserDAO {

	@Override
	public user search(String userid) {
		
		Connection conn = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		user u=null;
		
		try {
			conn=getConnection();
			String sql="select * from user where useris=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, userid);
			rs=ps.getResultSet();
			if(rs.next()) {
				u=new user();
				u.setUserid(userid);
				u.setPassword(rs.getString("password"));
			}
			rs.close();
			ps.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public user getUserByID(String userid, String pwd) {
		Connection conn = null;
		PreparedStatement ps=null;
		user user1=null;
		ResultSet rs=null;
		try{
			conn = getConnection();
			String finalsql = null;
			finalsql="select * from user where userid=? and password=? ";
			ps = conn.prepareStatement(finalsql);
			ps.setString(1, userid);
			ps.setString(2, pwd);
			rs=ps.getResultSet();
			if(rs.next()) {
				user1=new user();
				user1.setUserid(userid);
				user1.setPassword(pwd);			
			}
			rs.close();
			ps.close();
			conn.close();

		}catch(Exception e){
			e.printStackTrace();
		}
		return user1;
	}

	@Override
	public user getUserByEM(String email, String pwd) {
		Connection conn = null;
		PreparedStatement ps=null;
		user user1=null;
		ResultSet rs=null;
		try{
			conn = getConnection();
			String finalsql = null;
			finalsql="select * from user where email=? and password=? ";
			ps = conn.prepareStatement(finalsql);
			ps.setString(1, email);
			ps.setString(2, pwd);
			rs=ps.getResultSet();
			if(rs.next()) {
				user1=new user();
				user1.setUserid(rs.getString("userid"));
				user1.setUserid(email);
				user1.setPassword(pwd);
				
			}
			rs.close();
			ps.close();
			conn.close();

		}catch(Exception e){
			e.printStackTrace();
		}
		return user1;
	}

	@Override
	public UserInfo queryUser(String userid) {
		
		Connection conn = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		UserInfo u=new UserInfo();
		
		try {
			conn=getConnection();
			String sql1="select * from userdetail where userid=?";
			ps=conn.prepareStatement(sql1);
			ps.setString(1, userid);
			rs=ps.getResultSet();
			if(rs.next()) {
				u.setUserid(userid);
				u.setName(rs.getString("name"));
				u.setMaxborrow(rs.getInt("maxborrow"));
				u.setBorrowcount(rs.getInt("borrowcount"));
			}
			rs.close();
			ps.close();
			
			String sql2="select * from user where userid=?";
			ps=conn.prepareStatement(sql2);
			ps.setString(1, userid);
			rs=ps.getResultSet();
			if(rs.next()) {
				u.setEmail(rs.getString("email"));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return u;
	}

}

package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Bean.UserInfo;
import Bean.user;

public class UDAOConcrete extends DAOBase implements UserDAO {

	@Override
	public user search(String userid) {
		
		Connection conn = null;
		Statement s=null;
		ResultSet rs=null;
		user u=null;
		
		try {
			conn=getConnection();
			String sql="select * from user where userid='"+userid+"'";
			s=conn.createStatement();
			rs=s.executeQuery(sql);
			rs=s.getResultSet();
			if(rs.next()) {
				u=new user();
				u.setUserid(userid);
				u.setPassword(rs.getString("password"));
				u.setEmail(rs.getString("email"));
			}
			rs.close();
			s.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public user getUserByID(String userid, String pwd) {
		Connection conn = null;
		Statement s=null;
		user user1=null;
		ResultSet rs=null;
		try{
			conn = getConnection();
			String finalsql = null;
			finalsql="select * from user where userid='"+userid+"' and password='"+pwd+"'";
			s = conn.createStatement();
			s.executeQuery(finalsql);
			rs=s.getResultSet();
			if(rs.next()) {
				user1=new user();
				user1.setUserid(userid);
				user1.setPassword(pwd);			
			}
			rs.close();
			s.close();
			conn.close();

		}catch(Exception e){
			e.printStackTrace();
		}
		return user1;
	}

	@Override
	public user getUserByEM(String email, String pwd) {
		Connection conn = null;
		Statement s=null;
		user user1=null;
		ResultSet rs=null;
		try{
			conn = getConnection();
			String finalsql = null;
			finalsql="select * from user where email='"+email+"' and password='"+pwd+"'";
			s = conn.createStatement();
			s.executeQuery(finalsql);
			rs=s.getResultSet();
			if(rs.next()) {
				
				user1=new user();
				user1.setUserid(rs.getString("userid"));
				user1.setEmail(email);
				user1.setPassword(pwd);
				
			}
			rs.close();
			s.close();
			conn.close();

		}catch(Exception e){
			e.printStackTrace();
		}
		return user1;
	}

	@Override
	public UserInfo queryUser(String userid) {
		//System.out.println(userid+"queryUser");
		Connection conn = null;
		Statement s=null;
		ResultSet rs=null;
		UserInfo u=new UserInfo();
		try {
			conn=getConnection();
			String sql1="select * from userdetail where userdetail.userid='"+userid+"'";
			s=conn.createStatement();
			s.executeQuery(sql1);
			rs=s.getResultSet();
			if(rs.next()) {
				u.setUserid(userid);
				u.setName(rs.getString("name"));
				u.setMaxborrow(rs.getInt("maxborrow"));
				u.setBorrowcount(rs.getInt("borrowcount"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				s.close();
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return u;
	}

	@Override
	public boolean updateEmail(String userid,String email) {
		//System.out.println(userid+" "+email);
		Connection conn=null;
		PreparedStatement ps=null;
		boolean b=false;
		try {
			conn=getConnection();
			ps=conn.prepareStatement("update user set email=? where userid=?");
			ps.setString(1, email);
			ps.setString(2, userid);
			ps.executeUpdate();
			b=true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return b;
		
	}

}

package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
			if(rs.next()) {
				u=new user();
				u.setUserid(userid);
				u.setPassword(rs.getString("password"));
				u.setMaxborrow(rs.getInt("maxborrow"));
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
	public user getUser(String userid, String pwd) {
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

}

package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Bean.user;

public class UDAOConcrete extends DAOBase implements UserDAO {

	@Override
	public user search(String userid, String pwd) {
		return null;
		// TODO Auto-generated method stub

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

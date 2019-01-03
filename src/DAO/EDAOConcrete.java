package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Bean.BorrowBook;

public class EDAOConcrete extends DAOBase implements EvaluateDAO{

	@Override
	public boolean addevaluate(String callnumber,String userid,int score) {
		Connection conn=null;
		PreparedStatement ps=null;
		boolean b=false;
		try {
			conn=getConnection();
			String sql="insert into evaluate values(?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, callnumber);
			ps.setString(2, userid);
			ps.setInt(3, score);
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

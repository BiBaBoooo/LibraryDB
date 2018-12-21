package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UDDAOConcrete extends DAOBase implements UserDetailDAO {

	@Override
	public boolean addBorrowCount(String userid) {
		Connection conn=null;
		PreparedStatement ps=null;
		boolean b=false;
		try {
			conn=getConnection();
			ps=conn.prepareStatement("update userdetail set borrowcount=borrowcount+1 where userid=?");
			ps.setString(1, userid);
			ps.executeUpdate();
			b=true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
				ps.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return b;
	}

	@Override
	public boolean subBorrowCount(String userid) {
		Connection conn=null;
		PreparedStatement ps=null;
		boolean b=false;
		try {
			conn=getConnection();
			ps=conn.prepareStatement("update userdetail set borrowcount=borrowcount-1 where userid=?");
			ps.setString(1, userid);
			ps.executeUpdate();
			b=true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
				ps.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return b;
	}

	@Override
	public boolean checkMaxBorrow(String userid) {
		String sql="select borrowcount,maxborrow from userdetail where userid='"+userid+"'";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		boolean b=false;
		try {
			conn=getConnection();
			stmt=conn.createStatement();
			stmt.executeQuery(sql);
			rs=stmt.getResultSet();
			if(rs.next()) {
				int borrow=rs.getInt("borrowcount");
				int max=rs.getInt("maxborrow");
				if(borrow==max)
					b=false;
				else if(borrow<max)
					b=true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

}

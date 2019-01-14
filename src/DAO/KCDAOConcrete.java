package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class KCDAOConcrete extends DAOBase implements KCDAO {

	public String searchByC(String callnumber) {
		
		String sql="select * from category,kc where category.cID=kc.cID and kc.callnumber='"+callnumber+"'";
		Connection conn = null;
		Statement stmt = null;
		String type="";
		ResultSet rs = null;
		try{
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeQuery(sql);
			rs=stmt.getResultSet();	
			while(rs.next()){
				//Category kb=new Category();
				
				String xx=rs.getString("cName");
				type=type+xx+"¡¢";
			}
			
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return type;
	}

	@Override
	public boolean insertt(int typeid, String bookid) {
		Connection conn=null;
		PreparedStatement ps=null;
		boolean b=false;
		try{
			String sql2 ="insert into kc(cID,callnumber) values(?,?)";
			conn=getConnection();
			ps=conn.prepareStatement(sql2);
			ps.setInt(1, typeid);
			ps.setString(2, bookid);
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

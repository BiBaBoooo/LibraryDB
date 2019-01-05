package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Bean.Category;
import Bean.KS;
import Bean.kindbook;

public class CategoryDAOConcrete extends DAOBase implements CategoryDAO{

	@Override
	public List<kindbook> searchtype(String type) {
		String sql="select * from kindbook,category,kc where kindbook.callnumber=kc.callnumber and category.cID=kc.cID and cName='"+type+"'"; //根据bookname查出指定的书
		List<kindbook> kbooks = new ArrayList<kindbook>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeQuery(sql);
			rs=stmt.getResultSet();
			while(rs.next()){
				kindbook kb=new kindbook();
				kb.setCallnumber(rs.getString("callnumber"));
				kb.setBookname(rs.getString("bookname"));
				kb.setAuthor(rs.getString("author"));
				kb.setCount(rs.getInt("count"));
				kbooks.add(kb);
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return kbooks;
	}

	@Override
	public String searchtypeid(String type) {
		String sql="select cID from category where cName= '"+type+"'"; //根据bookname查出指定的书
		List<String> ks = new ArrayList<String>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement ps=null;
		try{
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeQuery(sql);
			rs=stmt.getResultSet();
			while(rs.next()){
				String cid=rs.getString("cID");
				ks.add(cid);
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(ks.size()==1)
			return ks.get(0);
		else {
			if(inserttype(type)==true) {
				Connection conn2 = null;
				Statement stmt2 = null;
				ResultSet rs2 = null;
				try{
					conn2 = getConnection();
					stmt2 = conn2.createStatement();
					stmt2.executeQuery(sql);
					rs2=stmt2.getResultSet();
					while(rs2.next()){
						String cid=rs2.getString("cID");
						ks.add(cid);
					}
					rs2.close();
					stmt2.close();
					conn2.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if(ks.size()==1)
				return ks.get(0);
		}
		return null;
	}

	@Override
	public boolean inserttype(String type) {
		Connection conn=null;
		PreparedStatement ps=null;
		boolean b=false;
		try{
			String sql2 ="insert into category(cName) values(?)";
			conn=getConnection();
			ps=conn.prepareStatement(sql2);
			ps.setString(1, type);
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

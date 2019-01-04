package DAO;

import java.sql.Connection;
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
		// TODO Auto-generated method stub
		return kbooks;
	}
		//
}

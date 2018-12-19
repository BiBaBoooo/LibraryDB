package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Bean.kindbook;

public class KDAOConcrete extends DAOBase implements KindBookDAO {

	@Override
	public List<kindbook> searchByC(String callnumber) {
		String sql=null;
		if(callnumber==null)
			sql="select * from kindbook";  //查出所有的
		else
			sql="select * from kindbook where callnumber='"+callnumber+"'"; //根据callnumber查出指定的书
		List<kindbook> kbooks = new ArrayList<kindbook>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeQuery(sql);
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
	public void updateInfo(kindbook k) {
		// TODO Auto-generated method stub

	}

}

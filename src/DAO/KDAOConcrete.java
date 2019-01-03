package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	public boolean updateInfo(kindbook k) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean b=false;
		try {
			conn=getConnection();
			pstm=conn.prepareStatement("update kindbook set bookname=?,author=?,count=?,content=?,catalog=?,publish=?,topic=? where callnumber=?");
			pstm.setString(1, k.getBookname());
			pstm.setString(2, k.getAuthor());
			pstm.setInt(3, k.getCount());
			pstm.setString(4, k.getContent());
			pstm.setString(5, k.getCatalog());
			pstm.setString(6, k.getPublish());
			pstm.setString(7, k.getTopic());
			pstm.setString(8, k.getCallnumber());
			pstm.executeUpdate();
			b=true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try{
				pstm.close();
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return b;
	}

	@Override
	public boolean updateContent(String content, String callnumber) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean t=false;
		try{
			conn=getConnection();
			String sql="update kindbook set content=? where callnumber=?";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, content);
			pstm.setString(2, callnumber);
			pstm.executeUpdate();
			t=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
				try{
					pstm.close();
					conn.close();
				}catch(Exception e){
					e.printStackTrace();
				}
		}
		return t;
	}

	@Override
	public List<kindbook> searchByname(String name) {
		String sql="select * from kindbook where bookname='"+name+"'"; //根据name查出指定的书
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
}

package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Bean.KS;
import Bean.specificbook;

public class SDAOConcrete extends DAOBase implements SpecificBookDAO {

	protected List<specificbook> executeSQL(String sql){
		List<specificbook> kbooks = new ArrayList<specificbook>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeQuery(sql);
			rs=stmt.getResultSet();
			while(rs.next()){
				specificbook kb=new specificbook();
				kb.setCallnumber(rs.getString("callnumber"));
				kb.setBarcode(rs.getString("barcode"));
				kb.setBuydate(rs.getDate("buydate"));
				kb.setPlace(rs.getString("place"));
				kb.setState(rs.getString("state"));
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
	public List<KS> search(String bookname) {
		String sql="select * from specificbook,kindbook where specificbook.callnumber=kindbook.callnumber and bookname like '%"+bookname+"%'"; //根据bookname查出指定的书
		List<KS> ks = new ArrayList<KS>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeQuery(sql);
			rs=stmt.getResultSet();
			while(rs.next()){
				KS kb=new KS();
				kb.setCallnumber(rs.getString("callnumber"));
				kb.setBarcode(rs.getString("barcode"));
				kb.setBookname(rs.getString("bookname"));
				kb.setAuthor(rs.getString("author"));
				kb.setBuydate(rs.getDate("buydate"));
				kb.setPlace(rs.getString("place"));
				kb.setState(rs.getString("state"));
				kb.setCatalog(rs.getString("catalog"));
				kb.setContent(rs.getString("content"));
				kb.setPublish(rs.getString("publish"));
				
				ks.add(kb);
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return ks;

	}

	@Override
	public List<specificbook> lookup(String barcode) {
		String sql=null;
		if(barcode==null)
			sql="select * from specificbook";  //查出所有的
		else
			sql="select * from specificbook where barcode='"+barcode+"'"; //根据barcode查出指定的书
		return executeSQL(sql);//
		
	}

	@Override
	public boolean updatespecificbook(String place, String barcode) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean t=false;
		try{
			conn=getConnection();
			String sql="update specificbook set place=? where barcode=?";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, place);
			pstm.setString(2, barcode);
			pstm.executeUpdate();
			t=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
				try{
					conn.close();
					pstm.close();
				}catch(Exception e){
					e.printStackTrace();
				}
		}
		return t;
	}
	@Override
	public boolean borrowBook(String barcode) {
		
		Connection conn=null;
		PreparedStatement ps=null;
		boolean b=false;
		try {
			conn=getConnection();
			ps=conn.prepareStatement("update specificbook set state='已借' where barcode=?");
			ps.setString(1, barcode);
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
	public boolean give_backBook(String barcode) {
		Connection conn=null;
		PreparedStatement ps=null;
		boolean b=false;
		try {
			conn=getConnection();
			ps=conn.prepareStatement("update specificbook set state='未借' where barcode=?");
			ps.setString(1, barcode);
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
	//@Override
	//public specificbook searchone(String barcode) {
	//	String sql="select * from specificbook where specificbook.carbode='"+barcode+"'"; //根据barcode查出指定的书

	//	return null;
	//}

}

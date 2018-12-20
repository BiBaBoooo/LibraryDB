package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Bean.kindbook;
import Bean.sepcificbook;

public class SDAOConcrete extends DAOBase implements SepcificBookDAO {

	protected List<sepcificbook> executeSQL(String sql){
		List<sepcificbook> kbooks = new ArrayList<sepcificbook>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeQuery(sql);
			rs=stmt.getResultSet();
			while(rs.next()){
				sepcificbook kb=new sepcificbook();
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
	public List<sepcificbook> search(String bookname) {
		String sql=null;
		if(bookname==null)
			sql="select * from sepcificbook";  //查出所有的
		else
			sql="select * from sepcificbook where bookname='"+bookname+"'"; //根据bookname查出指定的书
		
		return executeSQL(sql);

	}

	@Override
	public List<sepcificbook> lookup(String barcode) {
		String sql="select * from sepcificbook where barcode='"+barcode+"'"; //根据barcode查出指定的书
		return executeSQL(sql);//
		
	}

	@Override
	public boolean updatesecificbook(String place, String barcode) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean t=false;
		try{
			conn=getConnection();
			String sql="update sepcificbook set place=? where barcode=?";
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

}

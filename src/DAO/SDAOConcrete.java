package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import Bean.sepcificbook;

public class SDAOConcrete extends DAOBase implements SepcificBookDAO {

	@Override
	public List<sepcificbook> getAllBook(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<sepcificbook> search(String bookname) {
		return null;
		// TODO Auto-generated method stub

	}

	@Override
	public void update(sepcificbook s) {
		// TODO Auto-generated method stub

	}

	@Override
	public sepcificbook lookup(String barcode) {
		return null;
		// TODO Auto-generated method stub
		
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

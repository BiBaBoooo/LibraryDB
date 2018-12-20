package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import Bean.kindbook;

public class KDAOConcrete extends DAOBase implements KindBookDAO {

	@Override
	public List<kindbook> searchByC(String callnumber) {
		return null;
		// TODO Auto-generated method stub

	}

	@Override
	public void updateInfo(kindbook k) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean updatekindbook(String kkk, String callnumer) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean t=false;
		try{
			conn=getConnection();
			String sql="update kindbook set kkk=? where callnumer=?";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, kkk);
			pstm.setString(2, callnumer);
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

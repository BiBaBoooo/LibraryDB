package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Bean.collect;

public class CDAOConcrete extends DAOBase implements CollectDAO {

	private static final String CREATE_COLLECT_SQL="insert into collect values(?,?,?)";
	
	@Override
	public List<collect> search(String userid) {
		
		Connection conn = null;
		Statement s=null;
		ResultSet rs=null;
		List<collect> collects=new ArrayList<collect>();
		try {
			String sql="select * from collect where userid='"+userid+"'";
			conn=getConnection();
			s=conn.createStatement();
			s.executeQuery(sql);
			rs=s.getResultSet();
			while (rs.next()) {
				collect collect=new collect();
				collect.setUserid(userid);
				collect.setBarcode(rs.getString("barcode"));
				collect.setCollectdate(rs.getDate("collectdate"));
				collects.add(collect);
			}
			
			rs.close();
			s.close();
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return collects;
		
	}

	@Override
	public boolean insert(collect c) {
		Connection conn=null;
		PreparedStatement ps=null;
		boolean b=false;
		try {
			conn=getConnection();
			ps=conn.prepareStatement(CREATE_COLLECT_SQL);
			ps.setString(1, c.getBarcode());
			ps.setString(2, c.getUserid());
			Date sqlDate=new Date(c.getCollectdate().getTime());		// java.util.Date 转换成 java.sql.Date 
			ps.setDate(3, sqlDate);
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

package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Bean.KS;
import Bean.collect;

public class CDAOConcrete extends DAOBase implements CollectDAO {

	private static final String CREATE_COLLECT_SQL="insert into collect values(?,?,?)";
	
	@Override
	public List<KS> search(String userid) {
		
		Connection conn = null;
		Statement s=null;
		ResultSet rs=null;
		List<KS> collects=new ArrayList<KS>();
		try {
			String sql="select * from collect,specificbook,kindbook where collect.barcode=specificbook.barcode and specificbook.callnumber=kindbook.callnumber and collect.userid='"+userid+"'";
			conn=getConnection();
			s=conn.createStatement();
			s.executeQuery(sql);
			rs=s.getResultSet();
			while (rs.next()) {
				KS collect=new KS();
				collect.setCallnumber(rs.getString("callnumber"));
				collect.setBarcode(rs.getString("barcode"));
				collect.setBookname(rs.getString("bookname"));
				collect.setAuthor(rs.getString("author"));
				collect.setPlace(rs.getString("place"));
				collect.setState(rs.getString("state"));
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

	@Override
	public boolean delete(String userid, String barcode) {
		Connection conn=null;
		PreparedStatement ps=null;
		boolean b=false;
		try {
			conn=getConnection();
			ps=conn.prepareStatement("delete from collect where userid=? and barcode=?");
			ps.setString(1, userid);
			ps.setString(2, barcode);
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

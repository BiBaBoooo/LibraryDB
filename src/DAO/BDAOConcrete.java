package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Bean.borrow;
import Bean.user;

public class BDAOConcrete extends DAOBase implements BorrowDAO {

	private static final String CREATE_BORROW_SQL="insert into borrow values(?,?,?,?,?)";
	@Override
	public List<borrow> searchCurrent(String userid) {
		Connection conn = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		List<borrow> b=new ArrayList<borrow>();
		try {
			conn=getConnection();
			String sql="select * from borrow where userid=? and status='已借'";
			ps=conn.prepareStatement(sql);
			ps.setString(1, userid);
			if(rs.next()) {
				borrow book=new borrow();
				book.setBarcode(rs.getString("barcode"));
				book.setUserid(rs.getString("userid"));
				book.setBorrowdate(rs.getDate("borrowdate"));
				book.setBackdate(rs.getDate("backdate"));
				book.setStatus("已借");
				b.add(book);
			}
			rs.close();
			ps.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return b;	
	}
	
	@Override
	public List<borrow> searchHistory(String userid) {
		Connection conn = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		List<borrow> b=new ArrayList<borrow>();
		try {
			conn=getConnection();
			String sql="select * from borrow where userid=? and status='已还'";
			ps=conn.prepareStatement(sql);
			ps.setString(1, userid);
			if(rs.next()) {
				borrow book=new borrow();
				book.setBarcode(rs.getString("barcode"));
				book.setUserid(rs.getString("userid"));
				book.setBorrowdate(rs.getDate("borrowdate"));
				book.setBackdate(rs.getDate("backdate"));
				book.setStatus("已借");
				b.add(book);
			}
			rs.close();
			ps.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return b;
	}

	@Override
	public void insert(borrow borrow) {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=getConnection();
			ps=conn.prepareStatement(CREATE_BORROW_SQL);
			ps.setString(1, borrow.getBarcode());
			ps.setString(2, borrow.getUserid());
			Date sqlDate1=new Date(borrow.getBorrowdate().getTime());		// java.util.Date 转换成 java.sql.Date 
			ps.setDate(3, sqlDate1);
			Date sqlDate2=new Date(borrow.getBackdate().getTime());
			ps.setDate(4, sqlDate2);
			ps.setString(5, borrow.getStatus());
			ps.executeUpdate();
			ps.close();
			conn.close();			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
}

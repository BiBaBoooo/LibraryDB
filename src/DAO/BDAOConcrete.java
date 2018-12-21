package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Bean.BorrowBook;
import Bean.borrow;

public class BDAOConcrete extends DAOBase implements BorrowDAO {

	private static final String CREATE_BORROW_SQL="insert into borrow(barcode,userid,borrowdate,status) values(?,?,?,?)";
	@Override
	public List<BorrowBook> searchCurrent(String userid) {
		Connection conn = null;
		Statement s=null;
		ResultSet rs=null;
		
		List<BorrowBook> b=new ArrayList<BorrowBook>();
		try {
			conn=getConnection();
			String sql="select * from borrow,specificbook,kindbook where borrow.barcode=specificbook.barcode and specificbook.callnumber=kindbook.callnumber and borrow.userid='"+userid+"' and borrow.status='已借'";
			s=conn.createStatement();
			s.executeQuery(sql);
			rs=s.getResultSet();
			if(rs.next()) {
				BorrowBook book=new BorrowBook();
				book.setBarcode(rs.getString("barcode"));
				book.setCallnumber(rs.getString("callnumber"));
				book.setBookname(rs.getString("bookname"));
				book.setAuthor(rs.getString("author"));
				book.setBorrowdate(rs.getDate("borrowdate"));
				book.setBackdate(rs.getDate("backdate"));
				book.setStatus(rs.getString("status"));
				b.add(book);
			}
			rs.close();
			s.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return b;	
	}
	
	@Override
	public List<BorrowBook> searchHistory(String userid) {
		Connection conn = null;
		Statement s=null;
		ResultSet rs=null;
		
		List<BorrowBook> b=new ArrayList<BorrowBook>();
		try {
			conn=getConnection();
			String sql="select * from borrow,specificbook,kindbook where borrow.barcode=specificbook.barcode and specificbook.callnumber=kindbook.callnumber and borrow.userid='"+userid+"' and borrow.status='已还'";
			s=conn.createStatement();
			s.executeQuery(sql);
			rs=s.getResultSet();
			if(rs.next()) {
				BorrowBook book=new BorrowBook();
				book.setBarcode(rs.getString("barcode"));
				book.setCallnumber(rs.getString("callnumber"));
				book.setBookname(rs.getString("bookname"));
				book.setAuthor(rs.getString("author"));
				book.setBorrowdate(rs.getDate("borrowdate"));
				book.setBackdate(rs.getDate("backdate"));
				book.setStatus(rs.getString("status"));
				b.add(book);
			}
			rs.close();
			s.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return b;	
	}

	@Override
	public boolean insert(borrow borrow) {
		Connection conn=null;
		PreparedStatement ps=null;
		boolean b=false;
		try {
			conn=getConnection();
			ps=conn.prepareStatement(CREATE_BORROW_SQL);
			ps.setString(1, borrow.getBarcode());
			ps.setString(2, borrow.getUserid());
			Date sqlDate1=new Date(borrow.getBorrowdate().getTime());		// java.util.Date 转换成 java.sql.Date 
			ps.setDate(3, sqlDate1);
			//Date sqlDate2=new Date(borrow.getBackdate().getTime());
			//ps.setDate(4, sqlDate2);
			ps.setString(4, borrow.getStatus());
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
	public boolean updateStatus(String userid, String barcode) {
		Connection conn=null;
		PreparedStatement ps=null;
		boolean b=false;
		try {
			Calendar calendar=Calendar.getInstance();
			Date backdate=new Date(calendar.getTime().getTime());
			conn=getConnection();
			ps=conn.prepareStatement("update borrow set status='已还',backdate=? where userid=? barcode=?");
			ps.setDate(1, backdate);
			ps.setString(2, userid);
			ps.setString(3, barcode);
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
}

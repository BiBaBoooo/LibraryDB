package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Bean.comment;

public class CMDAOConcrete extends DAOBase implements CommentDAO{

	@Override
	public boolean addComment(String userid,String callnumber,String comment) {
		Connection conn=null;
		PreparedStatement ps=null;
		boolean b=false;
		try {
			conn=getConnection();
			String sql="insert into comment values(?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, callnumber);
			ps.setString(2, userid);
			ps.setString(3, comment);
			
			Calendar calendar=Calendar.getInstance();
			Date time=new Date(calendar.getTime().getTime());
			ps.setDate(4, time);
			
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
	public List<comment> searchComment(String callnumber) {
		
		Connection conn = null;
		Statement s=null;
		ResultSet rs=null;
		
		List<comment> comments=new ArrayList<comment>();
		try {
			conn=getConnection();
			String sql="select * from comment where callnumber='"+callnumber+"'";
			s=conn.createStatement();
			rs=s.executeQuery(sql);
			if(rs.next()) {
				comment comm=new comment();
				comm.setCallnumber(callnumber);
				comm.setUserid(rs.getString("userid"));
				comm.setComment(rs.getString("comment"));
				comm.setTime(rs.getDate("time"));
				comments.add(comm);
			}
			rs.close();
			s.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return comments;
	}

}

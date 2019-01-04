package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Bean.Category;
import Bean.kc;
import Bean.kindbook;

public class KCDAOConcrete extends DAOBase implements KCDAO {

	@Override
	public String searchByC(String callnumber) {
		// TODO Auto-generated method stub
		String sql="select * from category,kc where category.cID=kc.cID and kc.callnumber=' "+callnumber+"'";
		Connection conn = null;
		Statement stmt = null;
		String type=null;
		ResultSet rs = null;
		try{
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeQuery(sql);
			rs=stmt.getResultSet();	
			while(rs.next()){
				Category kb=new Category();
				type=type+" "+kb.getCname();
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return type;
	}
}

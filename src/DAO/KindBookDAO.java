package DAO;

import java.util.List;

import Bean.kindbook;

public interface KindBookDAO {

	public List<kindbook> searchByC(String callnumber);		//根据索书号查询
	public void updateInfo(kindbook k);
}

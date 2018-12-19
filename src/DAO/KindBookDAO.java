package DAO;

import Bean.kindbook;

public interface KindBookDAO {

	public void searchByC(String callnumber);
	public void updateInfo(kindbook k);
}

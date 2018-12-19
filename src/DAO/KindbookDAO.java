package DAO;

import Bean.kindbook;

public interface KindbookDAO {

	public void searchByC(String callnumber);
	public void searchByN(String bookname);
	public void updateInfo(kindbook k);
}

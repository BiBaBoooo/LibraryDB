package DAO;

import Bean.kindbook;

public interface KindbookDAO {

	public void searchByC(String callnumber);
	public void updateInfo(kindbook k);
}

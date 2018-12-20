package DAO;

import java.util.List;

import Bean.kindbook;

public interface KindBookDAO {

	public List<kindbook> searchByC(String callnumber);		//æ ¹æ®ç´¢ä¹¦å·æŸ¥è¯¢
	public void updateInfo(kindbook k);
	public boolean updatekindbook(String kkk,String callnumer);// ÍêÉÆÍ¼ÊéÏêÏ¸ĞÅÏ¢
}

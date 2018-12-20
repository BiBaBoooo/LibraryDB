package DAO;

import java.util.List;

import Bean.sepcificbook;

public interface SepcificBookDAO {

	public List<sepcificbook> getAllBook(String sql);
	public List<sepcificbook> search(String bookname);	//閫氳繃涔﹀悕鎼滅储
	public sepcificbook lookup(String barcode);		//鏍规嵁鏉″舰鐮佹煡璇㈠浘涔﹁鎯�
	public void update(sepcificbook s);
	public boolean updatesecificbook(String place,String  barcode);//更新一本书的位置信息
}

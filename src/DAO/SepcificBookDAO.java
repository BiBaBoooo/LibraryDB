package DAO;

import java.util.List;

import Bean.sepcificbook;

public interface SepcificBookDAO {

	public List<sepcificbook> search(String bookname);	//如果bookname为null,则查询所有的书；若不为null,则之查询对应的书
	public List<sepcificbook> lookup(String barcode);		//根据索书号查询  
	public boolean updatesecificbook(String place,String  barcode);//更新一本书的位置信息
}

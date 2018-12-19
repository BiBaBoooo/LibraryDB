package DAO;

import java.util.List;

import Bean.sepcificbook;

public interface SepcificbookDAO {

	public List<sepcificbook> getAllBook(String sql);
	public void search(String bookname);	//通过书名搜索
	public void lookup(String barcode);		//根据条形码查询图书详情
	public void update(sepcificbook s);
}

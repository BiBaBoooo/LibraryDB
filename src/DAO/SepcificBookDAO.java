package DAO;

import java.util.List;

import Bean.sepcificbook;

public interface SepcificBookDAO {

	public List<sepcificbook> getAllBook(String sql);
	public List<sepcificbook> search(String bookname);	//通过书名搜索
	public sepcificbook lookup(String barcode);		//根据条形码查询图书详情
	public void update(sepcificbook s);
}

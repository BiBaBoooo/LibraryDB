package DAO;

import java.util.List;

import Bean.sepcificbook;

public interface SepcificbookDAO {

	public List<sepcificbook> getAllBook(String sql);
	public void search(String bookname);
	public void update(sepcificbook s);
}

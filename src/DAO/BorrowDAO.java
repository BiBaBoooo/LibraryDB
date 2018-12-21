package DAO;

import java.util.List;

import Bean.borrow;

public interface BorrowDAO {

	public List<borrow> searchCurrent(String userid);		//查询当前借阅
	public List<borrow> searchHistory(String userid);		//查询历史借阅
	public boolean insert(borrow borrow);		//用户借书
}

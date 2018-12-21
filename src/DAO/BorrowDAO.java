package DAO;

import java.util.List;

import Bean.BorrowBook;
import Bean.borrow;

public interface BorrowDAO {

	public List<BorrowBook> searchCurrent(String userid);		//查询当前借阅
	public List<BorrowBook> searchHistory(String userid);		//查询历史借阅
	public boolean insert(borrow borrow);		//用户借书
	public boolean updateStatus(String userid,String barcode);		//用户还书,staus变为‘已还’
}

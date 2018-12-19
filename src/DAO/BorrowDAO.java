package DAO;

import java.util.List;

import Bean.borrow;

public interface BorrowDAO {

	public List<borrow> search(String userid);
	public void insert(borrow borrow);
}

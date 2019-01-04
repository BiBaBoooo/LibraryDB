package DAO;

import java.util.List;

import Bean.kindbook;

public interface CategoryDAO {
	public List<kindbook> searchtype(String type);
}

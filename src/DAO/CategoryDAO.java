package DAO;

import java.util.List;

import Bean.kindbook;

public interface CategoryDAO {
	public List<kindbook> searchtype(String type);
	public String searchtypeid(String type) ;
	public boolean inserttype(String type);
	
}

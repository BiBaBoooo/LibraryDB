package DAO;

import java.util.List;

import Bean.collect;

public interface CollectDAO {

	public List<collect> search(String userid);
	public void insert(collect c);
}

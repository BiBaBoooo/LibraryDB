package DAO;

import java.util.List;

import Bean.collect;

public interface CollectDAO {

	public List<collect> search(String userid);
	public boolean insert(collect c);
}

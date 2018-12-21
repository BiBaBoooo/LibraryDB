package DAO;

import java.util.List;

import Bean.KS;
import Bean.collect;

public interface CollectDAO {

	public List<KS> search(String userid);		//查询暂存书架
	public boolean insert(collect c);		//加入暂藏书架
	public boolean delete(String userid,String barcode);	//移除暂藏书架
}

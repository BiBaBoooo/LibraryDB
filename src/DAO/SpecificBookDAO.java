package DAO;

import java.util.List;

import Bean.KS;
import Bean.specificbook;

public interface SpecificBookDAO {

	public List<KS> search(String bookname);	//如果bookname为null,则查询所有的书；若不为null,则之查询对应的书
	public List<specificbook> lookup(String barcode);		//根据条形码查询  
	public boolean updatespecificbook(String place,String  barcode);//更新一本书的位置信息
}

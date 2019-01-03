package DAO;

import java.util.List;

import Bean.KS;
import Bean.specificbook;

public interface SpecificBookDAO {

	public List<KS> search(String bookname);	//如果bookname为null,则查询所有的书；若不为null,则之查询对应的书
	public List<specificbook> lookup(String barcode);		//根据条形码查询  
	public boolean updatespecificbook(String place,String  barcode);//更新一本书的位置信息
	public boolean borrowBook(String barcode);	//借书，修改该书的state属性值为‘已借’
	public boolean give_backBook(String barcode);	//还书，修改该书的state属性值为‘未借’
	//public specificbook searchone(String barcode);
}

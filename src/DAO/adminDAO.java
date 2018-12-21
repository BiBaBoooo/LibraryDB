package DAO;

import java.util.List;

import Bean.kindbook;
import Bean.specificbook;

public interface adminDAO {

	public List<kindbook> selectKindBook(String callnumber); //(����callnumberѡ����)
	public List<specificbook> selectSpecificBook(String barcode);//����barcode��ѯ
}

package DAO;

import java.util.List;

import Bean.kindbook;
import Bean.sepcificbook;

public interface adminDAO {

	public List<kindbook> selectKindBook(String callnumber); //(根据callnumber选出书)
	public List<sepcificbook> selectSepcificBook(String barcode);//根据barcode查询
}

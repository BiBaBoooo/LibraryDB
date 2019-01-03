package DAO;

import java.util.List;

import Bean.kindbook;

public interface KindBookDAO {

	public List<kindbook> searchByC(String callnumber);		//根据索书号查询
	public boolean updateInfo(kindbook k);		//完善书本详细信息
	public boolean updateContent(String content,String callnumber);// ����ͼ����ϸ��Ϣ
	public List<kindbook> searchByname(String name);
}

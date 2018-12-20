package DAO;

import Bean.user;

public interface UserDAO {

	public user search(String userid);
	public user getUser(String userid,String pwd);//���ڵ�½�����������user��userpwd���в��ң�������һ���û���Ϣ
}

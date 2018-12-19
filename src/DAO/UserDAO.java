package DAO;

import Bean.user;

public interface UserDAO {

	public user search(String userid,String pwd);
	public user getUser(String userid,String pwd);//用于登陆（根据输入的user和userpwd进行查找），返回一个用户信息
}

package DAO;

import java.util.List;

import Bean.user;

public interface UserDAO {

	public user search(String userid);
	public user getUser(String userid,String pwd);//用于登陆
}

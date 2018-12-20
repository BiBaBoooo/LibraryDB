package DAO;

import Bean.UserInfo;
import Bean.user;

public interface UserDAO {

	public user search(String userid);
	public user getUserByID(String userid,String pwd); //ID登陆
	public user getUserByEM(String email,String pwd); //email登陆
	public UserInfo queryUser(String userid);		//查询用户详细信息

}

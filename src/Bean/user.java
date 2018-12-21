package Bean;

/*
 * 用户表
 */
public class user {
	private String userid;		//用户ID
	private String email;		//用户邮箱
	private String password;	//密码
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserid() {
		return userid;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
}

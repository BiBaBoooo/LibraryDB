package Bean;

/*
 * 用户表
 */
public class user {
	private String userid;		//用户ID
	private String password;	//密码
	private int maxborrow;		//最大借书量
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setMaxborrow(int maxborrow) {
		this.maxborrow = maxborrow;
	}
	
	public String getUserid() {
		return userid;
	}
	public String getPassword() {
		return password;
	}
	public int getMaxborrow() {
		return maxborrow;
	}

}

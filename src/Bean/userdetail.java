package Bean;

/*
 * 用户详细信息表
 */
public class userdetail {

	private String userid;		//用户ID
	private String name;		//用户姓名
	private int maxborrow;		//最大借书量
	private int borrowcount;	//已借书量
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMaxborrow(int maxborrow) {
		this.maxborrow = maxborrow;
	}
	public void setBorrowcount(int borrowcount) {
		this.borrowcount = borrowcount;
	}
	
	public String getUserid() {
		return userid;
	}
	public String getName() {
		return name;
	}
	public int getMaxborrow() {
		return maxborrow;
	}
	public int getBorrowcount() {
		return borrowcount;
	}
}

package DAO;

public interface UserDetailDAO {

	public boolean addBorrowCount(String userid);	//借书，当前借数量加一
	public boolean subBorrowCount(String userid);	//还书，当前借数量减一
	public boolean checkMaxBorrow(String userid);	//判断当前借数量是否超过最大借书量
}

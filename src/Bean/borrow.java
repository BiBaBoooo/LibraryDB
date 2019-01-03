package Bean;

import java.util.Date;

/*
 * 借阅历史表
 */
public class borrow {
	//
	private String barcode;		//图书条形码
	private String userid;		//用户ID
	private Date borrowdate;	//借书时间
	private Date backdate;		//归还时间
	private String status;		//书本状态（是否归还）
	
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public void setUserid(String useid) {
		this.userid = useid;
	}
	public void setBorrowdate(Date borrowdate) {
		this.borrowdate = borrowdate;
	}
	public void setBackdate(Date backdate) {
		this.backdate = backdate;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getBarcode() {
		return barcode;
	}
	public String getUserid() {
		return userid;
	}
	public Date getBorrowdate() {
		return borrowdate;
	}
	public Date getBackdate() {
		return backdate;
	}
	public String getStatus() {
		return status;
	}
}

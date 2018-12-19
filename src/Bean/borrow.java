package Bean;

import java.util.Date;

public class borrow {
	private String barcode;
	private String useid;
	private Date borrowdate;
	private Date backdate;
	
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public void setUseid(String useid) {
		this.useid = useid;
	}
	public void setBorrowdate(Date borrowdate) {
		this.borrowdate = borrowdate;
	}
	public void setBackdate(Date backdate) {
		this.backdate = backdate;
	}
	
	public String getBarcode() {
		return barcode;
	}
	public String getUseid() {
		return useid;
	}
	public Date getBorrowdate() {
		return borrowdate;
	}
	public Date getBackdate() {
		return backdate;
	}
}

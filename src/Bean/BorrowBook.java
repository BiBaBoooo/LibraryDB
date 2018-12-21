package Bean;

import java.util.Date;

public class BorrowBook {

	private String barcode;
	private String callnumber;
	private String bookname;
	private String author;
	private Date borrowdate;	//借书时间
	private Date backdate;		//归还时间
	private String status;
	
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public void setCallnumber(String callnumber) {
		this.callnumber = callnumber;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public void setAuthor(String author) {
		this.author = author;
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
	public String getCallnumber() {
		return callnumber;
	}
	public String getBookname() {
		return bookname;
	}
	public String getAuthor() {
		return author;
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

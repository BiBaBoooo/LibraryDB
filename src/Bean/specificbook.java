package Bean;

import java.util.Date;

/*
 * 图书馆中所有的书构成的表
 */
public class specificbook {

	private String barcode;		//条形码
	private String callnumber;	//索书号
	private Date buydate;		//购书时间
	private String place;		//藏书位置
	private String state;		//是否被借
	
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public void setCallnumber(String callnumber) {
		this.callnumber = callnumber;
	}
	public void setBuydate(Date buydate) {
		this.buydate = buydate;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getBarcode() {
		return barcode;
	}
	public String getCallnumber() {
		return callnumber;
	}
	public Date getBuydate() {
		return buydate;
	}
	public String getPlace() {
		return place;
	}
	public String getState() {
		return state;
	}
	
}

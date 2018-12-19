package Bean;

import java.util.Date;

/*
 * 用户收藏（暂存）图书表
 */
public class collect {

	private String barcode;		//图书条形码
	private String userid;		//用户ID
	private Date collectdate;	//收藏时间
	
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setCollectdate(Date collectdate) {
		this.collectdate = collectdate;
	}
	
	public String getBarcode() {
		return barcode;
	}
	public String getUserid() {
		return userid;
	}
	public Date getCollectdate() {
		return collectdate;
	}
}

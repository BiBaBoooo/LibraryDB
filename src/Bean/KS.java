package Bean;

import java.util.Date;

/*
 * 书的详细信息Bean
 */
public class KS {

	private String callnumber;	//索书号
	private String bookname;	//书名
	private String author;	//作者
	private int count;	//相同的书在图书馆中的数量
	
	private String barcode;		//条形码
	private String content;		//书本简介
	private String catalog;		//目录
	private String publish;		//出版社
	private String topic;		//学科主题
	private Date buydate;		//购书时间
	private String place;		//藏书位置
	private String state;		//是否被借
	
	
	public String getCallnumber() {
		return callnumber;
	}
	public void setCallnumber(String callnumber) {
		this.callnumber = callnumber;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCatalog() {
		return catalog;
	}
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public Date getBuydate() {
		return buydate;
	}
	public void setBuydate(Date buydate) {
		this.buydate = buydate;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}

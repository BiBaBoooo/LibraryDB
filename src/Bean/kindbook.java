package Bean;

/*
 * 根据索书号（不同的书）构成的表
 */
public class kindbook {

	private String callnumber;	//索书号
	private String bookname;	//书名
	private String author;	//作者
	private int count;	//相同的书在图书馆中的数量
	private String content;		//书本简介
	private String catalog;		//目录
	private String publish;		//出版社
	private String topic;		//学科主题
	
	public void setCallnumber(String callnumber) {
		this.callnumber = callnumber;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public void setTopic(String topic) {
		this.topic = topic;
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
	public int getCount() {
		return count;
	}
	public String getContent() {
		return content;
	}
	public String getCatalog() {
		return catalog;
	}
	public String getPublish() {
		return publish;
	}
	public String getTopic() {
		return topic;
	}
}

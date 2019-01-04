package Bean;

public class kc {
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getcID() {
		return cID;
	}
	public void setcID(String cID) {
		this.cID = cID;
	}
	public String getCallnumber() {
		return callnumber;
	}
	public void setCallnumber(String callnumber) {
		this.callnumber = callnumber;
	}
	private int id;	//相同的书在图书馆中的数量
	private String cID;		//书本简介
	private String callnumber;
}

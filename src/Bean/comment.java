package Bean;

import java.util.Date;

public class comment {
	
	private String callnumber;
	private String userid;
	private String comment;
	private Date time;
	
	public void setCallnumber(String callnumber) {
		this.callnumber = callnumber;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	public String getCallnumber() {
		return callnumber;
	}
	public String getUserid() {
		return userid;
	}
	public String getComment() {
		return comment;
	}
	public Date getTime() {
		return time;
	}
}

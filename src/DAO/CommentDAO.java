package DAO;

import java.util.List;

import Bean.comment;

public interface CommentDAO {
	public boolean addComment(String userid,String callnumber,String comment);
	public List<comment> searchComment(String callnumber);
}

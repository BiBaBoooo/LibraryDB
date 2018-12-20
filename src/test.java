import java.util.List;
import java.util.Scanner;

import Bean.KS;
import Bean.kindbook;
import Bean.user;
import DAO.DAOFactory;

import java.io.*;

public class test {

	public static void main(String[] args) {
		//List<KS> ks=DAOFactory.getSpecificBookDAO().search("ja");
		//for(int i=0;i<ks.size();i++) {
		//	KS k=ks.get(i);
		//	System.out.println(k.getBarcode()+"\t"+k.getBookname());
		//}
		
		Scanner sc=new Scanner(System.in);
		boolean b=true;
		while(b) {
			System.out.println("请选择你的登陆方式：");
			System.out.println("\t1.用户ID登录");
			System.out.println("\t2.email登录");
			System.out.println("\t3.退出");
			int temp=Integer.parseInt(sc.nextLine());
			switch(temp) {
				case 1:{
					System.out.println("请输入你的用户ID：");
					String userid=sc.nextLine();
					System.out.println("请输入你的密码：");
					String pwd=sc.nextLine();
					user u=DAOFactory.getUserDAO().getUserByID(userid, pwd);
					if(u==null) {
						System.out.println("请重新登录");
						break;
					}
					else {
						if(userid.equals("admin")) {
							//进行管理员的相关操作
							admin(u);
						}
						else {
							//普通用户的相关操作
							user0(u);
						}
					}
				}
				case 2:{
					System.out.println("请输入你的email：");
					String username=sc.nextLine();
					System.out.println("请输入你的密码：");
					String pwd=sc.nextLine();
					break;
				}
				case 3:{
					b=false;
					break;
				}
			}
		}
		
	}
	protected static void admin(user u) {
		Scanner sc=new Scanner(System.in);
		boolean b=true;
		while(b) {
			System.out.println("请选择以下功能");
			System.out.println("\t1.完善借阅书籍的详细信息");
			System.out.println("\t2.完善借阅位置相关信息");
			System.out.println("\t3.退出");
			int temp=Integer.parseInt(sc.nextLine());
			switch(temp) {
				case 1:{
					adminUpdateDetail();//更新书籍详细信息
					break;
				}
				case 2:{
					adminUpdateLocation();//完善借阅位置的相关信息
					break;
				}
				case 3:{
					b=false;
					break;
				}
			}
		}
		
		
	}
	protected static void adminUpdateDetail() {//更新书籍详细信息
		//先输出所有的书籍信息
		List<kindbook> kb=DAOFactory.getKindBookDAO().searchByC(null);
		for(int i=0;i<kb.size();i++) {
			kindbook k=kb.get(i);
			System.out.println(k.getCallnumber()+"\t"+k.getBookname()+"\t"+k.getAuthor()+"\t"+k.getContent()+"\t"+k.getCount());
		}
		
		System.out.println("请输入要更新的书籍的索书号:");
		Scanner sc=new Scanner(System.in);
		String callnumber=sc.nextLine();
		List<kindbook> kblist=DAOFactory.getKindBookDAO().searchByC(callnumber);//输出要更新的书籍的信息
		kindbook k0=kblist.get(0);
		boolean b=true;
		while(b) {
			System.out.println(k0.getCallnumber()+"\t"+k0.getBookname()+"\t"+k0.getAuthor()+"\t"+k0.getCount()+"\t"+k0.getContent());
			System.out.println("请选择要更新的内容:");
			System.out.println("\t1.书名");
			System.out.println("\t2。作者");
			System.out.println("\t3.该书在图书馆中的数量");
			System.out.println("\t4.内容");
			System.out.println("\t5.目录");
			System.out.println("\t6.出版社");
			System.out.println("\t7.学科主题");
			System.out.println("\t8.退出");
			int temp=Integer.parseInt(sc.nextLine());
			String s=null;
			if(temp!=8) {
				System.out.println("要修改成：");
				s=sc.nextLine();
			}
			switch(temp) {
				case 1:{
					k0.setBookname(s);
					break;
				}
				case 2:{
					k0.setAuthor(s);
					break;
				}
				case 3:{
					k0.setCount(Integer.parseInt(s));
					break;
				}
				case 4:{
					k0.setContent(s);
					break;
				}
				case 5:{
					
					break;
				}
				case 6:{
					
					break;
				}
				case 7:{
					
					break;
				}
				case 8:{
					b=false;
					break;
				}
			}
			DAOFactory.getKindBookDAO().updateInfo(k0);
		}
	}
	protected static void adminUpdateLocation() {//完善借阅位置的相关信息
		//先输出所有的书籍位置信息
		
	}
	protected static void user0(user u) {
		
	}
}

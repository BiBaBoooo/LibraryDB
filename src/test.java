import java.util.List;
import java.util.Scanner;

import Bean.KS;
import Bean.borrow;
import Bean.kindbook;
import Bean.specificbook;
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
					break;
				}
				case 2:{
					System.out.println("请输入你的email：");
					String email=sc.nextLine();
					System.out.println("请输入你的密码：");
					String pwd=sc.nextLine();
					user u=DAOFactory.getUserDAO().getUserByEM(email, pwd);
					user0(u);	//只有普通用户有邮箱登陆相关操作,管理员没有邮箱
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
			System.out.println("请选择以下功能编号");
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
			System.out.println(k.getCallnumber()+"\t"+k.getBookname()+"\t"+k.getAuthor()+"\t"+k.getContent()+"\t"+k.getCount()
					+"\t"+k.getCatalog()+"\t"+k.getPublish()+"\t"+k.getTopic());
		}
		
		System.out.println("请输入要完善的书籍的索书号:");
		Scanner sc=new Scanner(System.in);
		String callnumber=sc.nextLine();
		List<kindbook> kblist=DAOFactory.getKindBookDAO().searchByC(callnumber);//输出要更新的书籍的信息
		kindbook k0=kblist.get(0);
		boolean b=true;
		while(b) {
			System.out.println();
			System.out.println("索书号:"+k0.getCallnumber());
			System.out.println("书名:"+k0.getBookname());
			System.out.println("作者:"+k0.getAuthor());
			System.out.println("数量:"+k0.getCount());
			System.out.println("简介:"+k0.getContent());
			System.out.println("目录:"+k0.getCatalog());
			System.out.println("出版社:"+k0.getPublish());
			System.out.println("学科主题:"+k0.getTopic());

			System.out.println("请选择要完善的内容编号:");
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
					k0.setCatalog(s);
					break;
				}
				case 6:{
					k0.setPublish(s);
					break;
				}
				case 7:{
					k0.setTopic(s);
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
		List<specificbook> skook=DAOFactory.getSpecificBookDAO().lookup(null);
		System.out.println("\n索书号:\t条形码:\t借阅室:\t书刊状态:");
		for(int i=0;i<skook.size();i++) {
			specificbook s=skook.get(i);
			System.out.println(s.getCallnumber()+"\t"+s.getBarcode()+"\t"+s.getPlace()+"\t"+s.getState());
		}	
		System.out.print("请输入要完善借阅室的书籍的条形码:");
		Scanner sc=new Scanner(System.in);
		String barcode=sc.nextLine();
		System.out.print("请输入借阅室位置:");
		String place=sc.nextLine();
		boolean t=DAOFactory.getSpecificBookDAO().updatespecificbook(place, barcode);
		if(t==true) {
			System.out.println("更新成功");	
			List<specificbook> sb=DAOFactory.getSpecificBookDAO().lookup(barcode);
			specificbook sb0=sb.get(0);
			System.out.println("\n索书号:\t条形码:\t借阅室:\t书刊状态:");
			System.out.println(sb0.getCallnumber()+"\t"+sb0.getBarcode()+"\t"+sb0.getPlace()+"\t"+sb0.getState());
		}
	}
	protected static void user0(user u) {
		Scanner sc=new Scanner(System.in);
		boolean b=true;
		while(b) {
			System.out.println("请选择以下功能编号");
			System.out.println("\t1.查询图书");
			System.out.println("\t2.查看当前借阅");
			System.out.println("\t3.查看借阅历史");
			System.out.println("\t6.查看暂存书架");
			System.out.println("\t5.查看个人信息");
			System.out.println("\t6.退出");
			int temp=Integer.parseInt(sc.nextLine());
			switch(temp) {
				case 1:{
					userSearchBook();	//搜索图书
					break;
				}
				case 2:{
					userCurrentBorrow(u.getUserid());	//当前借阅
					break;
				}
				case 3:{
					userHistoryBorrow(u.getUserid());	//历史借阅
					break;
				}
				case 4:{
					userSearchCollect(u.getUserid());	//暂存书架
					break;
				}
				case 5:{
					userInformation(u.getUserid()); 	//个人信息
					break;
				}
				case 6:{
					b=false;
					break;
				}
			}
		}
	}
	protected static void userSearchBook() {
		System.out.print("请输入你要查询的图书书名");
		
	}
	private static void userSearchCollect(String userid) {
		// TODO Auto-generated method stub
		
	}
	private static void userInformation(String userid) {
		// TODO Auto-generated method stub
		
	}
	private static void userHistoryBorrow(String string) {
		// TODO Auto-generated method stub
		
	}
	private static void userCurrentBorrow(String string) {
		// TODO Auto-generated method stub
		
	}
}

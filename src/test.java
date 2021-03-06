import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import Bean.BorrowBook;
import Bean.Category;
import Bean.KS;
import Bean.UserInfo;
import Bean.borrow;
import Bean.collect;
import Bean.comment;
import Bean.kc;
import Bean.kindbook;
import Bean.specificbook;
import Bean.user;
import DAO.DAO;
import DAO.DAOFactory;


public class test {

	public static void main(String[] args) {
		//List<KS> ks=DAOFactory.getSpecificBookDAO().search("ja");
		//for(int i=0;i<ks.size();i++) {
		//	KS k=ks.get(i);
		//	System.out.println(k.getBarcode()+"\t"+k.getBookname());
		//}
		//
		Scanner sc=new Scanner(System.in);
		boolean b=true;
		while(b) {
			System.out.println("请选择你的登陆方式:");
			System.out.println("\t1.用户ID登录");
			System.out.println("\t2.email登录");
			System.out.println("\t3.退出");
			int temp=Integer.parseInt(sc.nextLine());
			switch(temp) {
				case 1:{
					System.out.println("请输入你的用户ID:");
					String userid=sc.nextLine();
					System.out.println("请输入你的密码:");
					String pwd=sc.nextLine();
					user u=DAOFactory.getUserDAO().getUserByID(userid, pwd);
					if(u==null) {
						System.out.println("请重新登录");
						break;
					}
					else {
						if(userid.equals("admin")) {
							
							admin(u);//进行管理员的相关操作
						}
						else {
							
							user0(u);//普通用户的相关操作
						}
					}
					break;
				}
				case 2:{
					System.out.println("请输入你的email:");
					String email=sc.nextLine();
					System.out.println("请输入你的密码:");
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
		sc.close();
	}
	protected static void admin(user u) {
		Scanner sc=new Scanner(System.in);
		boolean b=true;
		while(b) {
			System.out.println("请输入以下功能编号:");
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
		//sc.close();
	}
	
	protected static void adminUpdateDetail() {//更新书籍详细信息
		allbook();
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
			String ss=DAOFactory.getKCDAO().searchByC(k0.getCallnumber());
			if(ss==null)
				System.out.println("暂未分类");
			else
				System.out.println(ss);
			System.out.println("请选择要完善的内容编号:");
			System.out.println("\t1.书名");
			System.out.println("\t2.作者");
			System.out.println("\t3.该书在图书馆中的数量");
			System.out.println("\t4.内容");
			System.out.println("\t5.目录");
			System.out.println("\t6.出版社");
			System.out.println("\t7.学科主题");
			System.out.println("\t8.类型");
			System.out.println("\t9.退出");
			int temp=Integer.parseInt(sc.nextLine());
			String s=null;
			if(temp!=9) {
				System.out.println("要修改成:");
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
					String string=DAOFactory.getCategoryDAO().searchtypeid(s);
					int t=Integer.valueOf(string);
					boolean bool=DAOFactory.getKCDAO().insertt(t, k0.getCallnumber());
					if(bool)System.out.println("添加类型成功！");
					break;
				}
				case 9:{
					b=false;
					break;
				}
			}
			DAOFactory.getKindBookDAO().updateInfo(k0);//更新图书信息
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
		//sc.close();
	}
	protected static void user0(user u) {
		Scanner sc=new Scanner(System.in);
		boolean b=true;
		while(b) {
			System.out.println("请选择以下功能编号:");
			System.out.println("\t1.查询图书");
			System.out.println("\t2.查看当前借阅");
			System.out.println("\t3.查看借阅历史");
			System.out.println("\t4.查看暂存书架");
			System.out.println("\t5.查看个人信息");
			System.out.println("\t6.退出");
			int temp=Integer.parseInt(sc.nextLine());
			switch(temp) {
				case 1:{
					System.out.println("请选择以下功能编号:");
					System.out.println("\t1.模糊查询图书");
					System.out.println("\t2.按类型查找图书");
					int x=Integer.parseInt(sc.nextLine());
					if(x==1)
					//System.out.println("\t3.查看借阅历史");
						userSearchBook(u.getUserid());	//搜索图书
					else if(x==2) {
						userSearchtype(u.getUserid());
					}
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
	protected static void userSearchBook(String userid) {
		Scanner sc=new Scanner(System.in);
		System.out.print("请输入你要查询的图书书名:");
		String name=sc.nextLine();
		List<KS> k=DAOFactory.getSpecificBookDAO().search(name);
		if(k.size()==0) {
			System.out.println("没有查找到相关书名图书");
		}
		System.out.println("一共找到了"+k.size()+"本书");
		for(int i=0;i<k.size();i++) {
			KS k0=k.get(i);
			System.out.print("索书号:"+k0.getCallnumber()+"  "+"条形码:"+k0.getBarcode()+"  "+"书名:"+k0.getBookname()+"  "+"作者:"+k0.getAuthor()+"  "+"章节:"+k0.getCatalog()+"  "+"内容:"+k0.getContent()+"  "+"出版社:"+k0.getPublish()+"  "+"存放地:"+k0.getPlace()+"  "+"状态:"+k0.getState()+"  "+"类型:");
			String string=DAOFactory.getKCDAO().searchByC(k0.getCallnumber());
			if(string==null)
				System.out.println("暂未分类");
			else
				System.out.println(string);
		}
		printl(userid);
		
		//sc.close();		
	}
	protected static void userSearchtype(String userid) {
		Scanner sc=new Scanner(System.in);
		System.out.print("请输入你要查询的类型:");
		String type=sc.nextLine();
		List<kindbook>kindbooks=DAOFactory.getCategoryDAO().searchtype(type);
		for(int i=0;i<kindbooks.size();i++) {
			kindbook kindbook=kindbooks.get(i);
			System.out.println("索书号:"+kindbook.getCallnumber()+"  "+"书名:"+kindbook.getBookname()+"  "+"作者:"+kindbook.getAuthor()+"  "+"章节:"+kindbook.getCatalog()+"  "+"内容:"+kindbook.getContent()+"  "+"出版社:"+kindbook.getPublish());
			List<KS>specificbooks=DAOFactory.getSpecificBookDAO().search(kindbook.getBookname());
			for(int j=0;j<specificbooks.size();j++) {
				KS ks=specificbooks.get(j);
				System.out.println("   				   条形码"+ks.getBarcode()+"       位置："+ks.getPlace());
			}
		}
		if(kindbooks.size()==0)
		{
			System.out.println("查询无结果");
			return ;
		}			
		else
		printl(userid);
	}
	private static void collectBook(String userid, String barcode) {
		collect c=new collect();
		c.setUserid(userid);
		c.setBarcode(barcode);
		Calendar calendar=Calendar.getInstance();//获取时间
		Date collectdate=calendar.getTime();
		c.setCollectdate(collectdate);
		
		boolean b=DAOFactory.getCollectDAO().insert(c);//插入借书记录
		if(b==true)
			System.out.println("加入成功");
		else 
			System.out.println("加入失败");
	}
	private static void borrowBook(String userid,String barcode) {
		borrow b=new borrow();
		b.setBarcode(barcode);
		b.setUserid(userid);
		
		Calendar calendar=Calendar.getInstance();
		Date borrowdate=calendar.getTime();
		b.setBorrowdate(borrowdate);
		b.setStatus("已借");
		boolean temp=DAOFactory.getUserDetailDAO().checkMaxBorrow(userid);
		if(temp==true) {
			boolean t1=DAOFactory.getBorrowDAO().insert(b);
			boolean t2=DAOFactory.getSpecificBookDAO().borrowBook(barcode);//改变书本的状态‘已借’
			boolean t3=DAOFactory.getUserDetailDAO().addBorrowCount(userid);//当前借书量++
			if(t1==true&&t2==true&&t3==true)
				System.out.println("借书成功");
			else
				System.out.println("借书失败");
		}
		else 
			System.out.println("您的借书量已到达最大值");
	}
	private static void userSearchCollect(String userid) {
		List<KS> collects=DAOFactory.getCollectDAO().search(userid);
		System.out.println("您的暂藏书架一共有"+collects.size()+"本书");
		for(int i=0;i<collects.size();i++) {
			KS collect=collects.get(i);
			System.out.print("索书号:"+collect.getCallnumber()+"  "+"条形码:"+collect.getBarcode()+"  "+"书名:"+collect.getBookname()+"  "+"作者:"+collect.getAuthor()+"  "+"借阅室"+collect.getPlace()+"  "+"书刊状态:"+collect.getState());
			String string=DAOFactory.getKCDAO().searchByC(collect.getCallnumber());
			if(string==null)
				System.out.println("图书类型 :暂无分类");
			else 
				System.out.println("图书类型:"+string);		
		}
		print2(userid);
	}
	private static void userInformation(String userid) {
		UserInfo user=DAOFactory.getUserDAO().queryUser(userid);//查找用户
		user u=DAOFactory.getUserDAO().search(userid);
	   //System.out.println(u.getEmail());
	    System.out.println(u.getUserid());
		System.out.println("用户ID:"+u.getUserid());
		System.out.println("邮箱:"+u.getEmail());
		System.out.println("姓名:"+user.getName());
		System.out.println("已借书量:"+user.getBorrowcount());
		System.out.println("最大借书量:"+user.getMaxborrow());
		
		Scanner sc=new Scanner(System.in);
		boolean b=true;
		while(b) {
			System.out.println("请输入以下功能编号:");
			System.out.println("\t1.添加|修改邮箱");
			System.out.println("\t2.返回上级选择");
			int temp=Integer.parseInt(sc.nextLine());
			switch(temp) {
				case 1:{
					String email=sc.nextLine();
					boolean t=DAOFactory.getUserDAO().updateEmail(userid, email);//添加Email
					if(t==true)
						System.out.println("更新成功");
					break;
				}
				case 2:{
					return;
				}
			}
		}
	}
	private static void userHistoryBorrow(String userid) {
		List<BorrowBook> books=DAOFactory.getBorrowDAO().searchHistory(userid);//查询历史纪录
		System.out.println("您看过"+books.size()+"本书");
		for(int i=0;i<books.size();i++) {
			BorrowBook book=books.get(0);
			System.out.println("索书号:"+book.getCallnumber());
			System.out.println("条形码:"+book.getBarcode());
			System.out.println("书名:"+book.getBookname());
			System.out.println("作者:"+book.getAuthor());
			System.out.println("借书时间:"+book.getBorrowdate());
			System.out.println("归还时间:"+book.getBackdate());
			System.out.println("书刊状态:"+book.getStatus());
			
			Scanner sc=new Scanner(System.in);
			boolean b=true;
			while(b) {
				System.out.println("请选择以下功能:");
				System.out.println("\t1.查看下一本书");
				System.out.println("\t2.返回上级选择");
				int temp=Integer.parseInt(sc.nextLine());
				switch(temp) {
					case 1:{
						b=false;
						break;
					}
					case 2:{
						return;
					}
				}
			}
		}
	}
	private static void userCurrentBorrow(String userid) {
		List<BorrowBook> books=DAOFactory.getBorrowDAO().searchCurrent(userid);//查询当前记录
		System.out.println("当前借阅"+books.size()+"本书");
		for(int i=0;i<books.size();i++) {
			BorrowBook book=books.get(0);
			System.out.print("索书号:"+book.getCallnumber());
			System.out.print("  条形码:"+book.getBarcode());
			System.out.print("  书名:"+book.getBookname());
			System.out.print("  作者:"+book.getAuthor());
			System.out.print("  借书时间:"+book.getBorrowdate());
			System.out.print("  书刊状态:"+book.getStatus()+"  ");
			String string=DAOFactory.getKCDAO().searchByC(book.getCallnumber());
			if(string==null)
				System.out.println("图书类型 :暂无分类");
			else 
				System.out.println("图书类型:"+string);	
			print3(userid);
		}
	}
	
	private static boolean addCommt(String userid,String callnumber){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入您的评价:");
		String comment=sc.nextLine();
		boolean t=DAOFactory.getCommentDAO().addComment(userid, callnumber, comment);//添加评论
		return t;
	}
	
	private static void searchCommt(String callnumber) {
		List<comment> cList=null;
		cList=DAOFactory.getCommentDAO().searchComment(callnumber);//查找评论
		if(cList.size()==0)
			System.out.println("暂时没有评论信息");
		for(int i=0;i<cList.size();i++) {
			comment c=cList.get(i);
			System.out.println(c.getUserid()+":"+c.getComment()+"\t"+c.getTime());
		}
	}
	private static void  allbook() {
		System.out.println("索书号           书名                                    作者               内容               数量              目录              出版社           主题           类型");
		//先输出所有的书籍信息
		List<kindbook> kb=DAOFactory.getKindBookDAO().searchByC(null);
		for(int i=0;i<kb.size();i++) {
			kindbook k=kb.get(i);
			System.out.print(k.getCallnumber()+"\t"+k.getBookname()+"\t"+k.getAuthor()+"\t"+k.getContent()+"\t"+k.getCount()
					+"\t"+k.getCatalog()+"\t"+k.getPublish()+"\t"+k.getTopic()+"\t");
			String type=DAOFactory.getKCDAO().searchByC(k.getCallnumber());
			if(type==null)
			{
				System.out.println("暂无分类");
			}
			else
				System.out.println(type);
		}
	}
	private static void printl(String userid) {
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入您想操作书本的条形码:");
		String ssh=sc.nextLine();
		List<specificbook> kk=DAOFactory.getSpecificBookDAO().lookup(ssh);//查找图书
		if(kk.size()==0) {
			System.out.println("条形码输入错误");
			return;
		}	
		else {
			boolean b=true;
			while(b) {
				
				specificbook k0=DAOFactory.getSpecificBookDAO().lookup(ssh).get(0);
				System.out.println("请选择以下功能:");
				System.out.println("\t1.借阅该书刊");
				System.out.println("\t2.加入暂存书架");
				System.out.println("\t3.评价");
				System.out.println("\t4.查看评论");
				System.out.println("\t5.返回上级选择");
				int temp=Integer.parseInt(sc.nextLine());
				switch(temp) {
					case 1:{
						if(k0.getState().equals("已借")) {
							System.out.println("不可借阅");
						}
						else { 
							borrowBook(userid,k0.getBarcode());
						}
						break;
					}
					case 2:{
						collectBook(userid,k0.getBarcode());
						break;
					}
					case 3:{
						if(addCommt(userid,k0.getCallnumber())) {
							System.out.println("评价成功");
						}
						break;
					}
					case 4:{
						searchCommt(k0.getCallnumber());
						break;
					}
					case 5:{
						return;
					}
				}
			}
		}
	}
	private static void print2(String bookid) {
		Scanner sc=new Scanner(System.in);
		System.out.println("\t请输入您想操作书本的条形码:");
		String ssh=sc.nextLine();
		List<specificbook> kk=DAOFactory.getSpecificBookDAO().lookup(ssh);//查找图书
		if(kk.size()==0) {
			System.out.println("条形码输入错误");
			return;
		}	
		else {
			boolean b=true;
			while(b) {
				
				specificbook k0=DAOFactory.getSpecificBookDAO().lookup(ssh).get(0);
				System.out.println("请选择以下功能:");
				System.out.println("\t1.借阅该书刊");
				System.out.println("\t2.将该书刊移除暂藏书架");
				System.out.println("\t3.返回上级选择");
				int temp=Integer.parseInt(sc.nextLine());
				switch(temp) {
					case 1:{
						if(k0.getState().equals("已借")) {
							System.out.println("不可借阅");
						}
						else { //可借阅
							borrowBook(bookid,k0.getBarcode());
						}
						break;
					}	
					case 2:{
						boolean t=DAOFactory.getCollectDAO().delete(bookid, k0.getBarcode());
						if(t==true)
							System.out.println("移除成功");
						else 
							System.out.println("移除失败");
						break;
					}
					case 3:{
						return;
					}
				}
			}
		}
	}
	private static void print3(String userid) {
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入您想操作书本的条形码:");
		String ssh=sc.nextLine();
		List<specificbook> kk=DAOFactory.getSpecificBookDAO().lookup(ssh);//查找图书
		if(kk.size()==0) {
			System.out.println("条形码输入错误");
			return;
		}	
		else {
			boolean b=true;
			while(b) {
				
				specificbook k0=DAOFactory.getSpecificBookDAO().lookup(ssh).get(0);
				System.out.println("请选择以下功能:");
				System.out.println("\t1.归还该书刊");
				System.out.println("\t2.查看下一本书");
				System.out.println("\t3.返回上级选择");
				int temp=Integer.parseInt(sc.nextLine());
				switch(temp) {
					case 1:{
						boolean b1=DAOFactory.getBorrowDAO().updateStatus(userid, k0.getBarcode());//修改图书状态
						boolean b2=DAOFactory.getSpecificBookDAO().give_backBook(k0.getBarcode());
						boolean b3=DAOFactory.getUserDetailDAO().subBorrowCount(userid);
						if(b1==true&&b2==true&&b3==true)
							System.out.println("还书成功");
						else
							System.out.println("还书失败");
						break;
					}
					case 2:{
						b=false;
						break;
					}
					case 3:{
						return;
					}
				}
			}
		}
	}
}

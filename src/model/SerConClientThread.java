/*
 * 服务器与某个客户端的通讯线程
 */

package model;

import java.net.*;
import java.sql.ResultSet;

import common.*;
import db.SqlHelper;

import java.io.*;

public class SerConClientThread extends Thread{
	
	Socket s;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	
	public SerConClientThread(Socket s)
	{
		this.s=s;//把服务器和该客户端的连接赋给s
	}
	public void close()
	{
		try {
			if(this.s.isClosed()==false)s.close();
			this.ois.close();
			this.oos.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void run()
	{
		while(true)
		{
			//该线程接收客户端信息
			try {
				this.ois=new ObjectInputStream(this.s.getInputStream());			
//				Message m=(Message)ois.readObject();
//				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
//				System.out.println("服务器收到客户端发来的报");
				if(m.getMesType()==4)//普通报
				{
					System.out.println(m.getSender()+" 给 "+m.getGetter()+" 说： "+m.getCon());
					SerConClientThread sc=ManagerClientThread.getThread(m.getGetter());//得到通讯接收人的线程
					this.oos=new ObjectOutputStream(sc.s.getOutputStream());//接上通讯接收人的线程
					this.oos.writeObject(m);//转发
				}else if(m.getMesType()==5){//登录报
//					System.out.println("服务器收到客户端发来的查询登录请求");
					SqlHelper sh=new SqlHelper();
					ResultSet rs=null;
					Message m1=new Message();
					m1.setMesType(5);
					String sql="select id from login where estate=? order by id";
					String []paras={"2"};
					rs=sh.query(sql, paras);
					while(rs.next())
					{
//						System.out.println(rs.getString(1).trim());
						m1.setCon(rs.getString(1).trim());
					}
					this.oos=new ObjectOutputStream(this.s.getOutputStream());//接上自己的线程
					this.oos.writeObject(m1);//发送
//					System.out.println("反馈登录请求");
				}else if(m.getMesType()==6){
					MyServerUser muser=new MyServerUser();
					muser.changeestate(m.getSender(), 1);
					System.out.println("收到"+m.getSender()+"的下线报");
					this.close();
					break;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

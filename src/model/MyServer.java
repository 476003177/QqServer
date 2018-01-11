/*
 * 服务器，监听，等待某个QQ客户端来连接
 */
package model;

import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.util.*;
import common.*;
import db.*;

public class MyServer {
	
	ServerSocket ss;
	Socket s;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	SqlHelper sh;
	MyServerUser muser;
	String sql;
	String []paras;
	ResultSet rs=null;
	
	public void close()
	{
		try {
			if(ss.isClosed()==false)ss.close();
			if(s.isClosed()==false)s.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public MyServer()
	{
		try {
			this.ss=new ServerSocket(9999);//监听
//			System.out.println("正在监听");
			Message m=new Message();
			sh=new SqlHelper();		
			while(true)
			{
				this.s=ss.accept();//阻塞，等待连接
//				BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));//接收客户端发来的信息
//				String info=br.readLine();
				this.ois=new ObjectInputStream(s.getInputStream());//接收从客户端发来的信息
				oos=new ObjectOutputStream(s.getOutputStream());//向s写入对象流
//				Message m1=(Message)ois.readObject();
//				if(m1.getMesType()!=5)continue;
//				User u=(User)m1;
				User u=(User)ois.readObject();
				String id=u.getid();
				String pass=u.getPass();
				System.out.println("服务器接收到 id："+id+"    密码："+pass);
				String sql="select estate from login where id=? and pass=?";
				String []paras={id,pass};
				rs=sh.query(sql, paras);
				if(rs.next())
				{
					int a=rs.getInt("estate");
					if(a==1)
					{
						m.setMesType(1);//正常，允许进入
						oos.writeObject(m);//发送信息报
						muser=new MyServerUser();
						muser.changeestate(id, 2);//设置在线状态
						SerConClientThread sclt=new SerConClientThread(s);//单开一个线程与客户端保持通讯
						ManagerClientThread.addClientThread(id, sclt);
						sclt.start();//启动线程
					}else if(a==0)
					{
						m.setMesType(3);//账号异常
						oos.writeObject(m);//发送信息报
						s.close();
					}
				}else{
					m.setMesType(2);//账号/密码错误
					oos.writeObject(m);//发送信息报
					s.close();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{		
				this.close();
		}
	}
}

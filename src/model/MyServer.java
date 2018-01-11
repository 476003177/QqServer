/*
 * ���������������ȴ�ĳ��QQ�ͻ���������
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
			this.ss=new ServerSocket(9999);//����
//			System.out.println("���ڼ���");
			Message m=new Message();
			sh=new SqlHelper();		
			while(true)
			{
				this.s=ss.accept();//�������ȴ�����
//				BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));//���տͻ��˷�������Ϣ
//				String info=br.readLine();
				this.ois=new ObjectInputStream(s.getInputStream());//���մӿͻ��˷�������Ϣ
				oos=new ObjectOutputStream(s.getOutputStream());//��sд�������
//				Message m1=(Message)ois.readObject();
//				if(m1.getMesType()!=5)continue;
//				User u=(User)m1;
				User u=(User)ois.readObject();
				String id=u.getid();
				String pass=u.getPass();
				System.out.println("���������յ� id��"+id+"    ���룺"+pass);
				String sql="select estate from login where id=? and pass=?";
				String []paras={id,pass};
				rs=sh.query(sql, paras);
				if(rs.next())
				{
					int a=rs.getInt("estate");
					if(a==1)
					{
						m.setMesType(1);//�������������
						oos.writeObject(m);//������Ϣ��
						muser=new MyServerUser();
						muser.changeestate(id, 2);//��������״̬
						SerConClientThread sclt=new SerConClientThread(s);//����һ���߳���ͻ��˱���ͨѶ
						ManagerClientThread.addClientThread(id, sclt);
						sclt.start();//�����߳�
					}else if(a==0)
					{
						m.setMesType(3);//�˺��쳣
						oos.writeObject(m);//������Ϣ��
						s.close();
					}
				}else{
					m.setMesType(2);//�˺�/�������
					oos.writeObject(m);//������Ϣ��
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

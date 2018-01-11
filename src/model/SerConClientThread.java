/*
 * ��������ĳ���ͻ��˵�ͨѶ�߳�
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
		this.s=s;//�ѷ������͸ÿͻ��˵����Ӹ���s
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
			//���߳̽��տͻ�����Ϣ
			try {
				this.ois=new ObjectInputStream(this.s.getInputStream());			
//				Message m=(Message)ois.readObject();
//				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
//				System.out.println("�������յ��ͻ��˷����ı�");
				if(m.getMesType()==4)//��ͨ��
				{
					System.out.println(m.getSender()+" �� "+m.getGetter()+" ˵�� "+m.getCon());
					SerConClientThread sc=ManagerClientThread.getThread(m.getGetter());//�õ�ͨѶ�����˵��߳�
					this.oos=new ObjectOutputStream(sc.s.getOutputStream());//����ͨѶ�����˵��߳�
					this.oos.writeObject(m);//ת��
				}else if(m.getMesType()==5){//��¼��
//					System.out.println("�������յ��ͻ��˷����Ĳ�ѯ��¼����");
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
					this.oos=new ObjectOutputStream(this.s.getOutputStream());//�����Լ����߳�
					this.oos.writeObject(m1);//����
//					System.out.println("������¼����");
				}else if(m.getMesType()==6){
					MyServerUser muser=new MyServerUser();
					muser.changeestate(m.getSender(), 1);
					System.out.println("�յ�"+m.getSender()+"�����߱�");
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

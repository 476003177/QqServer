package common;

import java.util.*;

public class Message implements java.io.Serializable{
	
	private int mesType;//1�ɹ���2ʧ�ܣ�3�쳣��4��ͨ����5Ϊ��¼����6Ϊ���߱�
	private String Sender;//������
	private String getter;//������
	private Vector con=new Vector<String>();//��Ϣ���ݱ���
	private String time;//����ʱ��
	
	public int getMesType() {
		return mesType;
	}
	public void setMesType(int mesType) {
		this.mesType = mesType;
	}
	public String getSender() {
		return Sender;
	}
	public void setSender(String sender) {
		Sender = sender;
	}
	public String getGetter() {
		return getter;
	}
	public void setGetter(String getter) {
		this.getter = getter;
	}
	public String getCon() {
		return (String) this.con.get(0);
	}
	public Vector getConVt() {
		return (Vector) this.con;
	}
	public void setCon(String con) {
		this.con.add(con);
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}

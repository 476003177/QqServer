/*
 * �������˵Ŀ��ƽ��棬�����������������رշ�����������ͼ���û�
 */
package view;

import javax.swing.*;

import model.*;
import java.awt.*;
import java.awt.event.*;

public class MyServerFrame extends JFrame implements ActionListener{

	JPanel jp1;
	JButton jb1,jb2;
	MyServerUser muser;
	MyServer myserver;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyServerFrame mysf=new MyServerFrame();
		
	}
	public MyServerFrame()
	{
		jp1=new JPanel();
		jb1=new JButton("����������");
		jb1.addActionListener(this);
		jb2=new JButton("�رշ�����");
		jb2.addActionListener(this);
		jp1.add(jb1);
		jp1.add(jb2);
		
		this.add(jp1,"North");
		
		this.setTitle("��������"); // ���ô������
		this.setIconImage((new ImageIcon("images/qq.gif")).getImage());// ����ͼ��
		this.setSize(500, 400); // ������Ϊ��λ���ô��峤��
		this.setLocation(200, 200); // ���ó�ʼ����λ��
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���ùرմ��ڼ��˳�
		this.setResizable(true); // �����û��ı䴰���С
		this.setVisible(true); // ��ʾ
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1)
		{
			muser=new MyServerUser();
			muser.recoveryestate();
			myserver=new MyServer();
		}else if(e.getSource()==jb2)
		{
			//�رշ�����
		}
	}
}

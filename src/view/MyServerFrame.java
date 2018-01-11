/*
 * 服务器端的控制界面，可以启动服务器、关闭服务器、管理和监控用户
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
		jb1=new JButton("启动服务器");
		jb1.addActionListener(this);
		jb2=new JButton("关闭服务器");
		jb2.addActionListener(this);
		jp1.add(jb1);
		jp1.add(jb2);
		
		this.add(jp1,"North");
		
		this.setTitle("服务器端"); // 设置窗体标题
		this.setIconImage((new ImageIcon("images/qq.gif")).getImage());// 设置图标
		this.setSize(500, 400); // 以像素为单位设置窗体长宽
		this.setLocation(200, 200); // 设置初始横纵位置
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置关闭窗口即退出
		this.setResizable(true); // 允许用户改变窗体大小
		this.setVisible(true); // 显示
		
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
			//关闭服务器
		}
	}
}

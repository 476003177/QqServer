package model;

import db.*;

public class MyServerUser {
	
	SqlHelper sh;
	
	public void changeestate(String id,int estate)//�ı�ĳ��id״̬
	{
		sh=new SqlHelper();
		String sql="update login set estate=? where id=?";
		String paras[]={estate+"",id};
		sh.updExectue(sql, paras);
	}
	
	public void recoveryestate()//ȫ������״̬�ָ�
	{
		sh=new SqlHelper();
		String sql="update login set estate=? where estate=2";
		String paras[]={"1"};
		sh.updExectue(sql, paras);
	}
}

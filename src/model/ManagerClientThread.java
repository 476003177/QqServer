package model;

import java.util.*;

public class ManagerClientThread {
	
	public static HashMap hm=new HashMap<String, SerConClientThread>(); //���̴߳����hashmap�����String��ʶ���ҿɵ�֪˭����
	//����߳�
	public static void addClientThread(String id,SerConClientThread ct)
	{
		hm.put(id, ct);
	}
	public static SerConClientThread getThread(String id)
	{
		return (SerConClientThread)hm.get(id);
	}
	
}

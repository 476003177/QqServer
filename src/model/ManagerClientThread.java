package model;

import java.util.*;

public class ManagerClientThread {
	
	public static HashMap hm=new HashMap<String, SerConClientThread>(); //将线程存放在hashmap里，并用String标识，且可得知谁在线
	//添加线程
	public static void addClientThread(String id,SerConClientThread ct)
	{
		hm.put(id, ct);
	}
	public static SerConClientThread getThread(String id)
	{
		return (SerConClientThread)hm.get(id);
	}
	
}

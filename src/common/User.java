package common;

public class User  extends Message implements java.io.Serializable{//ʵ�ֽӿڣ����л�����һ����������������ϴ���
	
	private String id;
	private String pass;
	
	public User()
	{
		this.setMesType(5);
	}
	public String getid() {
		return id;
	}
	public void setid(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
}

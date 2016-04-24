package cn.edu.hebiace.javajpk.model;

public class User {
	private int user_id;// id
	private String user_name;// 用户名
	private String user_pwd;// 用户密码

	public User(int user_id, String user_name, String user_pwd) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_pwd = user_pwd;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	@Override
	public String toString() {
		return   " "+ user_name  ;
				
	}
	

}
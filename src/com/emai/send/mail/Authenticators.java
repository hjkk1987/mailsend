package com.emai.send.mail;

import javax.mail.*;

public class Authenticators extends Authenticator {
	String userName = null;
	String password = null;

	public Authenticators() {
	}

	/*********************
	 * 
	 * @param username   用户名
	 * @param password    密码
	 */
	public Authenticators(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	// 新建用户名和密码的存储库
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}
}

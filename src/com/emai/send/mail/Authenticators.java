package com.emai.send.mail;

import javax.mail.*;

public class Authenticators extends Authenticator {
	String userName = null;
	String password = null;

	public Authenticators() {
	}

	/*********************
	 * 
	 * @param username   �û���
	 * @param password    ����
	 */
	public Authenticators(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	// �½��û���������Ĵ洢��
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}
}

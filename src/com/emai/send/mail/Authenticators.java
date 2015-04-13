package com.emai.send.mail;

import javax.mail.*;

public class Authenticators extends Authenticator {
	String userName = null;
	String password = null;

	public Authenticators() {
	}

	public Authenticators(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}
}

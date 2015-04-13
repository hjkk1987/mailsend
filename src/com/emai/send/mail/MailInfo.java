package com.emai.send.mail;

import java.util.Properties;

public class MailInfo {
	public String _mail_user_account = "";// 用户名
	public String _mail_user_passwd = "";// 密码
	public String _mail_service_port = "";// 服务器端口号
	public String _mail_service_IP = "";// 服务器IP地址
	public String _mail_sender_address = "";// 发送人地址
	public String _mail_receiver_address = "";// 接收者地址
	public String _mail_content_txt = "";// 数据发送内容
	public String _mail_subject_txt = ""; // 主题内容
	public String[] _mail_file_path;// 邮件附件的路径
	public boolean isValidate = false;// 是否需要验证

	public Properties getProperties() {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", _mail_service_IP);
		properties.put("mail.smtp.port", _mail_service_port);
		properties.put("mail.smtp.auth", isValidate ? "true" : "false");
		return properties;
	}

}

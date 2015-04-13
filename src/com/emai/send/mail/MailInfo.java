package com.emai.send.mail;

import java.util.Properties;

public class MailInfo {
	public String _mail_user_account = "";// �û���
	public String _mail_user_passwd = "";// ����
	public String _mail_service_port = "";// �������˿ں�
	public String _mail_service_IP = "";// ������IP��ַ
	public String _mail_sender_address = "";// �����˵�ַ
	public String _mail_receiver_address = "";// �����ߵ�ַ
	public String _mail_content_txt = "";// ���ݷ�������
	public String _mail_subject_txt = ""; // ��������
	public String[] _mail_file_path;// �ʼ�������·��
	public boolean isValidate = false;// �Ƿ���Ҫ��֤

	public Properties getProperties() {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", _mail_service_IP);
		properties.put("mail.smtp.port", _mail_service_port);
		properties.put("mail.smtp.auth", isValidate ? "true" : "false");
		return properties;
	}

}

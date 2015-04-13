package com.emai.send.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class MailSender {
	/**
	 * ���ı���ʽ�����ʼ�
	 * 
	 * @param mailInfo
	 *            �����͵��ʼ�����Ϣ
	 */
	public boolean sendTextMail(MultiMailSenderInfo mailInfo) {
		// �ж��Ƿ���Ҫ�����֤
		Authenticators authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate) {
			// �����Ҫ�����֤���򴴽�һ��������֤��
			authenticator = new Authenticators(mailInfo._mail_user_account,
					mailInfo._mail_user_passwd);
		}
		// �����ʼ��Ự���Ժ�������֤������һ�������ʼ���session
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		try {
			// ����session����һ���ʼ���Ϣ
			Message mailMessage = new MimeMessage(sendMailSession);
			// �����ʼ������ߵ�ַ
			Address from = new InternetAddress(mailInfo._mail_sender_address);
			// �����ʼ���Ϣ�ķ�����
			mailMessage.setFrom(from);
			// �����ʼ��Ľ����ߵ�ַ�������õ��ʼ���Ϣ��
			Address[] tos = null;
			String[] receivers = mailInfo.getReceivers();
			if (receivers != null) {
				// Ϊÿ���ʼ������ߴ���һ����ַ
				tos = new InternetAddress[receivers.length + 1];
				tos[0] = new InternetAddress(mailInfo._mail_receiver_address);
				for (int i = 0; i < receivers.length; i++) {
					tos[i + 1] = new InternetAddress(receivers[i]);
				}
			} else {
				tos = new InternetAddress[1];
				tos[0] = new InternetAddress(mailInfo._mail_receiver_address);
			}

			// Message.RecipientType.TO���Ա�ʾ�����ߵ�����ΪTO
			mailMessage.setRecipients(Message.RecipientType.TO, tos);
			// �����ʼ���Ϣ������
			mailMessage.setSubject(mailInfo._mail_subject_txt);
			// �����ʼ���Ϣ���͵�ʱ��
			mailMessage.setSentDate(new Date());
			// �����ʼ���Ϣ����Ҫ����
			String mailContent = mailInfo._mail_content_txt;
			mailMessage.setText(mailContent);
			// �����ʼ�
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * �����ʼ������������,��Html����
	 * 
	 * @param mailInfo
	 *            �������ʼ�����Ϣ
	 * @return
	 */
	public static boolean sendMailtoMultiReceiver(MultiMailSenderInfo mailInfo) {
		Authenticators authenticator = null;
		if (mailInfo.isValidate) {
			authenticator = new Authenticators(mailInfo._mail_user_account,
					mailInfo._mail_user_passwd);
		}
		Session sendMailSession = Session.getInstance(mailInfo.getProperties(),
				authenticator);
		try {
			Message mailMessage = new MimeMessage(sendMailSession);
			// �����ʼ������ߵ�ַ
			Address from = new InternetAddress(mailInfo._mail_sender_address);
			mailMessage.setFrom(from);
			// �����ʼ��Ľ����ߵ�ַ�������õ��ʼ���Ϣ��
			Address[] tos = null;
			String[] receivers = mailInfo.getReceivers();
			if (receivers != null) {
				// Ϊÿ���ʼ������ߴ���һ����ַ
				tos = new InternetAddress[receivers.length + 1];
				tos[0] = new InternetAddress(mailInfo._mail_receiver_address);
				for (int i = 0; i < receivers.length; i++) {
					tos[i + 1] = new InternetAddress(receivers[i]);
				}
			} else {
				tos = new InternetAddress[1];
				tos[0] = new InternetAddress(mailInfo._mail_receiver_address);
			}
			// �����н����ߵ�ַ����ӵ��ʼ�������������
			mailMessage.setRecipients(Message.RecipientType.TO, tos);

			mailMessage.setSubject(mailInfo._mail_subject_txt);
			mailMessage.setSentDate(new Date());
			// �����ʼ�����
			Multipart mainPart = new MimeMultipart();
			BodyPart html = new MimeBodyPart();
			html.setContent(mailInfo._mail_content_txt,
					"text/html; charset=GBK");
			mainPart.addBodyPart(html);
			mailMessage.setContent(mainPart);
			// �����ʼ�
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * ���ʹ����͵��ʼ�
	 * 
	 * @param mailInfo
	 *            �������ʼ�����Ϣ
	 * @return
	 */
	public static boolean sendMailtoMultiCC(MultiMailSenderInfo mailInfo) {
		Authenticators authenticator = null;
		if (mailInfo.isValidate) {
			authenticator = new Authenticators(mailInfo._mail_user_account,
					mailInfo._mail_user_passwd);
		}
		Session sendMailSession = Session.getInstance(mailInfo.getProperties(),
				authenticator);
		try {
			Message mailMessage = new MimeMessage(sendMailSession);
			// �����ʼ������ߵ�ַ
			Address from = new InternetAddress(mailInfo._mail_sender_address);
			mailMessage.setFrom(from);
			// �����ʼ��Ľ����ߵ�ַ�������õ��ʼ���Ϣ��
			Address to = new InternetAddress(mailInfo._mail_receiver_address);
			mailMessage.setRecipient(Message.RecipientType.TO, to);

			// ��ȡ��������Ϣ
			String[] ccs = mailInfo.getCcs();
			if (ccs != null) {
				// Ϊÿ���ʼ������ߴ���һ����ַ
				Address[] ccAdresses = new InternetAddress[ccs.length];
				for (int i = 0; i < ccs.length; i++) {
					ccAdresses[i] = new InternetAddress(ccs[i]);
				}
				// ����������Ϣ���õ��ʼ���Ϣ�У�ע������ΪMessage.RecipientType.CC
				mailMessage.setRecipients(Message.RecipientType.CC, ccAdresses);
			}

			mailMessage.setSubject(mailInfo._mail_subject_txt);
			mailMessage.setSentDate(new Date());
			// �����ʼ�����
			Multipart mainPart = new MimeMultipart();
			BodyPart html = new MimeBodyPart();
			html.setContent(mailInfo._mail_content_txt,
					"text/html; charset=GBK");
			mainPart.addBodyPart(html);
			mailMessage.setContent(mainPart);
			// �����ʼ�
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * ���Ͷ�����������ʼ��Ļ�����Ϣ
	 */
	public static class MultiMailSenderInfo extends MailInfo {
		// �ʼ��Ľ����ߣ������ж��
		private String[] receivers;
		// �ʼ��ĳ����ߣ������ж��
		private String[] ccs;

		public String[] getCcs() {
			return ccs;
		}

		public void setCcs(String[] ccs) {
			this.ccs = ccs;
		}

		public String[] getReceivers() {
			return receivers;
		}

		public void setReceivers(String[] receivers) {
			this.receivers = receivers;
		}
	}
}

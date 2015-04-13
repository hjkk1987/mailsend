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
	 * 以文本格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件的信息
	 */
	public boolean sendTextMail(MultiMailSenderInfo mailInfo) {
		// 判断是否需要身份认证
		Authenticators authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate) {
			// 如果需要身份认证，则创建一个密码验证器
			authenticator = new Authenticators(mailInfo._mail_user_account,
					mailInfo._mail_user_passwd);
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo._mail_sender_address);
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address[] tos = null;
			String[] receivers = mailInfo.getReceivers();
			if (receivers != null) {
				// 为每个邮件接收者创建一个地址
				tos = new InternetAddress[receivers.length + 1];
				tos[0] = new InternetAddress(mailInfo._mail_receiver_address);
				for (int i = 0; i < receivers.length; i++) {
					tos[i + 1] = new InternetAddress(receivers[i]);
				}
			} else {
				tos = new InternetAddress[1];
				tos[0] = new InternetAddress(mailInfo._mail_receiver_address);
			}

			// Message.RecipientType.TO属性表示接收者的类型为TO
			mailMessage.setRecipients(Message.RecipientType.TO, tos);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo._mail_subject_txt);
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// 设置邮件消息的主要内容
			String mailContent = mailInfo._mail_content_txt;
			mailMessage.setText(mailContent);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 发送邮件给多个接收者,以Html内容
	 * 
	 * @param mailInfo
	 *            带发送邮件的信息
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
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo._mail_sender_address);
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address[] tos = null;
			String[] receivers = mailInfo.getReceivers();
			if (receivers != null) {
				// 为每个邮件接收者创建一个地址
				tos = new InternetAddress[receivers.length + 1];
				tos[0] = new InternetAddress(mailInfo._mail_receiver_address);
				for (int i = 0; i < receivers.length; i++) {
					tos[i + 1] = new InternetAddress(receivers[i]);
				}
			} else {
				tos = new InternetAddress[1];
				tos[0] = new InternetAddress(mailInfo._mail_receiver_address);
			}
			// 将所有接收者地址都添加到邮件接收者属性中
			mailMessage.setRecipients(Message.RecipientType.TO, tos);

			mailMessage.setSubject(mailInfo._mail_subject_txt);
			mailMessage.setSentDate(new Date());
			// 设置邮件内容
			Multipart mainPart = new MimeMultipart();
			BodyPart html = new MimeBodyPart();
			html.setContent(mailInfo._mail_content_txt,
					"text/html; charset=GBK");
			mainPart.addBodyPart(html);
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 发送带抄送的邮件
	 * 
	 * @param mailInfo
	 *            待发送邮件的消息
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
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo._mail_sender_address);
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo._mail_receiver_address);
			mailMessage.setRecipient(Message.RecipientType.TO, to);

			// 获取抄送者信息
			String[] ccs = mailInfo.getCcs();
			if (ccs != null) {
				// 为每个邮件接收者创建一个地址
				Address[] ccAdresses = new InternetAddress[ccs.length];
				for (int i = 0; i < ccs.length; i++) {
					ccAdresses[i] = new InternetAddress(ccs[i]);
				}
				// 将抄送者信息设置到邮件信息中，注意类型为Message.RecipientType.CC
				mailMessage.setRecipients(Message.RecipientType.CC, ccAdresses);
			}

			mailMessage.setSubject(mailInfo._mail_subject_txt);
			mailMessage.setSentDate(new Date());
			// 设置邮件内容
			Multipart mainPart = new MimeMultipart();
			BodyPart html = new MimeBodyPart();
			html.setContent(mailInfo._mail_content_txt,
					"text/html; charset=GBK");
			mainPart.addBodyPart(html);
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 发送多接收者类型邮件的基本信息
	 */
	public static class MultiMailSenderInfo extends MailInfo {
		// 邮件的接收者，可以有多个
		private String[] receivers;
		// 邮件的抄送者，可以有多个
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

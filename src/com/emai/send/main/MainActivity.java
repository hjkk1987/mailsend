package com.emai.send.main;

import com.emai.send.mail.MailSender;
import com.emai.send.mail.MailSender.MultiMailSenderInfo;
import com.email.send.main.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private EditText et_send = null;
	private Button btn_send = null;
	private MultiMailSenderInfo mailSenderInfo = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		initMail();
		widget_init();
	}

	private void initMail() {
		mailSenderInfo = new MultiMailSenderInfo();
		mailSenderInfo._mail_service_IP = "smtp.163.com";
		mailSenderInfo._mail_service_port = "25";
		mailSenderInfo.isValidate = true;
		mailSenderInfo._mail_user_account = "hjkk19872@163.com";
		mailSenderInfo._mail_user_passwd = "hujun19872";// 您的邮箱密码
		mailSenderInfo._mail_sender_address = "hjkk19872@163.com";
		mailSenderInfo._mail_receiver_address = "593138786@qq.com";
		mailSenderInfo._mail_subject_txt = "设置邮箱标题";
		mailSenderInfo._mail_content_txt = "设置邮箱内容";
		String[] receivers = new String[] { "1693158071@qq.com" };
		String[] ccs = receivers;
		mailSenderInfo.setReceivers(receivers);
		mailSenderInfo.setCcs(ccs);
	}

	private void widget_init() {
		et_send = (EditText) findViewById(R.id.et_content_send_message);
		btn_send = (Button) findViewById(R.id.btn_send);
		btn_send.setOnClickListener(new ClickListener());
	}

	private class ClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_send: {
				MailSender sms = new MailSender();
				sms.sendTextMail(mailSenderInfo);// 发送文体格式
				MailSender.sendMailtoMultiReceiver(mailSenderInfo);// 发送多个接收者
				MailSender.sendMailtoMultiCC(mailSenderInfo);// 发送抄送
			}
				break;

			default:
				break;
			}

		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}

}

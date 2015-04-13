package com.email.send.state;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

/*****************************
 * �ж�����״̬
 * 
 * @author beiyong01
 * 
 */
public class State {

	private ConnectivityManager connectivityManager = null;

	// ��ȡϵͳ������״̬������
	public State(Context context) {
		connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
	}

	/*********************************************
	 * ��ȡϵͳ������״̬�������Ϳ����ڵ����Ի����ʱ����ʾһ���Ի���
	 * 
	 * @return
	 */
	public boolean getInterState() {
		boolean isConnect = false;
		if (connectivityManager != null) {
			NetworkInfo[] netInfo = connectivityManager.getAllNetworkInfo();
			for (int i = 0; i < netInfo.length; i++) {
				if (netInfo[i].getState() == NetworkInfo.State.CONNECTED) {
					isConnect = true;
					break;
				}
			}
		}
		return isConnect;
	}

	
}

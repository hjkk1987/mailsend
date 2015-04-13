package com.email.send.state;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

/*****************************
 * 判断连接状态
 * 
 * @author beiyong01
 * 
 */
public class State {

	private ConnectivityManager connectivityManager = null;

	// 获取系统的连接状态管理器
	public State(Context context) {
		connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
	}

	/*********************************************
	 * 获取系统的连接状态。这样就可以在弹出对话框的时候提示一个对话框。
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

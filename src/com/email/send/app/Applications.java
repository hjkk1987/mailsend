package com.email.send.app;

import com.email.send.state.State;
import android.app.Application;

public class Applications extends Application {
	public static State mState = null;//ÍøÂçÁ¬½Ó×´Ì¬
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mState = new State(getApplicationContext());
	}
}

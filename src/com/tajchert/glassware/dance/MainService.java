package com.tajchert.glassware.dance;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.google.android.glass.timeline.LiveCard;

public class MainService extends Service {

	


	@SuppressWarnings("unused")
	private LiveCard mLiveCard;

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	public int onStartCommand(Intent intent, int flags, int startId) {
		/*if(Tools.isNetworkAvailable(this)){
			new UserLoginTask().execute();
		}*/
		Intent i = new Intent(this, LessonActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(i);
		return START_STICKY;
	} // onStartCommand
	
	
	
}
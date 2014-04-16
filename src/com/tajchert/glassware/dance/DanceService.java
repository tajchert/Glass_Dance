package com.tajchert.glassware.dance;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.android.glass.timeline.LiveCard;
import com.google.android.glass.timeline.LiveCard.PublishMode;

public class DanceService extends Service {

	private static final String LOG_TAG = "GLASS_DANCE";
    private static final String LIVE_CARD_TAG = "dance_card";

    private DancingDrawer mCallback;
    private LiveCard mLiveCard;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mLiveCard == null) {
            Log.d(LOG_TAG, "Publishing LiveCard");
            //mLiveCard = mTimelineManager.createLiveCard(LIVE_CARD_TAG);
            mLiveCard = new LiveCard(this, LIVE_CARD_TAG);
            Dance dance = null;
            if (intent !=null && intent.getExtras()!=null){
                 int danPos = (int) intent.getExtras().getInt("dance");
                 Log.d(LOG_TAG, "danPos: "+ danPos);
                 switch (danPos){
	                 case 0:
	                	 dance = InitDance.initEnglishWaltz();
	                	 break;
	                 case 1:
	                	 dance = InitDance.initViennaWaltz();
	                	 break;
                 }
            }
            mCallback = new DancingDrawer(this, dance);
            mLiveCard.setDirectRenderingEnabled(true).getSurfaceHolder().addCallback(mCallback);

            Intent menuIntent = new Intent(this, MenuActivity.class);
            menuIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mLiveCard.setAction(PendingIntent.getActivity(this, 0, menuIntent, 0));
            mLiveCard.attach(this);
            mLiveCard.publish(PublishMode.REVEAL);
            Log.d(LOG_TAG, "Done publishing LiveCard");
        } else {
            mLiveCard.navigate();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mLiveCard != null && mLiveCard.isPublished()) {
            Log.d(LOG_TAG, "Unpublishing LiveCard");
            if (mCallback != null) {
                mLiveCard.getSurfaceHolder().removeCallback(mCallback);
            }
            mLiveCard.unpublish();
            mLiveCard = null;
        }
        super.onDestroy();
    }
}


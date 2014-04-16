package com.tajchert.glassware.dance;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;

public class DancingDrawer implements SurfaceHolder.Callback {
    private static final String TAG = "GLASS_DANCE";

    private final DanceView mDancePlayer;
    private final Handler mHandler = new Handler();

    private SurfaceHolder mHolder;
    public DancingDrawer(Context context, Dance dance) {

        mDancePlayer = new DanceView(context, dance);
        mDancePlayer.setListener(new DanceView.ChangeListener() {

            @Override
            public void onChange() {
                draw(mDancePlayer);
            }
        });
        mDancePlayer.setForceStart(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);

        mDancePlayer.measure(measuredWidth, measuredHeight);
        mDancePlayer.layout(
                0, 0, mDancePlayer.getMeasuredWidth(), mDancePlayer.getMeasuredHeight());
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "Surface created");
        mHolder = holder;
        mDancePlayer.start();
        
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "Surface destroyed");
        mDancePlayer.stop();
        mHolder = null;
    }

    /**
     * Draws the view in the SurfaceHolder's canvas.
     */
    private void draw(View view) {
        Canvas canvas;
        try {
            canvas = mHolder.lockCanvas();
        } catch (Exception e) {
            return;
        }
        if (canvas != null) {
            view.draw(canvas);
            mHolder.unlockCanvasAndPost(canvas);
        }
    }
    protected void post(Runnable runnable) {
    	Log.d("GLASS_DANCE", "post");
        mHandler.post(runnable);
    }
}


package com.tajchert.glassware.dance;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class DanceView extends FrameLayout {

    /**
     * Interface to listen for changes on the view layout.
     */
    public interface ChangeListener {
        public void onChange();
    }

    // About 24 FPS.
    private static final long DELAY_MILLIS = 100;
    private static final String LOG_TAG = "GLASS_DANCE";
    private static final long MINUS_TIME = 100;


    private boolean mStarted;
    private boolean mForceStart;
    private boolean mVisible;
    private boolean mRunning;
    private int lastPosition;
    private long nextPositionTime;
    
    private Dance dance;
    private Bitmap bitmapLeft;
    private Bitmap bitmapRight;
    private Canvas canvas;
    private Bitmap bg;
    private LinearLayout ll;

    private long mBaseMillis;
    private ChangeListener mChangeListener;
    

    public DanceView(Context context, Dance dance) {
        this(context, null, 0, dance);
    }

    public DanceView(Context context, AttributeSet attrs, Dance dance) {
        this(context, attrs, 0, dance);
    }

    public DanceView(Context context, AttributeSet attrs, int style, Dance dance) {
        super(context, attrs, style);
        LayoutInflater.from(context).inflate(R.layout.dance_instractions, this);
        
        setBaseMillis(SystemClock.elapsedRealtime());
        setDance(dance);
        ll = (LinearLayout) findViewById(R.id.main_area);
        bitmapLeft = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.left), 70, 200, false);
        bitmapRight = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.right), 70, 200, false);
        bg = Bitmap.createBitmap(360, 640, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bg);
    }

    public void setBaseMillis(long baseMillis) {
        mBaseMillis = baseMillis;
        lastPosition = 0;
        updateText();
    }
    
    public void setDance(Dance dance){
    	this.dance = dance;
    }

    public long getBaseMillis() {
        return mBaseMillis;
    }

    public void setListener(ChangeListener listener) {
        mChangeListener = listener;
    }

    public void setForceStart(boolean forceStart) {
        mForceStart = forceStart;
        updateRunning();
    }

    public void start() {
        mStarted = true;
        updateRunning();
    }

    public void stop() {
        mStarted = false;
        updateRunning();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mVisible = false;
        updateRunning();
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        mVisible = (visibility == VISIBLE);
        updateRunning();
    }


    private final Handler mHandler = new Handler();

    private final Runnable mUpdateTextRunnable = new Runnable() {
        @Override
        public void run() {
            if (mRunning) {
                updateText();
                mHandler.postDelayed(mUpdateTextRunnable, DELAY_MILLIS);
            }
        }
    };
    private void updateRunning() {
        boolean running = (mVisible || mForceStart) && mStarted;
        if (running != mRunning) {
            if (running) {
                mHandler.post(mUpdateTextRunnable);
            } else {
                mHandler.removeCallbacks(mUpdateTextRunnable);
            }
            mRunning = running;
        }
    }
    private void updateText() {
        long millis = SystemClock.elapsedRealtime() - mBaseMillis;
        Log.d(LOG_TAG, "pos" +lastPosition+", millis:" + millis);
        if(dance != null){
	        if(nextPositionTime <= millis && lastPosition < (dance.moves.size()-2)){
	        	DanceMove dm = dance.moves.get(lastPosition);
	        	//Log.d(LOG_TAG, "(" + dm.leftFeetX +","+ dm.leftFeetY +"),("+dm.rightFeetX+","+dm.rightFeetY+")");
	        	//Log.d(LOG_TAG, "nextPositionTime:" + nextPositionTime +" @ "+millis);
	        	lastPosition++;
	        	
	        	bg = Bitmap.createBitmap(360, 640, Bitmap.Config.ARGB_8888);
	            canvas = new Canvas(bg);
	            
	        	//TODO height and width are wrongly set below!
	            canvas.drawColor(Color.BLACK);
	            canvas.drawBitmap(bitmapLeft, dm.leftFeetX, dm.leftFeetY, null);
	            canvas.drawBitmap(bitmapRight, dm.rightFeetX, dm.rightFeetY, null);
	            
	            ll.setBackgroundColor(0);
	            ll.setBackgroundDrawable(new BitmapDrawable(bg));
	        	nextPositionTime = (dance.moves.get(lastPosition+1).time - MINUS_TIME);
	        }
	        if (mChangeListener != null) {
	            mChangeListener.onChange();
	        }
	        if(lastPosition >= (dance.moves.size()-2)){
	        	lastPosition = 0;
	        	nextPositionTime = (dance.moves.get(lastPosition).time - MINUS_TIME);
	        	mBaseMillis = SystemClock.elapsedRealtime()-1000;
	        	//this.stop();
	        }
        }
        
    }
}

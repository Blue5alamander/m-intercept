package com.kirit.android.mcommand;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;


public class MCommand extends Activity {
	GameView view;
	private Handler handler = new Handler();
	private Runnable runner = new Runnable() {
		public void run() {
			// do drawing etc
			view.invalidate();
			// reschedule
			handler.postDelayed(runner, 50);
		}
	};

    public MCommand() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new GameView(this);
        view.setPadding(0, 0, 0, 0);
        setContentView(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.post(runner);
    }
    @Override
    protected void onPause() {
    	super.onPause();
    	handler.removeCallbacks(runner);
    }
}

package com.kirit.android.mintercept.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.kirit.android.mintercept.Player;


public class Level extends View {
	private Player player = new Player();

	public Level(Context context) {
		super(context);
	}
	public Level(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public Level(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onDraw(Canvas c) {
		player.draw(c);
	}
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	if ( event.getAction() == MotionEvent.ACTION_DOWN ) {
    		player.tap(event.getX(), event.getY());
    		return true;
    	}
    	return false;
    }
}

package com.kirit.android.mcommand;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.kirit.android.mcommand.Player;


public class GameView extends View {
	private Player player = new Player();

	public GameView(Context context) {
		super(context);
	}
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public GameView(Context context, AttributeSet attrs, int defStyle) {
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

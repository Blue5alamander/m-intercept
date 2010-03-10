/*
    Copyright 1995-2010, Kirit Saelensminde.
    http://www.kirit.com/Missile%20intercept

    This file is part of Missile intercept.

    AnimRay is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    AnimRay is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with AnimRay.  If not, see <http://www.gnu.org/licenses/>.
*/


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

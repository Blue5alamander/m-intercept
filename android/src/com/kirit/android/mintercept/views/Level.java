/*
    Copyright 1995-2010, Kirit Saelensminde.
    http://www.kirit.com/Missile%20intercept

    This file is part of Missile intercept.

    Missile intercept is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Missile intercept is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Missile intercept.  If not, see <http://www.gnu.org/licenses/>.
*/


package com.kirit.android.mintercept.views;

import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.kirit.android.mintercept.Game;


public class Level extends Scene {
	private Game game;


	public Level(Context context) {
		super(context);
		game = new Game(context, this);
		draw(game);
	}

    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	if ( event.getAction() == MotionEvent.ACTION_DOWN ) {
    		resume(); // Make sure we don't stay paused
    		game.getPlayer().tap(event.getX(), event.getY());
    		return true;
    	}
    	return false;
    }
    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if ( keyCode == KeyEvent.KEYCODE_DPAD_UP && event.getAction() == MotionEvent.ACTION_DOWN ) {
    		togglePause();
    		return true;
    	} else
    		return super.onKeyDown(keyCode, event);
    }*/

    @Override
    public void reset() {
    	game.reset();
    }
}

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
    along with Missile intercept.  If not, see <http://www.gnu.org/licenses/>.
*/


package com.kirit.android.mintercept.views;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import com.kirit.android.Element.Layer;
import com.kirit.android.mintercept.Game;


public class Level extends View {
	private Game game;


	public Level(Context context) {
		super(context);
	}

	public void setGame(Context context, Game g) {
		game = g;
	}

	@Override
	protected void onDraw(Canvas c) {
		c.drawARGB(255, 0, 0, 0);
		game.tick();
		game.draw(c, Layer.BACKGROUND);
		game.draw(c, Layer.CITIES);
		game.draw(c, Layer.TRAILS);
		game.draw(c, Layer.EXPLOSIONS);
		game.draw(c, Layer.MISSILES);
		game.draw(c, Layer.CHROME);
	}

    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	if ( event.getAction() == MotionEvent.ACTION_DOWN ) {
    		game.getPlayer().tap(event.getX(), event.getY());
    		return true;
    	}
    	return false;
    }
}

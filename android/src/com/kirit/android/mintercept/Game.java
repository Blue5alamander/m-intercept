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


package com.kirit.android.mintercept;

import com.kirit.android.Element;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;


public class Game extends Element {
	private boolean isover;
	private Player player;
	private BitmapDrawable gameover;
	private Rect location;

	public Game(Context context) {
		isover = false;
		player = new Player(context, this);
		gameover = (BitmapDrawable)context.getResources().getDrawable(R.drawable.gameover); 
	}

	public Player getPlayer() {
		return player;
	}

	/**
	 * When called the game is over
	 */
	public void over() {
		isover = true;
	}
	/**
	 * Allows us to determine if the game is over.
	 */
	public boolean isOver() {
		return isover;
	}

	@Override
	public boolean draw(Canvas c) {
		if ( isover ) {
			if ( location == null ) {
				location = new Rect(
					c.getWidth() / 2 - gameover.getMinimumWidth() / 2, c.getHeight() / 2 - gameover.getMinimumHeight(),
					c.getWidth() / 2 + gameover.getMinimumWidth() / 2, c.getHeight() / 2
				);
			}
			gameover.setBounds(location);
			gameover.draw(c);
		}
		return player.draw(c);
	}
}

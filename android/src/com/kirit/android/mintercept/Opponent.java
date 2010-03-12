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

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.kirit.android.Element;

public class Opponent extends Element {
	private static Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

	private class Missile extends Element {
		private boolean inuse;
		float sx, sy, cx, cy, dx, dy, tx;
		public Missile(Context context) {
			inuse = false;
			dx = 0; dy = 0;
		}

		public boolean reset() {
			if ( !inuse ) {
				inuse = true; dy = 0;
				return true;
			}
			return false;
		}

		@Override
		public boolean draw(Canvas c) {
			if ( inuse ) {
				if ( dy == 0 ) {
					// Initialise the missile
					dx = 0; dy = 1;
					sx = Game.randomGenerator.nextInt(c.getWidth());
					sy = game.score.getTotalHeight() + 1;
					cx = sx; cy = sy;
					tx = Game.randomGenerator.nextInt(c.getWidth());
					dx = ( tx - sx ) / ( c.getHeight() - sy );
				}
				// Move the missile then draw it
				cx += dx; cy += dy;
				if ( cy == c.getHeight() ) {
					inuse = false;
				}
				paint.setColor(0xff808080);
				c.drawLine(sx, sy, cx, cy, paint);
				paint.setColor(0xffffffff);
				c.drawCircle(cx, cy, 2, paint);
			}
			return inuse;
		}
	};
	
	private Game game;
	private Missile [] missiles;
	private int timer;

	public Opponent(Context context, Game g) {
		game = g;
		missiles = new Missile [20];
		for ( int i = 0; i != missiles.length; ++i )
			missiles[i] = new Missile(context);
		reset();
	}
	
	public void reset() {
		timer = 0;
		game.missiles.reset(5 * game.level.getValue() + 5);
	}

	@Override
	public boolean draw(Canvas c) {
		if ( timer <= 0 && game.missiles.getValue() > 0 ) {
			for ( Missile m : missiles )
				if ( m.reset() ) {
					game.missiles.alter(-1);
					timer = 20;
					break;
				}
		} else
			--timer;
		boolean inplay = game.missiles.getValue() > 0;
		for ( Missile m : missiles )
			if ( m.draw(c) );
				inplay = true;
		return inplay;
	}

}

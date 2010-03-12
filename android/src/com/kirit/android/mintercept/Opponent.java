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
import android.view.View;

import com.kirit.android.Element;

public class Opponent extends Element {
	private static Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

	private class Missile extends Element {
		private Game game;
		private View view;
		private boolean inuse, exploding;
		private int fade;
		private float sx, sy, cx, cy, dx, dy, tx;
		private Explosion explosion;

		public Missile(Game g, Context context, View v) {
			game = g;
			view = v;
			inuse = false; exploding = false;
			dx = 0; dy = 0;
			explosion = new Explosion(g, 10, Layer.MISSILES);
		}

		public boolean reset() {
			if ( !inuse ) {
				fade = 8;
				dy = 0; dx = 0;
				inuse = true;
				exploding = false;
				return true;
			}
			return false;
		}

		@Override
		public boolean tick() {
			if ( inuse ) {
				if ( dy == 0 && !exploding ) {
					dx = 0; dy = 1f + game.level.getValue() / 10f;
					sx = Game.randomGenerator.nextInt(view.getWidth());
					sy = game.missiles.getTotalHeight() + 1;
					cx = sx; cy = sy;
					tx = Game.randomGenerator.nextInt(view.getWidth());
					dx = ( tx - sx ) / ( view.getHeight() - sy );
				}
				cx += dx; cy += dy;
				if ( cy >= view.getHeight() - 20 ) {
					City struck = game.getPlayer().hasStruck(cx);
					if ( struck != null ) {
						exploding = true;
						explosion.reset(cx, cy);
						struck.explode();
					} else if ( !exploding && cy >= view.getHeight() - 3 ) {
						dy = 0; dx = 0;
						exploding = true;
						explosion.reset(cx, cy);
						game.award(-3 * game.level.getValue());
					}
				} else if ( !exploding && game.inExplosion(cx, cy) ) {
					exploding = true;
					explosion.reset(cx, cy);
					game.getPlayer().hit();
				}
				if ( exploding )
					inuse = explosion.tick();
			}
			return inuse;
		}

		@Override
		public void draw(Canvas c, Layer layer) {
			if ( inuse ) {
				if (layer == Layer.TRAILS) {
					if ( !exploding || fade-- == 8 ) {
						paint.setColor(0xff808080);
						c.drawLine(sx, sy, cx, cy, paint);
					} else if ( fade > 0 )  {
						paint.setColor(0x808080 + fade * 0x02000000 + fade * 0x20000000);
						c.drawLine(sx, sy, cx, cy, paint);
						--fade;
					}
				} else if (layer == Layer.MISSILES && !exploding) {
					paint.setColor(0xffffffff);
					c.drawCircle(cx, cy, 2, paint);
				}
				explosion.draw(c, layer);
			}
		}
	};

	private Game game;
	private Missile [] missiles;
	private int timer;

	public Opponent(Context context, View view, Game g) {
		game = g;
		missiles = new Missile [20];
		for ( int i = 0; i != missiles.length; ++i )
			missiles[i] = new Missile(g, context, view);
		reset();
	}

	public void reset() {
		timer = 0;
		game.missiles.reset(2 * game.level.getValue() + 3);
	}

	@Override
	public boolean tick() {
		boolean inplay = game.missiles.getValue() > 0;
		if ( timer <= 0 && inplay ) {
			for ( Missile m : missiles )
				if ( m.reset() ) {
					game.missiles.alter(-1);
					timer = Math.max(80 - 4 * game.level.getValue(), 2);
					break;
				}
		} else
			--timer;
		for ( Missile missile : missiles )
			if ( missile.tick() )
				inplay = true;
		return inplay;
	}

	@Override
	public void draw(Canvas c, Layer layer) {
		for ( Missile m : missiles )
			m.draw(c, layer);
	}
}

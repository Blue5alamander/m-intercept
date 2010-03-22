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


package com.kirit.android.mintercept;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.kirit.android.Element;
import com.kirit.android.mintercept.R.drawable;


public class City extends Element {
	private boolean drawcity, isdead;
	private Drawable city;
	private View view;
	private int number, outof;
	private Rect location;
	private Explosion explosion;

	public City(Game game, Context context, View v, int n, int o) {
		view = v; number = n; outof = o;
		city = context.getResources().getDrawable(drawable.city);
		explosion = new Explosion(game, city.getMinimumWidth(), Layer.EXPLOSIONS);
        reset();
	}

    public void reset() {
        drawcity = true;
        isdead = false;
    }
	public boolean hasStruck(float x) {
		if ( isdead )
			return false;
		else
			return x >= location.left && x <= location.right;
	}

	public void explode() {
		isdead = true;
		explosion.reset(
			location.left + (location.right - location.left) / 2,
			location.top + (location.bottom - location.top) / 2
		);
	}

	@Override
	public boolean tick() {
		explosion.tick();
		if ( drawcity && isdead && explosion.pastZenith() )
			drawcity = false;
		return !isdead;
	}

	@Override
	public void draw(Canvas c, Layer layer) {
		if ( layer == Layer.CITIES && drawcity ) {
			if ( location == null ) {
				int w = city.getMinimumWidth();
				int spacing = view.getWidth() / outof;
				int left = spacing * number + (spacing - w) / 2;
				location = new Rect(
						left, view.getHeight() - city.getMinimumHeight(),
						left + w, view.getHeight()
					);
			}
			city.setBounds(location);
			city.draw(c);
		}
		explosion.draw(c, layer);
	}
}

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
import android.view.View;

import com.kirit.android.Element;


public class Player extends Element {
	private Game game;
	private Explosion [] explosions;
	private City cities [];

	public 	Player(Context context, View view, Game g) {
		game = g;
		explosions = new Explosion [10];
		for ( int i = 0; i != explosions.length; ++i )
			explosions[i] = new Explosion(35, Layer.EXPLOSIONS);
		cities = new City [3];
		for ( int n = 0; n != cities.length; ++n )
			cities[n] = new City(context, view, n, cities.length);
		reset();
	}

	public void reset() {
	}

	public boolean tap(float x, float y) {
		if ( !game.isOver() && explosions[0].reset(x, y) ) {
			Explosion e = explosions[0];
			for ( int i = 0; i != explosions.length-1; ++i )
				explosions[i] = explosions[i+1];
			explosions[explosions.length-1] = e;
			game.award(-1);
			return true;
		}
		return false;
	}

	@Override
	public boolean tick() {
		return true;
	}

	@Override
	public void draw(Canvas c, Layer layer) {
		for ( City city : cities )
			city.draw(c, layer);
		for ( Explosion e : explosions )
			e.draw(c, layer);
	}
}

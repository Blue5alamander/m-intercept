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
import android.view.View;

import com.kirit.android.Element;


public class Player extends Element {
	private int hitbonus;
	private Game game;
	private Explosions explosions;
	private City cities [];

	public 	Player(Context context, View view, Game g) {
		game = g;
		cities = new City [3];
		explosions = new Explosions(10);
		for ( int n = 0; n != cities.length; ++n )
			cities[n] = new City(g, context, view, n, cities.length);
		reset();
	}

	public void reset() {
	}

	public City hasStruck(float x) {
		for ( City c : cities )
			if ( c.hasStruck(x) )
				return c;
		return null;
	}
	
	/**
	 * The player has exploded a missile.
	 */
	public void hit() {
		 game.award(5 * game.level.getValue() * hitbonus++);
	}

	public boolean tap(float x, float y) {
		if ( !game.isOver() && explosions.reset(x, y) != null ) {
			hitbonus = 1;
			game.award(-1);
			return true;
		}
		return false;
	}

	@Override
	public boolean tick() {
		boolean alldead = true;
		for ( City city : cities )
			if ( city.tick() )
				alldead = false;
		if ( alldead )
			game.over();
		explosions.tick();
		return !alldead;
	}

	@Override
	public void draw(Canvas c, Layer layer) {
		for ( City city : cities )
			city.draw(c, layer);
		explosions.draw(c, layer);
	}
}

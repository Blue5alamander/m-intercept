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
import com.kirit.android.Element;
import com.kirit.android.NumberPanel;


public class Player extends Element {
	private Game game;
	private NumberPanel score;
	private Explosion [] explosions;
	private int cities;

	public 	Player(Context context, Game g) {
		game = g;
		explosions = new Explosion [10];
		for ( int i = 0; i != explosions.length; ++i )
			explosions[i] = new Explosion(35);
		score = new NumberPanel(context, 8, R.drawable.score_prolog, R.drawable.score_numbers);
		reset();
	}
	
	public void reset() {
		cities = 3;
		score.reset(10);
	}
	
	public int getCities() {
		return cities;
	}

	public boolean tap(float x, float y) {
		if ( explosions[0].reset(x, y) ) {
			Explosion e = explosions[0];
			for ( int i = 0; i != explosions.length-1; ++i )
				explosions[i] = explosions[i+1];
			explosions[explosions.length-1] = e;
			if ( score.alter(-1) == 0 )
				game.over();
			return true;
		}
		return false;
	}

	public boolean draw(Canvas c) {
		boolean drawn = false;
		for ( int i = 0; i != explosions.length; ++i )
			if ( explosions[i].draw(c) && !drawn )
				drawn = true;
		score.draw(c);
		return drawn;
	}
}

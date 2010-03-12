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


package com.kirit.android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;


public class NumberPanel extends Element {
	private int number, digits;
	private Drawable prolog, numbers;
	private Rect location;

	public NumberPanel(Context context, int d, int p, int n) {
		number = 0; digits = d;
		prolog = context.getResources().getDrawable(p);
		numbers = context.getResources().getDrawable(n);
	}

	public void reset(int n) {
		number = n;
	}
	public int alter(int by) {
		return number += by;
	}

	@Override
	public boolean draw(Canvas c) {
		/*for ( int i = 0, n = number; i < digits; ++i ) {
			int v = n % 10;
			n = n / 10;
		}*/
		if ( location == null ) {
			location = new Rect(
					0, 0,
					prolog.getMinimumWidth(), prolog.getMinimumHeight()
				);
		}
		prolog.setBounds(location);
		prolog.draw(c);
		//numbers.draw(c);
		return true;
	}
}

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

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.kirit.android.Element;
import com.kirit.android.Spectrum;


class Explosion extends Element {
	private static Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private boolean draw = false;
	private Spectrum colour = new Spectrum(0.0f, 0.75f, 0.75f);
	private float cx, cy, inner_radius, outer_radius;
	private int size, fade;

	public Explosion(int s) {
		size = s;
	}

	public boolean reset(float x, float y) {
		if ( !draw ) {
			draw = true;
			cx = x; cy = y;
			inner_radius = 1;
			outer_radius = 1;
			fade = 8;
			return true;
		} else
			return false;
	}
	@Override
	public boolean draw(Canvas c) {
		if ( draw ) {
			if (inner_radius < size) {
				paint.setColor(colour.next(size));
				c.drawCircle(cx, cy, outer_radius, paint);
				if ( outer_radius > size ) {
					paint.setColor(0xff404040);
					c.drawCircle(cx, cy, inner_radius, paint);
					++inner_radius;
				} else
					++outer_radius;
			} else if ( fade > 0 ) {
				--fade;
				paint.setColor(0x404040 + fade * 0x02000000 + fade * 0x20000000);
				c.drawCircle(cx, cy, inner_radius, paint);
			} else
				draw = false;
			return true;
		} else
			return false;
	}
}

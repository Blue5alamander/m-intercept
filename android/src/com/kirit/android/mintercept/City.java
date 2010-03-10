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
import android.graphics.drawable.Drawable;

import com.kirit.android.Element;
import com.kirit.android.mintercept.R.drawable;


public class City extends Element {
	private Drawable city;

	public City(Context context, int number, int outof) {
		city = context.getResources().getDrawable(drawable.city);
		city.setBounds(30 + number * 40, 30, 30 + number * 40 + city.getMinimumWidth(), 30 + city.getMinimumHeight());
	}

	@Override
	public boolean draw(Canvas c) {
		city.draw(c);
		return true;
	}

}

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
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.kirit.android.Element;
import com.kirit.android.mintercept.R.drawable;


public class City extends Element {
	private Drawable city;
	private View view;
	private int number, outof;
	private Rect location;

	public City(Context context, View v, int n, int o) {
		view = v;
		number = n;
		outof = o;
		city = context.getResources().getDrawable(drawable.city);
	}

	@Override
	public boolean draw(Canvas c) {
		if ( location == null ) {
			int w = city.getMinimumWidth();
			int spacing = view.getWidth() / (outof + 1);
			int left = spacing * number - w / 2 + spacing;
			location = new Rect(
					left, view.getHeight() - city.getMinimumHeight(),
					left + w, view.getHeight()
				);
		}
		city.setBounds(location);
		city.draw(c);
		return true;
	}
}

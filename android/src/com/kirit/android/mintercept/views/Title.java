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


package com.kirit.android.mintercept.views;

import com.kirit.android.Element;
import com.kirit.android.mintercept.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;


public class Title extends Scene {
	private class Demo extends Element {
		View view;
		BitmapDrawable felspar;
		Rect location = new Rect();

		public Demo(Context context, View v) {
			view = v;
			felspar = (BitmapDrawable)context.getResources().getDrawable(R.drawable.felspar);
		}

		@Override
		public boolean tick() {
			return true;
		}

		@Override
		public void draw(Canvas c, Layer layer) {
			if ( layer == Layer.BACKGROUND ) {
				location.left = (view.getWidth() - felspar.getMinimumWidth() ) /2;
				location.right = view.getWidth() - location.left;
				location.top = view.getHeight() - felspar.getMinimumHeight();
				location.bottom = view.getHeight();
				felspar.setBounds(location);
				felspar.draw(c);
			}
		}
	};
	Demo demo;


	Context context;
	public Title(Context c) {
		super(c);
		context = c;
		demo = new Demo(context, this);
		draw(demo);
	}
}

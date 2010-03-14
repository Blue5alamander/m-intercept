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
import com.kirit.android.Element.Layer;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;


public class Scene extends View {
	private Element todraw;


	public Scene(Context context) {
		super(context);
	}

	public void draw(Element e) {
		todraw = e;
	}

	@Override
	protected void onDraw(Canvas c) {
		c.drawARGB(255, 0, 0, 0);
		todraw.tick();
		todraw.draw(c, Layer.BACKGROUND);
		todraw.draw(c, Layer.CITIES);
		todraw.draw(c, Layer.TRAILS);
		todraw.draw(c, Layer.EXPLOSIONS);
		todraw.draw(c, Layer.MISSILES);
		todraw.draw(c, Layer.CHROME);
	}

}

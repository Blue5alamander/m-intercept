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

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import com.kirit.android.Element;
import com.kirit.android.mintercept.views.Level;
import com.kirit.android.mintercept.views.Title;


public class Overlay extends Element {
    private static Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private View view;
    private boolean allowBackground;
    private boolean active;
    private Rect location = new Rect();

    public Overlay(Title title) {
        view = title;
        allowBackground = true;
        deactivate();
    }
    public Overlay(Level level) {
        view = level;
        allowBackground = false;
        deactivate();
    }

    public void activate() {
        active = true;
    }
    public void deactivate() {
        active = false;
    }
    public boolean isActive() {
        return active;
    }
 
    @Override
    public void draw(Canvas c, Layer layer) {
        if ( active ) {
            location.top = 0; location.bottom = view.getHeight();
            location.left = 0; location.right = view.getWidth();
            paint.setColor(Color.BLACK);
            paint.setAlpha(180);
            c.drawRect(location, paint);
        }
    }

    @Override
    public boolean tick() {
        return allowBackground || !active;
    }
}

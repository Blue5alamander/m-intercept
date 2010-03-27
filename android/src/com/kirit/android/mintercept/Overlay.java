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
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
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
    
    private BitmapDrawable vibrate_on, vibrate_off;


    private Overlay(Context context, View v, boolean allowbg) {
        view = v;
        allowBackground = allowbg;
        deactivate();
        
        vibrate_on = (BitmapDrawable)context.getResources().getDrawable(R.drawable.vibrate_on);
        vibrate_off = (BitmapDrawable)context.getResources().getDrawable(R.drawable.vibrate_off);
    }
    public Overlay(Context context, Title title) {
        this(context, title, true);
    }
    public Overlay(Context context, Level level) {
        this(context, level, false);
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

    /**
     * Pass on touch events to the overlay when it is active
     * @param event
     * @return
     */
    public boolean onTouchEvent(MotionEvent event) {
        if ( active ) {
            return true;
        }
        return false;
    }

    @Override
    public void draw(Canvas c, Layer layer) {
        if ( active ) {
            location.top = 0; location.bottom = view.getHeight();
            location.left = 0; location.right = view.getWidth();
            paint.setColor(Color.BLACK);
            paint.setAlpha(180);
            c.drawRect(location, paint);

            location.top = view.getHeight() / 3;
            location.bottom = location.top + vibrate_on.getMinimumHeight();
            location.left = ( view.getWidth() - vibrate_on.getMinimumWidth() ) / 2;
            location.right = location.left +vibrate_on.getMinimumWidth();
            paint.setAlpha(255);
            vibrate_on.setBounds(location);
            vibrate_on.draw(c);
        }
    }

    @Override
    public boolean tick() {
        return allowBackground || !active;
    }
}

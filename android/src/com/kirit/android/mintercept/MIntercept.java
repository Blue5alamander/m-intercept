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

import com.kirit.android.mintercept.views.Level;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;


public class MIntercept extends Activity {
	Game game;
	View view;
	private Handler handler = new Handler();
	private Runnable runner = new Runnable() {
		public void run() {
			// do drawing etc
			view.invalidate();
			// reschedule
			handler.postDelayed(runner, 50);
		}
	};

    public MIntercept() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = new Game();
        view = new Level(this, game);
        view.setPadding(0, 0, 0, 0);
        setContentView(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.post(runner);
    }
    @Override
    protected void onPause() {
    	super.onPause();
    	handler.removeCallbacks(runner);
    }
}

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
import com.kirit.android.mintercept.views.Title;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;


public class MIntercept extends Activity {
	private Handler handler = new Handler();
	private Runnable runner = new Runnable() {
		public void run() {
			level.invalidate();
			handler.postDelayed(runner, 50);
		}
	};

	Game game;
	Title title;
	Level level;

    public MIntercept() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = new Title(this);
        level = new Level(this);
        game = new Game(this, level);
        level.setGame(game);
        level.setPadding(0, 0, 0, 0);
        setContentView(title);
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

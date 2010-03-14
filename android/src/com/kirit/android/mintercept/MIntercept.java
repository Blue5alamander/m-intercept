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

import com.kirit.android.mintercept.views.Level;
import com.kirit.android.mintercept.views.Title;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;


public class MIntercept extends Activity {
	private Handler handler = new Handler();
	private Runnable runner = new Runnable() {
		public void run() {
			view.invalidate();
			handler.postDelayed(runner, 50);
		}
	};

	Game game;
	Title title;
	Level level;
	View view;

    public MIntercept() {
    }

    public void startGame() {
    	setContentView(level);
    	view = level;
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
        view = title;
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

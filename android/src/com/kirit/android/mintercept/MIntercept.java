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

import com.kirit.android.Sounds;
import com.kirit.android.Vibrator;
import com.kirit.android.mintercept.views.Level;
import com.kirit.android.mintercept.views.Scene;
import com.kirit.android.mintercept.views.Title;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


public class MIntercept extends Activity {
    private Handler handler = new Handler();
    private Runnable runner = new Runnable() {
        public void run() {
            view.invalidate();
            handler.postDelayed(runner, 50);
        }
    };

    Title title;
    Level level;
    View view;

    public Vibrator vibrator;
    public Sounds sounds;

    public MIntercept() {
        vibrator = new Vibrator(this);
        sounds = new Sounds(this);
    }

    public void startGame() {
        setView(level);
    }
    public void endGame() {
        setView(title);
    }

    private void setView(Scene scene) {
        scene.reset();
        if (scene == level) {
            if (view.getHeight() > view.getWidth())
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            else
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        view = scene;
        setContentView(scene);
        view.requestFocus();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = new Title(this);
        level = new Level(this);
        // Load the sounds
        sounds.load(R.raw.blip); // This first sound is used for the toggle noise
        sounds.load(R.raw.city_destroyed);
        sounds.load(R.raw.missile_destroyed);
        sounds.load(R.raw.missile_ground);
        sounds.load(R.raw.missile_launch);
        // Make full screen without title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Set the view to title, but don't use the setView API for this as it does too much
        setView(title);
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

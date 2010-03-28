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


package com.kirit.android;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;


public class Sounds implements Setting {
    private boolean on;
    private Context context;
    private SoundPool pool;
    private int toggle;
    private HashMap<Integer,Integer> sounds = new HashMap<Integer, Integer>();

    public Sounds(Context c) {
        context = c;
        pool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        toggle = 0;
        set(true);
    }

    /**
     * Load a sound at the default priority
     */
    public void load(int resid) {
        sounds.put(resid, pool.load(context, resid, 1));
        if ( toggle == 0 )
            toggle = resid;
    }

    public void play(int resid) {
        if ( on && sounds.containsKey(resid) )
            pool.play(sounds.get(resid), 1f, 1f, 0, 0, 1f);
    }
    public void play(int resid, int loop) {
        if ( on && sounds.containsKey(resid) )
            pool.play(sounds.get(resid), 1f, 1f, 0, loop, 1f);
    }

    @Override
    public boolean get() {
        return on;
    }

    @Override
    public void set(boolean newValue) {
        on = newValue;
    }

    @Override
    public boolean toggle() {
        on = !on;
        play(toggle);
        return on;
    }
}

package com.kirit.android.mcommand;

import android.graphics.Canvas;
import com.kirit.mgl.Element;


public class Player extends Element {
	private Explosion [] explosions;

	public 	Player() {
		explosions = new Explosion [10];
		for ( int i = 0; i != explosions.length; ++i )
			explosions[i] = new Explosion();
	}
	
	public boolean tap(float x, float y) {
		if ( explosions[0].reset(x, y) ) {
			Explosion e = explosions[0];
			for ( int i = 0; i != explosions.length-1; ++i )
				explosions[i] = explosions[i+1];
			explosions[explosions.length-1] = e;
			return true;
		}
		return false;
	}

	public boolean draw(Canvas c) {
		boolean drawn = false;
		for ( int i = 0; i != explosions.length; ++i )
			if ( explosions[i].draw(c) && !drawn )
				drawn = true;
		return drawn;
	}
}

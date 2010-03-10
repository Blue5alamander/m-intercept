package com.kirit.android.mcommand;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.kirit.mgl.Element;
import com.kirit.mgl.Spectrum;


public class Explosions extends Element {
	private class Explosion extends Element {
		private boolean draw = false;
		private Spectrum colour = new Spectrum(0.0f, 0.75f, 0.75f);
		private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		private float cx, cy, inner_radius, outer_radius;
		
		public boolean reset(float x, float y) {
			if ( !draw ) {
				draw = true;
				cx = x; cy = y;
				inner_radius = 1;
				outer_radius = 1;
				return true;
			} else {
				return false;
			}
		}
		@Override
		public boolean draw(Canvas c) {
			if ( draw ) {
				if (inner_radius < 50) {
					paint.setColor(colour.next(50));
					c.drawCircle(cx, cy, outer_radius, paint);
					if ( outer_radius > 50 ) {
						paint.setColor(Color.BLACK);
						c.drawCircle(cx, cy, inner_radius, paint);
						++inner_radius;
					} else
						++outer_radius;
				} else
					draw = false;
				return true;
			} else {
				return false;
			}
		}
	}
	private Explosion [] explosions;

	public 	Explosions() {
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

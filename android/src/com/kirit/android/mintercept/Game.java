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

import java.util.LinkedList;
import java.util.Random;

import com.kirit.android.Element;
import com.kirit.android.NumberPanel;
import com.kirit.android.mintercept.Explosion;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;


public class Game extends Element {
	private boolean isover;
	private Player player;
	private Opponent opponent;
	private LinkedList<Explosion> explosions = new LinkedList<Explosion>();

	private NumberPanel score;
	public NumberPanel level, missiles;

	private BitmapDrawable gameover;
	private Rect location = new Rect();

	static public Random randomGenerator = new Random();

	public Game(Context context, View view) {
		isover = false;

		score = new NumberPanel(context, 8, R.drawable.score_prolog, R.drawable.score_numbers, Layer.CHROME);
		score.reset(10);

		level = new NumberPanel(context, 4, R.drawable.level_prolog, R.drawable.level_numbers, Layer.BACKGROUND);
		level.reset(1); level.setLeft(score.getWidth());

		missiles = new NumberPanel(context, 6, R.drawable.missiles_prolog, R.drawable.missiles_numbers, Layer.CHROME);
		missiles.reset(0);

		int prolog = Math.max(Math.max(score.getPrologHeight(), missiles.getPrologHeight()), level.getPrologHeight());
		score.setNumberOffset(prolog);
		level.setNumberOffset(prolog);
		missiles.setNumberOffset(prolog);

		player = new Player(context, view, this);
		opponent = new Opponent(context, view, this);

		gameover = (BitmapDrawable)context.getResources().getDrawable(R.drawable.gameover); 
	}

	public Player getPlayer() {
		return player;
	}

	/**
	 * Register an explosion with the game
	 */
	public void explosion(Explosion e) {
		explosions.add(e);
	}
	/**
	 * Return true if the location is inside an explosion
	 */
	public boolean inExplosion(float x, float y) {
		for ( Explosion e : explosions )
			if ( e.inside(x, y) )
				return true;
		return false;
	}
	/**
	 * When called the game is over
	 */
	public void over() {
		isover = true;
	}
	/**
	 * Allows us to determine if the game is over.
	 */
	public boolean isOver() {
		return isover;
	}
	/**
	 * Award (or subtract) points from the player
	 */
	public boolean award(int points) {
		if ( !isOver() && score.alter(points) <= 0 )
			over();
		return isOver();
	}

	@Override
	public boolean tick() {
		if ( level.alpha > 0 )
			--level.alpha;
		if ( !opponent.tick() && !isOver() ) {
			level.alpha = 255;
			level.alter(1);
			opponent.reset();
		}
		player.tick();
		return !isOver();
	}

	@Override
	public void draw(Canvas c, Layer layer) {
		if ( isover && layer == Layer.BACKGROUND ) {
			location.left = c.getWidth() / 2 - gameover.getMinimumWidth() / 2;
			location.top = c.getHeight() / 2 - gameover.getMinimumHeight();
			location.right = c.getWidth() / 2 + gameover.getMinimumWidth() / 2;
			location.bottom = c.getHeight() / 2;
			gameover.setBounds(location);
			gameover.draw(c);
		}

		score.draw(c, layer);
		missiles.setLeft(c.getWidth() - missiles.getWidth());
		missiles.draw(c, layer);
		level.setLeft((c.getWidth() - level.getWidth()) / 2);
		level.setTop(c.getHeight() / 3);
		level.draw(c, layer);

		opponent.draw(c, layer);
		player.draw(c, layer);
	}
}

package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.registry.registry;

public class Game extends ApplicationAdapter {

	public registry reg;

	@Override
	public void create () {

		reg = new registry();

	}

	@Override
	public void render () {

		// Logic
		reg.logic.update();
		reg.logic.deleteTextEffects();

		// Draw
		reg.draw.update();

	}
	
	@Override
	public void dispose () {
		//batch.dispose();
		//img.dispose();
	}
}

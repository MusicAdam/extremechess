package com.gearworks.extremechess;

import chess.Board;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Vector3;

public class ExtremeChess implements ApplicationListener {
   private PerspectiveCamera camera;
   private ModelBatch modelBatch;
   private Environment environment;
   private Board board;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new PerspectiveCamera(60, w, h);
		camera.position.set(0f, 5f, 10f);
		camera.lookAt(0f, 0f, 0f);
		camera.near = 0.1f;
		camera.far = 300f;
		
		modelBatch = new ModelBatch();
		
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.8f, 0.8f, 0.8f, 1.0f));
		
		board = new Board();
		board.initialize();
		
		Gdx.input.setInputProcessor(new ExtremeInput());
	}

	@Override
	public void dispose() {
		modelBatch.dispose();
	}

	@Override
	public void render() {
	  Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
      Gdx.gl.glClearColor(0, 0, 0, 1);
      Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
 
      camera.rotateAround(Vector3.Zero, new Vector3(0,1,0),1f);
      camera.update();
 
      // Like spriteBatch, just with models!  pass in the box Instance and the environment
      modelBatch.begin(camera);
      modelBatch.render(board.modelInstance(), environment);
      modelBatch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}

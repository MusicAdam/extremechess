package chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

import come.gearworks.extremechess.Entity;

public class Board implements Entity{
	public static final float size 	= 10;
	
	private Model boardModel;
	private ModelInstance boardModelInstance;
	
	public Board(){
		//Temporary model builder:
		Texture texture = new Texture(Gdx.files.internal("data/board.jpeg"), true);
		texture.setFilter(TextureFilter.MipMap, TextureFilter.Linear);
		ModelBuilder mb = new ModelBuilder();
		boardModel = mb.createBox(size, .5f, size,
				new Material(ColorAttribute.createDiffuse(Color.WHITE), new TextureAttribute(TextureAttribute.Diffuse, texture)),
				Usage.Position | Usage.Normal);
	}

	@Override
	public ModelInstance modelInstance() {
		return boardModelInstance;
	}

	@Override
	public void initialize() {
		boardModelInstance = new ModelInstance(boardModel);
	}

	@Override
	public void update() {
		
	}
}

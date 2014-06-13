package com.gearworks.game.entities;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.gearworks.Client;
import com.gearworks.Utils;
import com.gearworks.game.Entity;

public abstract class Piece extends Entity{
	protected Cell cell;
	protected String[] whiteStart; //Specify where the piece can start on white
	protected String[] blackStart; //Specify where the piece can start on black
	protected boolean active;      //True when the piece has been moved at least once
	protected boolean white;
	protected Sprite sprite; 	   //Image from the sprite sheet
	protected Texture texture; //Sheet of all pieces

	public static <T> Piece build(Class<T> type, boolean white, Cell cell, Client game){
		Constructor[] ctors = type.getDeclaredConstructors();
		Constructor ctor = null;
		Piece p = null;
		
		for(int i = 0; i < ctors.length; i++){
			ctor = ctors[i];
			if(ctor.getGenericParameterTypes().length == 0)
				break;
		}
		
		try{
			ctor.setAccessible(true);
			p = (Piece)ctor.newInstance(white, cell, game);
			p.cell = cell;
			p.game = game;
			p.white = white;
			p.initialize();
		}catch(InstantiationException e){
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		
		return p;
	}
	
	/* This constructor should only be used by the static Piece.build() function */
	public Piece(){
		super(null);
		cell = null;
		active = false;
		texture = new Texture(Gdx.files.internal("pieces.png"));
		white = true;
	}
	
	public Piece(boolean white, Cell cell, Client cRef) {
		super(cRef);
		this.cell = cell;
		active = false;
		texture = new Texture(Gdx.files.internal("pieces.png"));
		this.white = white;
	}
	
	public int x(){
		return cell.x();
	}
	
	public int y(){
		return cell.y();
	}
	
	
	public boolean moveTo(int x, int y){
		return false;
	}
	
	public void dispose(){
		cell = null;
		texture.dispose();
	}
	
	public void alignToCell(){
		position(new Vector2(cell.position().x + cell.size().x/2 - size().x/2, cell.position().y + cell.size().y/2  - size().y/2));
	}
	
	@Override
	public void position(Vector2 p){
		super.position(p);
		sprite.setPosition(p.x, p.y);
	}
	
	@Override
	public void render(SpriteBatch b, ShapeRenderer r){
		if(sprite == null) return;
		
		b.begin();
			sprite.draw(b);
		//b.draw(texture, 0, 0);
		b.end();
	}
	
	//Should initialize the default start positions and sprite
	abstract void initialize();
	//Returns true if this piece can move to x, y
	abstract boolean validMove(int x, int y);
	//Returns true if this piece can capture on x, y
	abstract boolean validCapture(int x, int y);
	//Returns the PGN prefix for the piece (i.e. N for knigt, Q - queen)
	abstract String notationPrefix();
	//Returns true if the start position is valid
	boolean validStart(boolean white, int x, int y){
		String notation = Utils.indexToNotation(x, y);
		if(white){
			for(String pos : whiteStart){
				if(pos.equals(notation)) return true;
			}
		}else{
			for(String pos : blackStart){
				if(pos.equals(notation)) return true;
			}
		}
		
		return false;
	}
	

	public Cell cell(){ return cell; }
	public void cell(Cell cell){ this.cell = cell; }
	public void setActive(){ active = true; }
	public boolean color(){ return white; }
}

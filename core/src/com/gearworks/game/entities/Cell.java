package com.gearworks.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.gearworks.Client;
import com.gearworks.Utils;
import com.gearworks.game.Entity;

public class Cell extends Entity{
	private Piece piece;
	private int x, y;
	private String notation;
	private boolean validatePosition;
	private Color color;
	
	public Cell(int x, int y, Client ref){
		super(ref);
		this.x = x;
		this.y = y;
		notation = Utils.indexToNotation(x, y);
		invalidate();
		
		if(x % 2 == 0){
			if(y % 2 == 0){
				color = new Color(.7f, .7f, .7f, 1);	
			}else{		
				color = Color.WHITE;	
			}
		}else{
			if(y % 2 == 0){
				color = Color.WHITE;
			}else{
				color = new Color(.7f, .7f, .7f, 1);					
			}
		}
		
		size(Board.SIZE/8f, Board.SIZE/8f);
	}
	
	public int x(){ return x; }
	public int y(){ return y; }
	
	@Override
	public void render(SpriteBatch b, ShapeRenderer r){
		Utils.fillRect(r, color, position().x, position().y, size().x, size().y);
	}
	
	@Override
	public String toString(){
		return "[" + x + ", " + y + "] - " + notation;
	}
	
	@Override 
	public void update(){
		if(validatePosition){
			position(Utils.calculateCellPosition(this));
			if(piece != null){
				piece.alignToCell();
			}
		}
	}
	
	@Override
	public void position(Vector2 pos){
		super.position(pos);
		validatePosition = false;
	}
	
	public void empty(){ 
		piece = null; 
	}
	
	public void invalidate(){ validatePosition = true; }
	public Piece piece(){ return piece; }
	public void piece(Piece p){ piece = p; piece.alignToCell(); }
}

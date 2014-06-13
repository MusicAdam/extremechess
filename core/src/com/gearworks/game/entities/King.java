package com.gearworks.game.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gearworks.Client;

public class King extends Piece {

	public King(boolean white, Cell cell, Client cRef) {
		super(white, cell, cRef);
	}

	@Override
	void initialize() {
		whiteStart = new String[]{"e1"};
		blackStart = new String[]{"e8"};
		
		if(white){
			sprite = new Sprite(texture, 7, 65, 47, 50);
		}else{
			sprite = new Sprite(texture, 127, 124, 47, 50); 		
		}
		size(47, 50);
	}

	@Override
	boolean validMove(int x, int y) {
		return (x == x() + 1 ||
				x == x() - 1 ||
				y == y() + 1 ||
				y == y() - 1);
	}

	@Override
	boolean validCapture(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	String notationPrefix() {
		return "K";
	}
	
	@Override
	public String toString(){
		return "King";
	}

}

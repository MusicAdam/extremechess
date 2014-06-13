package com.gearworks.game.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gearworks.Client;
import com.gearworks.Utils;

public class Pawn extends Piece {

	public Pawn(boolean white, Cell cell, Client cRef) {
		super(white, cell, cRef);
	}

	@Override
	void initialize() {
		whiteStart = new String[]{ "a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2" };
		blackStart = new String[]{ "a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7" };
		
		if(white){
			sprite = new Sprite(texture, 134, 9, 31, 46);
		}else{
			sprite = new Sprite(texture, 14, 129, 31, 46); 		
		}
		size(31, 46);
	}

	@Override
	boolean validMove(int x, int y) {
		int sign = (white) ? 1 : -1;
		return (x == x() && 
				y == y() + 1 * sign ||
				(y == y() + 2 * sign && !active));
	}

	@Override
	boolean validCapture(int x, int y) {
		int sign = (white) ? 1 : -1;
		return ((x == x() + 1 && y == y() + 1 * sign) ||
				(x == x() - 1 && y == y() + 1 * sign));
	}

	@Override
	String notationPrefix() {
		return "";
	}
	
	@Override
	public String toString(){
		return "Pawn";
	}
}

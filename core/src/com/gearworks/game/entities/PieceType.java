package com.gearworks.game.entities;

//Piece type is a wapper for a java Class type, used to identify piece types by their class (Pawn, Rook, King, etc)
public class PieceType<T> {
	
	private Class<T> type;
	
	public PieceType(Class<T> type){
		this.type = type;
	}
	
	public Class<T> get(){ return type; }
}

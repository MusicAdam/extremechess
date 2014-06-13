package com.gearworks.game.entities;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.gearworks.Client;
import com.gearworks.game.Entity;

public class Board extends Entity{
	public static float SIZE = Client.V_HEIGHT - 100;
	
	private Cell data[][];
	private ArrayList<PieceType> pieceTypes;
	
	public static boolean movePieceToCell(Piece piece, Cell cell){
		boolean canMove = false;
		boolean isCapture = false;
		
		if(cell.piece() == null){
			if(piece.validMove(cell.x(), cell.y())){
				canMove = true;
			}
		}else if(cell.piece().color() != piece.color() && piece.validCapture(cell.x(), cell.y())){
			canMove = true;
			isCapture = true;
		}
		
		if(canMove){
			if(isCapture){
				cell.piece().cell(null);
				cell.piece().position(new Vector2(0, 0));
			}
			piece.cell().empty();
			piece.cell(cell);
			cell.piece(piece);
			piece.setActive();
			
			
		}
		
		return canMove;
	}
	
	public Board(Client ref){
		super(ref);
		
		//Register the pieces
		pieceTypes = new ArrayList<PieceType>();
		pieceTypes.add(new PieceType<Pawn>(Pawn.class));
		pieceTypes.add(new PieceType<King>(King.class));
		
		data = new Cell[8][8];
		position(new Vector2(Gdx.graphics.getWidth()/2 - SIZE/2, Gdx.graphics.getHeight()/2 - SIZE/2));
		size(new Vector2(SIZE, SIZE));
		
		for(int x = 0; x < 8; x++){
			for(int y = 0; y < 8; y++){
				data[x][y] = (Cell)game.spawn(new Cell(x, y, ref));
			}
		}
	}
	
	//Sets all piece positions to starting positions
	public void reset(){
		for(int x=0; x < 8; x++){
			for(int y=0; y < 8; y++){
				data[x][y].empty();
								
				//Find which piece goes here
				int start; //Indicates what starts here 1 = white, 0 = black, -1 = nothing
				
				if(y == 0 || y == 1){ //White starts on the 1 or 2 rank
					start = 1;
				}else if(y == 6 || y == 7){ //Black starts on 7 and 8 rank
					start = 0;
				}else{
					start = -1;
				}
				
				System.out.println(data[x][y] + " is a " + start + " start");
				
				if(start != -1){
					for(PieceType<?> pt : pieceTypes){
						boolean white = (start == 1) ? true : false;
						Piece piece = (Piece) Piece.build(pt.get(), white, data[x][y], game);
						if(piece.validStart(white, x, y)){
							System.out.println("Created a " + start + " " + piece + " for " + data[x][y]);
							data[x][y].piece(piece);
							game.spawn(piece);
						}else{
							piece = null;
						}
					}
				}
			}
		}
	}
	
	public Cell[][] cells(){
		return data;
	}
}

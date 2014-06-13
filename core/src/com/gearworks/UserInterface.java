package com.gearworks;


import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.gearworks.game.Entity;
import com.gearworks.game.entities.Board;
import com.gearworks.game.entities.Cell;
import com.gearworks.game.entities.Piece;
import com.gearworks.state.GameState;

public class UserInterface implements InputProcessor{
	private Client game;
	
	private Piece selected;
	
	float selectionPadding = 0f;
	
	//Takes a mouse coordinate and returns screen coordinates, does not alter original vector
	public static Vector2 mouseToScreen(Vector2 coord, Camera cam){
		Vector3 screenCoord = new Vector3(coord.x, coord.y, 0);
		cam.unproject(screenCoord);
		return new Vector2(screenCoord.x, screenCoord.y);
	}
	
	public UserInterface(Client game){
		this.game = game;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(button == 1){
			Vector2 mousePos = mouseToScreen(new Vector2(screenX, screenY), game.camera());
			
			//Check for Cell clicks
			ArrayList<Cell> found = (ArrayList<Cell>)Utils.findCellsAtPoint(game.board().cells(), mousePos);
			for(Cell cell : found){
				System.out.println(cell);
				System.out.println("\tPiece: " + cell.piece());
			}
		}
		return true; //This could interfere with menus in the future, unless this class handles the clicks...
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(button == 0){
			Vector2 mousePos = mouseToScreen(new Vector2(screenX, screenY), game.camera());
			
			//Check for Cell clicks
			ArrayList<Cell> found = (ArrayList<Cell>)Utils.findCellsAtPoint(game.board().cells(), mousePos);
			System.out.println("Found " + found.size());
			for(Cell cell : found){
				if(selected == null){
					selected = cell.piece();
					System.out.println(selected + " is selected");
				}else{
					if(!Board.movePieceToCell(selected, cell))
						selected.alignToCell();
					selected = null;
				}
			}
		}
		return true; //This could interfere with menus in the future, unless this class handles the clicks...
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
	public void render(SpriteBatch batch, ShapeRenderer renderer){
		renderer.setProjectionMatrix(game.camera().combined);
		renderer.identity();
	}
	
	public void update(){
		if(selected != null){
			Vector2 mousePos = mouseToScreen(new Vector2(Gdx.input.getX(), Gdx.input.getY()), game.camera());
			selected.position(new Vector2(mousePos.x - selected.size().x/2, mousePos.y - selected.size().y/2));
		}
	}

}

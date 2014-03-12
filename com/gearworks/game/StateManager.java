package com.gearworks.game;

import java.util.ArrayList;
import java.util.List;

public class StateManager {
	public StateManager(){
	}
	
	public void tick(){
		if(myState != null)
			myState.tick();
	}
	
	public void setState(State newState){
		if(newState.canEnterState()){
			myState.onExitState();
			myState = newState;
			myState.onEnterState();
		}
	}
	
	private State myState;	
}

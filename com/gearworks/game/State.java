package com.gearworks.game;

import com.badlogic.gdx.graphics.g3d.ModelBatch;

public abstract class State {
	public abstract void onEnterState();
	public abstract void onExitState();
	public abstract boolean canEnterState();
	public abstract boolean tick();
	public abstract boolean render(ModelBatch mb);
	private Game gameReference;
}

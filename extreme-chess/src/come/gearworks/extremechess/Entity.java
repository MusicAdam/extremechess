package come.gearworks.extremechess;

import com.badlogic.gdx.graphics.g3d.ModelInstance;

public interface Entity {
	public ModelInstance modelInstance();
	public void initialize();
	public void update();
}

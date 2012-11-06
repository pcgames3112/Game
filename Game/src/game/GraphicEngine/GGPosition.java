package game.GraphicEngine;

/** Not a subclass of GGObject because of reasons */
public class GGPosition extends GGObject{
	public float x = 0.f;
	public float y = 0.f;
	public float z = 0.f;
	
	/** There should be factory for every children from this class named create. */
	public static GGPosition create(float x, float y, float z) {
		GGPosition newObject = new GGPosition(x, y, z);
		return newObject;
	}
	
	public static GGPosition create() {
		GGPosition newObject = new GGPosition(0.f, 0.f, 0.f);
		return newObject;
	}
	
	protected GGPosition(float x, float y, float z) {
		super();
	}
}

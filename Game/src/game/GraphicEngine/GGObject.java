package game.GraphicEngine;

/** The basic object for everything in the engine. */
public class GGObject {
	
	/** There should be factory for every children from this class named create. */
	public static GGObject create() {
		GGObject newObject = new GGObject();
		return newObject;
	}

	
	/** The class doesn't need a public constructor because it has a factory. 
	 * You should use protected instead of private because their subclasses should call their super-constructor */
	protected GGObject() {

	}
}

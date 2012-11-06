package game.GraphicEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.PointerBuffer;
import org.lwjgl.opengl.Drawable;

/** The basic graphical object */
public class GGDrawable extends GGNode {
	
	public static GGDrawable create() {
		GGDrawable newDrawable = new GGDrawable();
		
		if(newDrawable.init()) {
			return newDrawable;
		}
		
		return null;	
	}
	
	private boolean init() {
		// TODO Auto-generated method stub
		return false;
	}

	private GGDrawable() {
		// TODO Auto-generated constructor stub
	}
	
	public void draw(){}
	
	
}

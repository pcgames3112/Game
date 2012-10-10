package game;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("OK, lets go");
        
        Game game = new Game();
        game.start();
        
    }
    
    protected void start() {
    	try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
    	
    	// init OPGL here
    	
    	while(!Display.isCloseRequested()) {
    		
    		// render stuff here/
    		
    		Display.update();
    	}
    	
    	Display.destroy();
    }
    
    
}

package game;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Game {

	boolean finished = false;
	
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
    	
    	
    	
    	while(!finished) {
    		
    		if(Display.isCloseRequested()) {
    			finished = true;
    		}
    		
    		handleKeyEvents();
    		//
    		
    		Display.update();
    	}
    	
    	Display.destroy();
    	System.out.println("Quit");
    }

	protected void handleKeyEvents() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
				finished = true;
			}
		}
		
	}
    
    
}

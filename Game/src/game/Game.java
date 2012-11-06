package game;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Vector;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;


// Initialize all the stuff
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
			
			
			
			
			Display.setTitle("GL-version: " + Display.getVersion());
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
    	
    	// init OPGL here
    	
    	
    	
    	// init OpenGL
    	
    	byte indexData[] = {0,1,2, 2,1,3};
    	
    	float vertexData[] = {-0.5f,-0.5f,0.0f, 0.5f,-0.5f,0.0f, -0.5f,0.5f,0.0f, 0.5f,0.5f,0.0f,};
     
    	ByteBuffer indexBuffer = BufferUtils.createByteBuffer(indexData.length);
    	FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertexData.length);
    	
    	indexBuffer.put(indexData);
    	vertexBuffer.put(vertexData);
    	
    	indexBuffer.flip();
    	vertexBuffer.flip();

    	
    	while (!Display.isCloseRequested()) {
    	    // Clear the screen and depth buffer
    	    //GL20.glC(GL12.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	
    		
    		handleKeyEvents();
    		
    		glColor3f(1.f, 1.f, 1.f);
    		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
    		glEnableClientState(GL_VERTEX_ARRAY);
    		glVertexPointer(3, 0, vertexBuffer);
    		glDrawElements(GL_TRIANGLES, indexBuffer);
    		
    		
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

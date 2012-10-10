package game.connection;

//import java.io.BufferedReader;
import java.io.Console;
import java.io.DataOutputStream;

public class WritingThread extends Thread {

    protected boolean running;
    private DataOutputStream out;
    private Console console;

    @Override
    public void run() {
        while (running) {
            try {
                out.writeUTF(console.readLine("What to Write?"));
            } catch (Exception e) {
                e.printStackTrace();
                running = false;
            }
        }
    }

    public WritingThread(DataOutputStream out, Console console) {
        running = true;
        this.out = out;
        this.console = console;
    }
}

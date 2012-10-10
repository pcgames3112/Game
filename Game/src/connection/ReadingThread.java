package connection;

import game.test.TestConnectionGUI;
import java.io.DataInputStream;
import java.net.SocketTimeoutException;

public class ReadingThread extends Thread {

    protected boolean running;
    private DataInputStream in;
    private TestConnectionGUI ncc;

    @Override
    public void run() {
        while (running) {
            try {
                String text = in.readUTF();
                System.out.println(text);
                ncc.addText(text);
            } catch (SocketTimeoutException e) {
                System.out.println("Timeout!");
            } catch (Exception e) {
                e.printStackTrace();
                running = false;
            }
        }
    }

    public ReadingThread(DataInputStream in, TestConnectionGUI ncc) {
        running = true;
        this.in = in;
        this.ncc = ncc;
    }
}

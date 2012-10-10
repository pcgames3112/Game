package game.connection;

import game.test.TestConnectionGUI;
import java.io.DataInputStream;
import packets.*;

import java.nio.charset.Charset;

public class ReadingThread extends Thread {

    protected boolean running;
    private DataInputStream in;
    private TestConnectionGUI ncc;
    private PacketReader preader;

    @Override
    public void run() {
        while (running) {
            try {
                //String text = in.readUTF();
                if (preader.ReadData(in)) {
                    byte[] bytes = preader.GetData();
                    String text = new String(bytes, Charset.forName("UTF-8"));
                    System.out.println(text);
                    ncc.addText(text);
                }
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

        preader = new PacketReader();
    }
}

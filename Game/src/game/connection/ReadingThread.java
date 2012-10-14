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
                //Try reading Data from Stream
                int ret = preader.ReadData(in);
                if (ret == 0) {
                    //Received good Packet
                    byte[] bytes = preader.GetData();
                    String text = new String(bytes, Charset.forName("UTF-8"));
                    System.out.println(text);
                    ncc.addText(text);
                } else if(ret == -1){
                    //Bad Connection, close it!!
                    //TODO: Connection Fail Handling in Client
                } else {
                    //Received bad Packet, ignore it
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

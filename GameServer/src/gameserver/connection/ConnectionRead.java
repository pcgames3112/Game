package gameserver.connection;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import packets.PacketReader;

public class ConnectionRead extends Thread {

    protected boolean running;
    private DataInputStream in;
    private Connection client;
    
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
                    client.Receive(text);
                }
            } catch (Exception e) {
                e.printStackTrace();
                client.close();
            }
        }
    }

    public ConnectionRead(DataInputStream in, Connection client) {
        this.in = in;
        this.client = client;
        preader = new PacketReader();
        running = true;
        
        
    }
    
    public void close(){
        running = false;
        try {
            in.close();
            
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}

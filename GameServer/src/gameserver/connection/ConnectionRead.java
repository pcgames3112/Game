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
                //Try reading Data from Stream
                int ret = preader.ReadData(in);
                if (ret == 0) {
                    //Received good Packet
                    byte[] bytes = preader.GetData();
                    String text = new String(bytes, Charset.forName("UTF-8"));
                    System.out.println(text);
                    client.Receive(text);
                } else if(ret == -1){
                    //Bad Connection, close it!!
                    client.server.removeClient(client);
                } else {
                    //Received bad Packet, ignore it
                }
            } catch (Exception e) {
                e.printStackTrace();
                client.server.removeClient(client);
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

package gameserver.connection;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;

public class ConnectionRead extends Thread {

    protected boolean running;
    private DataInputStream in;
    private Connection client;

    @Override
    public void run() {
        while (running) {
            try {
                String text = in.readUTF();
                System.out.println(text);
                client.Receive(text);
            } catch (SocketTimeoutException e) {
                System.out.println("ReadTimeout!");
            } catch (Exception e) {
                e.printStackTrace();
                client.close();
            }
        }
    }

    public ConnectionRead(DataInputStream in, Connection client) {
        this.in = in;
        this.client = client;
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

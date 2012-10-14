package gameserver.connection;

import gameserver.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Connection extends Thread{
    public ArrayList<String> messages = new ArrayList<String>();
    //public String message;
    public boolean running;
    
    public Socket socket;
    public String username;
    
    
    protected GameServer server;
    
    private ConnectionRead reader;
    private DataOutputStream out;
    
    public Connection(Socket socket, String username, GameServer server){
        this.socket = socket;
        try{
            this.server = server;
            this.username = username;
            reader = new ConnectionRead(new DataInputStream(socket.getInputStream()), this);
            out = new DataOutputStream(socket.getOutputStream());
            running = true;
        } catch(IOException e){
            e.printStackTrace();
            close();
        }
    }
    
    @Override
    public void run(){
        while(running){
            boolean b = true;
            if (messages.size() > 0){
                //System.out.println("Send->" + System.currentTimeMillis());
                synchronized(this){
                    String text = messages.get(0);
                    //send to client
                    try{
                        //out.writeUTF(text);
                        out.writeByte(42);
                        out.writeShort(1);
                        out.writeShort(text.length());
                        out.write(text.getBytes(Charset.forName("UTF-8")));
                        //out.writeUTF("Eine Nachricht");
                        //out.flush();
                        messages.remove(text);
                        //message = "";
                    } catch (IOException e){
                        e.printStackTrace();
                        close();
                    }
                }
            }
        }
    }
    
    public void Receive(String text){
        if (!username.equals("")){
            server.broadcast("[" + username + "] " + text);
        } else {
            username = text;
            messages.add("[Server] You're now called " + username);
        }
    }
    
    public void close(){
        try{
            running = false;
            if (out != null) {
                out.close();
            }
            if (reader != null) {
                reader.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void addMessage(String text) {
        messages.add(text);
        //message = text;
    }
    
    public void startReader(){
        reader.start();
    }
}

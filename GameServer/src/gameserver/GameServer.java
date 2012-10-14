package gameserver;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import gameserver.connection.*;

public class GameServer extends Thread {

    private static final int MAXCLIENTS = 5;
    private static final String WELCOMEMSG = "Welcome on this Testserver!\nCurrently it should support " + MAXCLIENTS + " Clients.\n\nPlease Enter your Username.";
    private static final int PORT = 6666;
    
    private ServerSocket serverSocket;
    private boolean running;
    
    private ArrayList<Connection> clients = new ArrayList<Connection>();

    public GameServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        //serverSocket.setSoTimeout(100000);
        running = true;
    }
    
   @Override
    public void run() {
        while (running) {
            try {
                if (clients.size() < MAXCLIENTS){
                    System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
                    Socket client = serverSocket.accept();
                    try{
                        Connection c = new Connection(client, "", this);
                        c.addMessage(WELCOMEMSG);
                        c.startReader();
                        c.start();
                        clients.add(c);
                        broadcast("[Server] New Connection: " + c.socket.getRemoteSocketAddress());
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Massive Error: Server Shutting Down!");
                running = false;
                removeAllClients();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Thread t = new GameServer(PORT);
            t.start();
        } catch (BindException e){
            System.out.println("Can't bind to Port " + PORT + "!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void broadcast(String text){
        Connection[] c = clients.toArray(new Connection[clients.size()]);
        for (int i = 0; i < c.length; i++) {
            c[i].addMessage("[" + System.currentTimeMillis() + "]" + text);
        }
    }
    
    public void removeClient(Connection client){
        if (client != null){
            client.close();
            broadcast("[Server] Connection Lost: " + client.socket.getRemoteSocketAddress());
        }
    }
    
    public void removeAllClients(){
        Connection[] c = clients.toArray(new Connection[clients.size()]);
        for (int i = 0; i < c.length; i++) {
            c[i].close();
        }
    }
}

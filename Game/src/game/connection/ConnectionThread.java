package game.connection;

import game.test.TestConnectionGUI;
import java.io.*;
import java.net.*;

import java.nio.charset.Charset;

public class ConnectionThread extends Thread {

    private Socket clientSocket;
    private String hostName;
    private int Port;
    private boolean running;
    private ReadingThread reader;
    private TestConnectionGUI _parent;
    //private WritingThread writer;
    OutputStream outToServer;
    DataOutputStream out;
    InputStream inFromServer;
    DataInputStream in;

    public ConnectionThread(String hostname, int port, TestConnectionGUI p) throws IOException {
        hostName = hostname;
        Port = port;
        _parent = p;
        running = true;
    }

    @Override
    public void run() {
        while (running) {
            try {
                //Connect
                System.out.println("Connecting to " + hostName + " on port " + Port);
                _parent.addText("Connecting to " + hostName + " on port " + Port);
                clientSocket = new Socket(hostName, Port);

                //get Streams
                outToServer = clientSocket.getOutputStream();
                out = new DataOutputStream(outToServer);
                inFromServer = clientSocket.getInputStream();
                in = new DataInputStream(inFromServer);
                //BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
                //Console console = System.console();
                //prepare Read and Write Threads
                reader = new ReadingThread(in, _parent);
                //writer = new WritingThread(out, console);
                //say Hello
                //out.writeUTF("Hello");
                //Wait for Welcome Message
                //String text = in.readUTF();
                //System.out.println(text);
                //_parent.addText(text);

                reader.start();
                //writer.start();
            } catch (SocketException e) {
                if (reader != null) {
                    reader.running = false;
                }
                /*if (writer != null) {
                 writer.running = false;
                 }*/
                running = false;
                System.out.println("Can't Connect to " + hostName + ":" + Port);
                _parent.addText("Can't Connect to " + hostName + ":" + Port);
            } catch (IOException e) {
                if (reader != null) {
                    reader.running = false;
                }
                /*if (writer != null) {
                 writer.running = false;
                 }*/
                e.printStackTrace();


            }
            if (reader != null) {// && writer != null) {
                while (reader.running) {// && writer.running) {
                    //Wait for Crash
                }
            }
            //socket died
            if (reader != null) {
                reader.running = false;
            }
            /*if (writer != null) {
             writer.running = false;
             }*/
        }
    }

    public void Send(String text) {
        try {
            //out.writeUTF(text);
            out.writeByte(42);
            out.writeShort(1);
            byte[] bytes = text.getBytes(Charset.forName("UTF-8"));
            out.writeShort(bytes.length);
            out.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
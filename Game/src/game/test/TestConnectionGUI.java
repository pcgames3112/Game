package game.test;

import connection.ConnectionThread;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.text.Element;

public class TestConnectionGUI implements ActionListener{

    //constants
    public static final int MAXLINES = 20;
    //frame
    private JFrame Frame;
    private JPanel Panel2;
    private JButton Button;
    private JTextArea outputField;
    private JScrollPane scrollPane;
    private JTextField inputField;
    private JPanel Panel1;
    private JLabel Label = new JLabel("Name here: ");
    //self
    private static TestConnectionGUI client;
    //Network
    private ConnectionThread t;

    public TestConnectionGUI() {
        //Initialise Components
        Frame = new JFrame("NetworkChatClient");

        Panel1 = new JPanel(new FlowLayout());
        outputField = new JTextArea(10, 27);
        outputField.setEditable(false);
        outputField.setLineWrap(true);
        outputField.setWrapStyleWord(true);
        
        scrollPane = new JScrollPane(outputField);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        Panel2 = new JPanel(new FlowLayout());
        inputField = new JTextField(20);
        inputField.addActionListener(this);
        Button = new JButton("Senden");
        Button.addActionListener(this);

        //add Components to Frame
        Panel1.add(scrollPane);

        //Panel2.add(Label);
        Panel2.add(inputField);
        Panel2.add(Button);

        Frame.add(Panel1);
        Frame.add(Panel2);

        //Initialize and show Frame
        Frame.setLayout(new FlowLayout());
        Frame.setSize(350, 260);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setVisible(true);
        
        
        String serverName = "andi-pc";//args[0];
        int port = 6666; //Integer.parseInt(args[1]);
        try {
            t = new ConnectionThread(serverName, port, this);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void addText(String text){
        outputField.append(text + "\n");
            outputField.setCaretPosition(outputField.getDocument().getLength());
            if (outputField.getLineCount() > MAXLINES) {
                Element root = outputField.getDocument().getDefaultRootElement();
                Element first = root.getElement(0);
                try {
                    outputField.getDocument().remove(first.getStartOffset(), first.getEndOffset());
                } catch (Exception e) {
                }
            }
    }

    public static void main(String[] args) {
        client = new TestConnectionGUI();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == Button || ae.getSource() == inputField){
            //addText(inputField.getText());
            t.Send(inputField.getText());
            inputField.setText("");
        }
    }
}

package jcanpoll;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observer;
import javax.swing.SwingUtilities;

public class MessagePipeline implements Runnable {
    
    private ArrayList<Observer> observers;
    private String oldMessage = "";
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    //private OutputStreamWriter writer;
    
    private String currentMessage = "";
    public  String selectedMessage ="";     // GUI: Formatted payload
    public  String selectedMessage2 = "";   // GUI: DLC field
    public  String selectedMessage3 = "";   // GUI: Payload field (raw hex)
    public  static Canmsg2j cmsg;
    
    private static MessagePipeline instance = null;
    private boolean running = false;
    private boolean connected = false;
    private int curMessageIndex = 0;
    
    boolean debugMode = false;

    
    public static MessagePipeline getInstance()
    {
        if(instance == null)
        {
            instance = new MessagePipeline();
            instance.observers = new ArrayList<>();
            instance.init();
        }
        return instance;
    }
   
    
    public void init()
    {
        running = true;
    }
    
    public boolean connect(String address, int port)
    {
        try {
            socket = new Socket(address, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            connected = true;
            System.out.println("Connected");
            return true;
        } catch (Exception e) {
            // e.printStackTrace();
            return false;
        } 
    }
    
    public void disconnect()
    {
        connected = false;
        currentMessage = "";
    }
    
    public void attach(Observer ob)
    {
        observers.add(ob);
    }
    
    private void notifyObservers()
    {
        //System.out.println("Notifying");
        /*for(Observer ob : observers)
        {
            ob.update(currentMessage);
        }*/
    }
    
    public void TEMPReadFromSocket()
    {
    }
    
    public void ReadFromSocket()
    {
        try {
            String s = reader.readLine();
            currentMessage = s;

        Canmsg2j cmsg = new Canmsg2j();
        cmsg.convert_msgtobin(s);
        // Check if this the selected CAN ID
        if (cmsg.id == NewJFrame.canidrcvi){
            PayloadDisplay pld = new PayloadDisplay();
            s = pld.toString(cmsg, NewJFrame.cmi2);
            selectedMessage  = s;    // Save for Swing update
            selectedMessage2 = String.format("%d",cmsg.dlc);
            selectedMessage3 = "";
            for (int i = 6; i < cmsg.dlc+6; i++){
                selectedMessage3 = selectedMessage3 + 
                        String.format("%02X ",cmsg.pb[i]);
            }
            UpdateText11(); // Set invokeLater to run update
            System.out.format("ReadFromSocket: %s\n",s);
        }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
public void UpdateText11(){
    
   SwingUtilities.invokeLater(
      new Runnable(){
      @Override
      public void run(){
            NewJFrame.setText11(
                    selectedMessage,
                    selectedMessage2,
                    selectedMessage3);
      }
      });     
 
}
    
    public void TEMPWriteToSocket(String s)
    {
        try {
            writer.write(s);
        } catch (IOException ex) {
            //ex.printStackTrace();
        }
    }
    
    public void WriteToSocket(String s)
    {
        try {
            writer.write(s);
            writer.flush();

        } catch (IOException ex) {
            ex.printStackTrace();
        }       
    }
    
    public void run()
    {
        while(running)
        {
            if(connected)
            {
                ReadFromSocket();
            }
        }
    }
    
    public void close()
    {
        running = false;
        try {
            socket.close();
            writer.close();
            reader.close();
        } catch (IOException ex) {
        }
    }
}

import java.awt.*; // IT IS A SERVER
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
public class Gopi extends Frame implements Runnable,ActionListener{
    TextField disp;
    TextField textField;
    TextArea textArea;
    Button send;

    
            ServerSocket serverSocket;
            Socket socket;

            DataInputStream dataInputStream;
            DataOutputStream dataOutputStream;

            Thread chat;

        Gopi(){
            disp = new TextField( "                         WELCOME    TO    CHATAPP [ Gopi ]  ");
            disp.setBounds(70,50,360,35);
            disp.setEditable(false);
            textField = new TextField("YOUR MSG!");
            textField.setBounds(140,140,210,35);
            textArea = new TextArea();
            textArea.setBounds(125,210,250,120);
            send = new Button("Send");
            send.setBounds(200,360,100,35);

            send.addActionListener(this);

            try{
                serverSocket = new ServerSocket(12000);
                socket = serverSocket.accept();

                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

            }
            catch(Exception e){

            }
            add(disp);
            add(textField);
            add(textArea);
            add(send);

            chat = new Thread(this);
            chat.setDaemon(true);
            chat.start();


            setSize(500,500);
            setTitle("GOPI");
            setLayout(null);
            setVisible(true);
        }

        public void actionPerformed(ActionEvent e){
            String msg = textField.getText();
            textArea.append("you : "+msg+"\n");
            textField.setText("");

            try{
                dataOutputStream.writeUTF(msg);
                dataOutputStream.flush();
            }
            catch(Exception ex){  //already e will be used over here.......

            }
        }
        
    public static void main(String[] args){
            new Gopi();

    }
    public void run(){
        while(true){
            try{
                String msg = dataInputStream.readUTF();
                textArea.append("Gokul Says : "+msg+"\n");
            }
            catch(Exception e){
                
            }
        }

    }
}
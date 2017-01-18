import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
public class VigGUI extends JFrame {
	private JPanel panel;
	private JButton sendButton;
	private JTextField send;
	private JTextField shift;
	private JLabel response;
	private JLabel textOne;
	private JLabel textTwo;
	private String stringSend, stringShift;
	
	public static void main(String[]args){
		VigGUI myWindow= new VigGUI();
	}
	public VigGUI(){
		
		//declaring GUI elements
		panel=new JPanel();
		sendButton=new JButton("Send Message");
		shift=new JTextField(10);
		send=new JTextField(10);
		response=new JLabel();
		textOne=new JLabel("Key:");
		textTwo=new JLabel("Message:");
		
		panel.add(textOne);
		panel.add(shift);
		panel.add(textTwo);
		panel.add(send);
		panel.add(sendButton);
		panel.add(response);
		add(panel);
		setTitle("Cyptography Project");
		setSize(500,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		sendButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e){
                if(e.getSource() == sendButton){
                	stringShift = shift.getText();
                    stringSend = send.getText();
                    try {
                        response.setText(sendMessage(stringSend,stringShift));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }
            }
        });
		
	}
	
	public String sendMessage(String message, String s) throws IOException {

        String response;

        //creates a new socket 
        Socket clientSocket = new Socket("localhost", 6789);

        // Wrapping the clientSocket in an output stream object
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        //Wrapping the clientSocket input stream in a buffered reader
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        //sending the strings to the server, message, string key, and int determining cipher type
        outToServer.writeBytes(VigenereCipher.encrypt(message,s) + '\n');
        outToServer.writeBytes((s) + '\n');
        outToServer.writeBytes(""+1+'\n');
        
        //reading from there server
        response = inFromServer.readLine();

        System.out.println("recieved encrypted message: " + response);

        response = VigenereCipher.decrypt(response,s);

        //closing the connection to the server
        clientSocket.close();

        return response;
    }
}



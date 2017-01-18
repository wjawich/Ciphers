import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.*;
import java.net.*;
import java.awt.*;

public class GUI extends JFrame {
	//declaring variables 
	private JPanel panel;
	private JButton sendButton;
	private JTextField send;
	private JTextField shift;
	private JLabel response;
	private JLabel textOne;
	private JLabel textTwo;
	private String stringSend, stringShift;
	private int intShift=0;
	
	//main method calling GUI
	public static void main(String[]args){
		GUI myWindow= new GUI();
	}
	
	public GUI(){
		
		//creating GUI objects
		panel=new JPanel();
		sendButton=new JButton("Send Message");
		shift=new JTextField(2);
		send=new JTextField(10);
		response=new JLabel();
		textOne=new JLabel("Shift:");
		textTwo=new JLabel("Message:");

		//adding elements to panel
		panel.add(textOne);
		panel.add(shift);
		panel.add(textTwo);
		panel.add(send);
		panel.add(sendButton);
		panel.add(response);
		
		//adding panel and setting panel information
		add(panel);
		setTitle("Cyptography Project");
		setSize(400,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		//event that occurs when send button is pressed
		sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(e.getSource() == sendButton){
                	
                	//getting shift
					stringShift=shift.getText();
					intShift=Integer.parseInt(stringShift);
					
                	//getting text from text box and attempting to send it to the server and then showing the sent message on the panel
                    stringSend = send.getText();
                    try {
                        response.setText(sendMessage(stringSend,intShift));
                    } 
                    catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }
            }
        });
	}
	
	public String sendMessage(String message, int s) throws IOException {
        String response;

        //creates a new socket, youll need to fill in an IP address for this to work
        Socket clientSocket = new Socket("localhost", 6789);

        // Wrapping the clientSocket in an output stream object
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        //Wrapping the clientSocket input stream in a buffered reader
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        //sending the strings to the server
        //the encrypted message is sent first followed by the shift and an int indicating which cipher to use
        outToServer.writeBytes(Caesar.encrypt(message,s) + '\n');
        outToServer.writeBytes(""+s+'\n');
        outToServer.writeBytes(""+0+'\n');
        response = inFromServer.readLine();

        //displaying encrypted message
        System.out.println("recieved encrypted message: " + response);

        //decrypting received message from server
        response = Caesar.decrypt(response,s);

        //closing the connection to there server
        clientSocket.close();

        //giving message back to GUI
        return response;
    }
}


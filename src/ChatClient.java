//Basic chat client example taken from Computer Networking: A Top Down Approach 4th edition.

import java.io.*;
import java.net.*;

public class ChatClient {

  public static void main(String args[]) throws Exception {

      String sentence;

      String modifiedSentence;

      // Creates a new input stream reader object wrapped in a buffer object.
      BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

      //creates a new socket to connect to my own PC on port 6789. If it was to another pc, I'll need the IP address!
      Socket clientSocket = new Socket("134.190.138.49", 6789);

      // Wrapping the clientSocket in an output stream object. This will be used to SEND to the server.
      DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

      //Wrapping the clientSocket input stream in a buffered reader. This will be used to READ from the server.
      // Wrapping the input and output streams in these objects just makes it easier to use.
      // You could use the clientSocket.getInputStream.read(), but the buffered reader allows us to read line by line and some other nice options :)
      //and the clientSocket.getOutputStream.write() methods on their own.
      BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

      //Reading from the USER's input stream and storing it in a string
      sentence = inFromUser.readLine();

      //sending the string to the server via the clientSocket
      outToServer.writeBytes(Caesar.encrypt(sentence) + '\n');

      //reading from there server and storing it in the modifiedSentence string.
      modifiedSentence = inFromServer.readLine();
      
      
      System.out.println("recieved encrypted message: " + modifiedSentence);
      
      System.out.println("Server: " + Caesar.decrypt(modifiedSentence));

      //closing the connection to there server. Very important!
      clientSocket.close();


  }

}
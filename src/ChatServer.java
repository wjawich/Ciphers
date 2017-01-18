//Basic chat server example taken from Computer Networking: A Top Down Approach 4th edition.
import java.io.*;
import java.net.*;
public class ChatServer {
public static void main (String args[]) throws Exception {
String message;
String reply;
String stringShift;
String cipher;
int intshift;
int intCipher;
ServerSocket welcomeSocket = new ServerSocket(6789);
while(true) {
	System.out.println("waiting for client to connect, and then passing off to new port #");
	Socket connectionSocket = welcomeSocket.accept();
	System.out.println("A client has connected. Passing client from welcomeSocket to it's own TCP socket");
	BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
	DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
 
	message = inFromClient.readLine();
	stringShift= inFromClient.readLine();
	cipher=inFromClient.readLine();

	//parsing cipher to get the int determining which cipher to use
	intCipher=Integer.parseInt(cipher);

 	//if intCipher==0, message is encrypted using Caesar Cipher, if not, Vigenere Cipher
 	if(intCipher==0){

 		//In Caesar ciphers ints are used to shift, extracting int
 		intshift=Integer.parseInt(stringShift);
	 	//printing encypted message
 		System.out.println("Recieved encrypted message: " + message);
 		//decrypting message and then printing it
 		reply = Caesar.decrypt(message, intshift);
 		System.out.println("Sending message: " + reply);
	 
 		//writing to the client re-encrypted message
 		outToClient.writeBytes(Caesar.encrypt(reply, intshift)+"\n");
 	}
	else{
		System.out.println("recieved encrypted message: " + message);
		reply = VigenereCipher.decrypt(message, stringShift);
		System.out.println("Sending message: " + reply);
		outToClient.writeBytes(VigenereCipher.encrypt(reply, stringShift)+"\n");
	}
 
 	//not closing welcome socket so more messages can be sent
}


}
}

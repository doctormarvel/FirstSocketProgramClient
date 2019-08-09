import java.net.*;
import java.io.*;

public class Client {
	//initialize socket and input output streams
	private Socket socket = null;
	private DataInputStream input = null;
	private DataOutputStream output = null;
	
	//constructor to put ip address and port
	public Client (String address, int port) {
		//establish a connection 
		//try allows us to define a block of code to be tested
		//for errors while being executed.
		try {
			socket = new Socket (address, port);
			System.out.println("connected");
			
			//takes input from terminal
			input = new DataInputStream (System.in);
			
			//sends output to the socket
			output = new DataOutputStream (socket.getOutputStream());
		}
		catch(UnknownHostException u) {
			System.out.println(u);
		}
		catch(IOException i) {
			System.out.println(i);
		}
		
		//string to read message from input
		String line = "";
		
		//Client keeps reading till "exit" is entered
		while(!line.equals("exit")) {
			try {
				line = input.readLine();
				output.writeUTF(line);
			}
			catch(IOException i){
				System.out.println(i);
			}
		}
		
		//close the connection
		try {
			input.close();
			output.close();
			socket.close();
		}
		catch (IOException i) {
			System.out.println(i);
		}
	}
	
	//localhost = 127.0.0.1
	public static void main(String args[]) {
		Client client = new Client("127.0.0.1", 4000);
	}
}

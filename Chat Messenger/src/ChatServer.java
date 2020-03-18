
import java.io.*;
import java.net.*;
import java.util.*;

/*
 * Anthony He & William Chung
 * ChatServer: Makes new thread for each client, sends message to every client, sends name of person who logs off to client
 * 			   so they can update their friends list
 */

public class ChatServer
{
	private ArrayList<PrintWriter> clientOutputStreams = new ArrayList<PrintWriter>();  
	private String secretKey;


	public  ChatServer() {
		
		try {
			ServerSocket server = new ServerSocket(4242);
			secretKey = (int)(Math.random()*Integer.MAX_VALUE+1000)+"";
			
			//keeps accepting clients and makes threads for them
			while(true) {
				Socket client = server.accept();
				PrintWriter toAdd = new PrintWriter(client.getOutputStream());
				clientOutputStreams.add(toAdd);
				Thread handler = new Thread(new ClientHandler(client));
				handler.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// writes a message to every socket in the lists
	public void tellEveryone(String message) {

		for(PrintWriter currentClient : clientOutputStreams) {
			currentClient.println(message);
			currentClient.flush();
		}
		
	}

	public class ClientHandler implements Runnable {

		private Scanner reader;
		private Socket sock;
		private PrintWriter theWriter;

		public ClientHandler(Socket clientSocket) {
			
			sock = clientSocket;

			try {
				reader = new Scanner(clientSocket.getInputStream());
				theWriter = new PrintWriter(clientSocket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			

		}

		//gives the secret first and then keeps accept and telling everyone messages till they log off 
		public void run() {
			
			theWriter.println(secretKey);
			theWriter.flush();
			
			String name = reader.nextLine();

			String loggingOff = name + ":logoff:" + secretKey;
			String message = "";

			
			while(reader.hasNextLine()) {

				message = reader.nextLine();
				
				//if the user logged off with the logoff string, we tell the client to remove the name and removes all connections
				if(message.equals(loggingOff)) {
					
					tellEveryone("Removing");
					tellEveryone(name);
					closeConnections();
					return;
					
				}
				
				//gives everyone the message
				tellEveryone(message);
		
			}

		}

		//gets rid of connections and closes from the list
		private void closeConnections(){

			try{
				synchronized(clientOutputStreams){
					reader.close();
					theWriter.close();
					sock.close();
					clientOutputStreams.remove(theWriter);
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}



	public static void main(String[] args) {
		new ChatServer();
	}
}

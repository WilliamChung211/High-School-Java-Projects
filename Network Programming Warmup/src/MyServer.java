import java.net.*;
import java.util.*;
import java.io.*;

public class MyServer {
	
	public static void main(String[]args){
		
		try{
			System.out.println("Server: ");
			ServerSocket server = new ServerSocket(4242);   //this line should only execute once in your program
			System.out.println(server.getLocalPort());
			System.out.println(InetAddress.getLocalHost().getHostAddress());
			
			//accept a client socket that attempts to connect
			Socket firSock = server.accept();
			Socket secSock = server.accept();
			
			//read from the client's socket
			//you can also use the socket's output stream and a PrintWriter
			//to send data to the client
			Scanner firIn = new Scanner(firSock.getInputStream());
			Scanner secIn = new Scanner(secSock.getInputStream());
			
			System.out.println("Name1: " + firIn.nextLine());
			System.out.println("Name2: " + secIn.nextLine());
			//as long as the client's socket is open
			//keep trying to read
			
		
		}catch(IOException e){
			e.printStackTrace();
			
		}
		
	}
}

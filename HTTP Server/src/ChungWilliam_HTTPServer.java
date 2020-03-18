import java.io.*;
import java.net.*;
import java.util.*;

/*
 * Name: William Chung
 * 
 * This program is a server that keeps accepting
 * requests of htmls and jpegs from clients/browsers 
 * using HTTP 1.1
 */
public class ChungWilliam_HTTPServer {

	public ChungWilliam_HTTPServer() {

		ServerSocket serverSocket = null;


		try {
			serverSocket = new ServerSocket(0);

		} catch (IOException e) {
			System.out.println("server not set up properly");
			System.exit(-1);
		}

		try {
			System.out.println("The address" + InetAddress.getLocalHost().getHostAddress());
			System.out.println("Port: " + serverSocket.getLocalPort());
		} catch (IOException e) {
			System.out.println("Error retrieving host name");
			System.exit(-1);
		}

		//keeps accepting clients from browsers
		while (true) {
			Socket clientSock = null;
			try {
				clientSock = serverSocket.accept();
			} catch (IOException e) {
				System.out.println("accept issue");
				System.exit(-1);
			}

			Thread clientThread = new Thread(new ClientHandler(clientSock));
			clientThread.start();
		}
	}

	public class ClientHandler implements Runnable {

		private Socket sock;
		private Scanner clientIn;
		private PrintWriter clientOut;

		public ClientHandler(Socket theSock) {

			sock = theSock;
			try {
				clientIn = new Scanner(theSock.getInputStream());
				clientOut = new PrintWriter(theSock.getOutputStream());
			} catch (IOException e) {
				System.out.println("Error retrieving input/output socket streams");
				System.exit(-1);
			}
		}

		//checks if the connection needs to be kept alive
		private boolean connection(ArrayList<String> lines) {
			for (String line : lines) {
				if (line.contains("Connection: ")) {
					return line.split(" ")[1].equals("Keep-Alive");
				}
			}

			return true;
		}

		//makes an error message and headers based on the error
		private void errMessage(int errNum, String errMess) {
			clientOut.println("HTTP/1.1 " + errNum);
			clientOut.flush();
			clientOut.println("Connection: Closed");
			clientOut.flush();
			clientOut.println("Content-Length: " + errMess.length());
			clientOut.flush();
			clientOut.println("Content-type: text/html");
			clientOut.flush();
			clientOut.println();
			clientOut.flush();
			clientOut.println(errMess);
			clientOut.flush();
		}
		
		//checks bad input in the first line
		private boolean checkBad(String[]words ) {
			return (words.length!=3||!(words[0].equals("GET") || words[0].equals("HEAD") || words[0].equals("POST"))||words[1].length()<=1||!(words[1].substring(0,1)).equals("/")||words[2].length()<=5||!words[2].substring(0,4).equals("HTTP"));
		}
		
		public void run() {


			String line;
			ArrayList<String> lines = new ArrayList<String>();

			//reads all line sent through and stores it in a list
			do {

				line = clientIn.nextLine();
				lines.add(line);
				System.out.println(line);
			} while (line.length() != 0);

			String[]words = lines.get(0).split(" ");
			
			if (checkBad(words)) {
				errMessage(400, "<html> <body> Bad Request! </body></html>");
				return;
			}

			String fName = words[1];
			fName = fName.substring(1,fName.length());

			File file = new File(fName);
			FileInputStream fin = null;
			
			try {

				fin = new FileInputStream(fName);
			} catch (FileNotFoundException e) {
				errMessage(404,"<html><body> File Not Found! </body></html>\r\n");  
				return;
			}

			clientOut.println("HTTP/1.1 200");
			clientOut.flush();
			String conStat;
			if (connection(lines)) {
				conStat = "close";
			} else {
				conStat = "Keep-Alive";
			}
			clientOut.println("Connection: " + conStat);
			clientOut.flush();
			clientOut.println("Content-Length: " + file.length());
			clientOut.flush();
			clientOut.println("Content-type: " + fName);
			clientOut.flush();
			clientOut.println();
			clientOut.flush();

			byte[] buffer = new byte[1024];

			//after sending information it sends the file in bytes per 1024 at a time
			try {

				DataOutputStream datOut = new DataOutputStream(sock.getOutputStream());
				while ((fin.read(buffer)) != -1) {
					datOut.write(buffer, 0, 1024);
				}

				fin.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			//it then closes everything
			clientIn.close();
			clientOut.close();
			try {
				sock.close();
			} catch (IOException e) {
				System.out.println("Error closing socket");
				System.exit(-1);
			}
		}
	}

	public static void main(String[] args) {

		new ChungWilliam_HTTPServer();

	}
}

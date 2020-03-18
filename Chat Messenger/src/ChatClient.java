
import java.io.*;
import java.net.*;
import java.time.Clock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/*
 * William Chung and Anthony He
 * ChatClient: Manages the GUI, each client manages their own friends list, sends message to server
 * 			   that is then broadcasted to every online client. Updates GUI texts and receives user action
 */
public class ChatClient extends JFrame {
	private JTextArea incoming; 
	private JTextField outgoing; 
	private String name; 
	private DefaultListModel<String> friendModel;
	private String secretKey;

	private Scanner reader; 
	private PrintWriter writer; 
	private Socket sock; 

	private final String SERVER_IP = "10.104.7.251";
	private final int SERVER_PORT = 4242;

	public void go() {

		//Sets up GUI
		setLayout(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(900, 525);

		incoming = new JTextArea(15, 50);
		incoming.setLineWrap(true);
		incoming.setWrapStyleWord(true);
		incoming.setEditable(false);

		JScrollPane qScroller = new JScrollPane(incoming);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		qScroller.setBounds(65, 30, 500, 250);
		qScroller.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Chat"));
		qScroller.setOpaque(false);
		add(qScroller);

		JLabel outMessage = new JLabel("Message: ");
		outMessage.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		outMessage.setBounds(65, 300, 70, 25);
		add(outMessage);

		outgoing = new JTextField(20);
		outgoing.setBounds(130, 300, 360, 20);
		outgoing.addActionListener(new SendButtonListener());
		add(outgoing);

		friendModel = new DefaultListModel<String>();
		JList<String> friendList = new JList<String>(friendModel);
		JScrollPane friendScroll = new JScrollPane(friendList);
		friendList.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Friends"));
		friendScroll.setBounds(675, 30, 150, 250);
		add(friendScroll);

		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new SendButtonListener());
		sendButton.setBounds(500, 300, 65, 18);
		add(sendButton);

		JButton logOff = new JButton("Log Off");
		logOff.addActionListener(new LogOffButtonListener());
		logOff.setBounds(775, 440, 80, 18);
		add(logOff);
		Random r = new Random();
		this.getContentPane().setBackground(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));

		setUpNetworking();

		// IncomingReader is an inner class that implements the Runnable interface
		// the run method is responsible for reading data from the server
		Thread clientThread = new Thread(new IncomingReader());
		clientThread.start();
		setVisible(true);
	}

	//Establishes connection to the server through port 4242 and the IP
	private void setUpNetworking() {

		//Sets up GUI title
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter your name: ");
		name = keyboard.nextLine();

		setTitle("Chat Client - " + name);


		//Sets up sock, reader, and writer to server
		try {
			sock = new Socket(SERVER_IP, SERVER_PORT);
			reader = new Scanner(sock.getInputStream());
			writer = new PrintWriter(sock.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();

		}

		//Key is received from server and stored for later use
		secretKey = reader.nextLine();

		//Client sends user name in return 
		writer.println(name);
		writer.flush();

	}

	// Handles action events for the send button
	public class SendButtonListener implements ActionListener {

		// Listens for user action and updates chat text box in GUI
		public void actionPerformed(ActionEvent ev) {

			String myString = String.format(name + ": " + outgoing.getText() + " %100s ", "["+ new Date() + "]");
			writer.println(myString);
			writer.flush();

			// clears out the text and resets the cursor
			outgoing.setText("");
			outgoing.requestFocus();
		}
	}


	//Handles action events for the Logoff button
	public class LogOffButtonListener implements ActionListener {

		//Listens for the logoff action and sends over the trigger key
		public void actionPerformed(ActionEvent ev) {

			try {
				writer.println(name + ":logoff:" + secretKey);
				writer.flush();

				sock.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

			System.exit(0);
		}
	}

	//Reads in messages from the server and broadcasts it in chat
	class IncomingReader implements Runnable {

		//Reads in data and either updates chat or updates friends list
		public void run() {

			while (reader.hasNextLine()) {

				String message = reader.nextLine();

				//removes name from friends list if the server told him to do it
				if (message.equals("Removing")) {
					friendModel.removeElement(reader.nextLine());
				}
				
				//prints message if the server did it
				else {
					String msgName = message.substring(0, message.indexOf(":"));
					incoming.append(message + "\n");

					//updates friends list if needed
					if (!name.equals(msgName) && !friendModel.contains(msgName)) {
						friendModel.addElement(msgName);
					}
					
				}
				
			}


		}
	}

	public static void main(String[] args) {
		new ChatClient().go();
	}
}

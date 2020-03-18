import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

/*
 * Name: William Chung
 * 
 * This class uses the Cryptable interface in order to decrypt,
 * encrypt, or reset either plaintext or cipher text based
 * on a given Cryptable object. 
 */
public class ChungWilliam_Cryptography  extends JFrame implements ActionListener{


	private JTextField plainText;			
	private JTextField cipText;			
	private Cryptable crypt;

	public ChungWilliam_Cryptography(Cryptable cry){

		crypt = cry;

		//makes the GUI frame
		setSize(340,140);
		setTitle(" Cryptography");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//sets the text fields default to nothing  
		plainText = new JTextField(20);
		plainText.setText("");
		cipText = new JTextField(20);
		cipText.setText("");

		//makes the 3 buttons the user can press
		JButton encrypt = new JButton("Encrypt");
		JButton decrypt = new JButton("Decrypt");
		JButton reset = new JButton("Reset");

		encrypt.addActionListener(this);
		decrypt.addActionListener(this);
		reset.addActionListener(this);  

		setLayout(new FlowLayout());

		//adds the labels to be displayed, text fields, and buttons in the order they should appear in
		add( new JLabel("Plaintext: "));
		add(plainText);  
		add(new JLabel("Ciphertext: "));
		add(cipText);  
		add(encrypt);
		add(decrypt);
		add(reset);

		setVisible(true);

	}

	//encrypts, decrypts, resets, or does nothing based on the text field
	public void actionPerformed(ActionEvent ae){

		//if the client pressed the encrypt button and there's something in the plaintext button, it encrypts
		if(ae.getActionCommand().equals("Encrypt")){
			
			if (!plainText.getText().equals("")) {
				cipText.setText(crypt.encrypt(plainText.getText()));
			}

		}

		//if the client pressed the decrypt button and there's something in the cyphertext button, it decrypts
		else if (ae.getActionCommand().equals("Decrypt")){
			
			if (!cipText.getText().equals("")) {
				plainText.setText(crypt.decrypt(cipText.getText()));
			}
			
		}
		
		//clears the textfield if the reset button was pressed
		else{
			plainText.setText("");
			cipText.setText("");
		}

	}
	
	//prompts the user for encryption type and runs the GUI based on it
	public static void main(String[] args){

		Scanner keyboard = new Scanner(System.in);

		// prompts the user for the type of encryption
		System.out.println("Enter the type of Encryption: String, Unstoppable, or Vigenere");
		String input = keyboard.nextLine();
		
		//runs the Cryptography based on the prompted cryptable
		if (input.equals("String")) {
			ChungWilliam_Cryptography crypt = new ChungWilliam_Cryptography(new CypherString()) ;
		}

		else if (input.equals("Unstoppable")) {
			ChungWilliam_Cryptography crypt = new ChungWilliam_Cryptography(new UnstoppableCrypt())  ;
		}

		else if (input.equals("Vigenere")) {
			ChungWilliam_Cryptography crypt = new ChungWilliam_Cryptography(new VigenereCrypt()) ;
		}

		else {
			System.out.println("Wrong input");
			main(args);
		}


	
	}

}

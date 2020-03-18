import java.util.Scanner;
/*
 * Name: William Chung
 * 
 * This class prompts the user for the type of encryption and then encrypts a prompted word based on that
 * type.
 */
public class Client {

	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);

		// prompts the user for the type of encryption and encrypts it with that type
		System.out.println("Enter the type of Encryption: String, Unstoppable, or Vigenere");
		String input = keyboard.nextLine();

		if (input.equals("String")) {
			convert(new CypherString());
		}

		else if (input.equals("Unstoppable")) {
			convert(new UnstoppableCrypt());
		}

		else if (input.equals("Vigenere")) {
			convert(new VigenereCrypt());
		}

		//asks the question again if, they inputed their answer wrong
		else {
			System.out.println("Wrong input");
			main(args);
		}

	}

	// prompts the user for a word to be encrypted and encrypts it
	public static void convert(Cryptable Crypt) {
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter a word");
		String input = keyboard.nextLine();
		System.out.println(Crypt.encrypt(input));
		
	}
}

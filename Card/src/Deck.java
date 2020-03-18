/*
 * Name: William Chung
 * This class represents a deck of a cards by having an array of card objects and size of number in deck. 
 * The top of the deck is at index 0 of this array.You can check its full or empty status, order it, 
 * shuffle it, deal from the top, and add to bottom. 
 */
import java.util.NoSuchElementException;

public class Deck {

	private int numberInDeck;
	private Card[] myCards;

	public Deck() {

		myCards = new Card[52];
		numberInDeck = 52;
		SUIT[] allSuit = SUIT.values();
		RANK[] allRank = RANK.values();

		// fills the array with all possible 52 unique cards
		for (int i = 0; i < numberInDeck; i++) {
			myCards[i] = new Card(allSuit[i / 13], allRank[i % 13]);
		}

	}

	// create empty deck with a max number of cards
	public Deck(int max) {
		
		if (max<0){
			throw new IllegalArgumentException("Can't be less than 0");
		}
		myCards = new Card[max];
		numberInDeck = 0;

	}

	public int numberInDeck() {
		return numberInDeck;
	}

	public boolean isEmpty() {
		return numberInDeck == 0;
	}

	public boolean isFull() {
		return numberInDeck == myCards.length;
	}

	// assigns a copy of the parameter to the bottom of the deck
	public void addToBottom(Card aCard) {

		if (this.isFull()) {
			throw new IllegalStateException("The deck is full");
		}

		Card cardCopy = new Card(aCard);
		myCards[numberInDeck] = cardCopy;
		numberInDeck++;

	}

	// Removes and returns the top card (face up) of the deck
	public Card dealFromTop() {

		if (this.isEmpty()) {
			throw new NoSuchElementException("The deck is empty");
		}

		Card top = myCards[0];

		// shifts all cards to the left of the deck
		for (int i = 0; i < numberInDeck - 1; i++) {
			myCards[i] = myCards[i + 1];
		}

		numberInDeck--;
		myCards[numberInDeck] = null;

		// makes the card face up
		if (!top.getFace().equals("It is face up")) {
			top.flip();
		}

		return top;
	}

	// Swaps two random and different cards and repeats this process numTimes
	public void shuffle(int numTimes) {

		int firstInt = 0;
		int secondInt = 0;

		for (int i = 0; i < numTimes; i++) {

			// finds two different places to swap
			firstInt = ((int) (Math.random() * myCards.length));

			if (firstInt == secondInt)
				secondInt--;
			} 

			// swaps the places
			Card temp = myCards[firstInt];
			myCards[firstInt] = myCards[secondInt];
			myCards[secondInt] = temp;
		}

	

	// Rearranges all cards in the deck into order
	public void order() {

		// checks a scanned portion which decreases by one for every iteration of this loop
		for (int n = numberInDeck; n >= 2; n--) {

			int maxIndex = 0; // location of the best index

			// finds the largest element in scanned portion
			for (int i = 1; i < n; i++) {

				if (myCards[i].compareTo(myCards[maxIndex]) > 0) {
					maxIndex = i;
				}

				// swaps the largest value with where it belongs in the array
				Card tempSwap = myCards[maxIndex];
				myCards[maxIndex] = myCards[n - 1];
				myCards[n - 1] = tempSwap;
			}

		}
	}

}

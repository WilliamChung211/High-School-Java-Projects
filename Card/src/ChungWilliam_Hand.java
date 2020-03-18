import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeMap;

/*
 * Name: William Chung
 * 
 * This program represents a data structure that represents a hand 
 * of playing cards. It has a TreeMap (which is like an array, but
 * instead of ints as keys, we can have any object) representing all 
 * cards in the hand.
 */
public class ChungWilliam_Hand {

	private TreeMap<RANK, Deck> allCards;

	public ChungWilliam_Hand() {
		allCards = new TreeMap<RANK, Deck>();
	}

	// adds a card to the map
	public void addToHand(Card toAdd) {
		RANK addRank = toAdd.getRank();
		if (!allCards.containsKey(addRank)) {
			allCards.put(addRank, new Deck(4));
		}
		allCards.get(addRank).addToBottom(toAdd);
	}

	// returns and removes a deck of all cards of a given rank found in the hand
	public Deck giveUp(RANK toFind) {

		if (!allCards.containsKey(toFind)) {
			return new Deck(4);
		}
		return allCards.remove(toFind);
	}

	public void printAll() {
		for (RANK rank : allCards.keySet()) {
			Deck deckToPr = allCards.get(rank);
			LinkedList<Card> cardsToAddBack = new LinkedList<Card>();

			// we print by removing the card toPrint face up (so we can see it), printing it, and saving it
			while (!deckToPr.isEmpty()) {
				Card toPrint = deckToPr.dealFromTop();
				System.out.println(toPrint);
				cardsToAddBack.add(toPrint);
			}
			
			while (!cardsToAddBack.isEmpty()) {
				deckToPr.addToBottom(cardsToAddBack.remove());
			}
			
		}
		
	}

	// if all 4 cards of rank toFind are in the hand, removes them and returns 1, otherwise returns 0
	public int booksWon(RANK toFind) {

		if (allCards.containsKey(toFind)) {
			Deck rankDeck = allCards.get(toFind);
			if (rankDeck.numberInDeck() == 4) {
			
				allCards.remove(toFind);
				return 1;
			}
			
		}
		return 0;
	}

	//examines the entire hand for all cards that contain 4 of the same rank and removes all cards if they do
	public int booksWon() {
		
		int totBooksWon = 0;

		//this linked list will be made to easily traverse and possibly delete through the map
		LinkedList<RANK>allRanks = new LinkedList<RANK>();
		
		for (RANK checkRnk:allCards.keySet()) {
			allRanks.add(checkRnk);
		}
		
		for(RANK checkRnk: allRanks) {
			totBooksWon+= booksWon(checkRnk);
		}
		
		//returns the total number of books found
		return totBooksWon;
	}

	//returns the rank that occurs more frequently in one hand
	public RANK mosFreq(RANK toIgnore) {

		int handSize = allCards.size();

		//returns null if the hand is empty
		if (handSize == 0) {
			return null;
		} 
		
		//if the rank to ignore is the only card present, we return that rank
		else if (handSize == 1) {
			return allCards.keySet().iterator().next();
		}

		ArrayList<Deck>[] freqs = new ArrayList[5];
		for (int i = 0; i < 5; i++) {
			freqs[i] = new ArrayList<Deck>();
		}

		//gets every frequency from each rank except the toIgnore
		for (RANK checkRnk : allCards.keySet()) {
			if (checkRnk != toIgnore) {
				Deck deckToChk = allCards.get(checkRnk);
				freqs[deckToChk.numberInDeck()].add(deckToChk);
			}
		}
		
		//this loop will eventually end since we know the hand is not empty
		for (int i = 4; ; i--) {
			ArrayList<Deck> freqList = freqs[i];
			
			//if more than one Rank has the same highest frequency, select randomly from the set
			if (!freqList.isEmpty()) {
				int randomInd = (int) (freqList.size() * Math.random());
				Card mosFreqCard = freqList.get(randomInd).dealFromTop();
				freqList.get(randomInd).addToBottom(mosFreqCard);
				return mosFreqCard.getRank();
			}
			
		}

	}

}

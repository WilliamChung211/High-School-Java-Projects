/*
 * Name: William Chung
 * This client tests the methods of the deck class 
 */
public class Client {
	
	public static void main (String[]args){
	
		Card hi = new Card(SUIT.SPADE,RANK.ACE);
		ChungWilliam_Hand hand = new ChungWilliam_Hand();
		hand.addToHand(hi);
		hand.addToHand(new Card(SUIT.HEART,RANK.ACE));
		hand.printAll();
		
	}
}

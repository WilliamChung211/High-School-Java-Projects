/*
 * Name: William Chung
 * This client tests the all the public methods of the Card Class. 
 */

public class ClientCard {
	public static void main (String[]args){
	Card hi = new Card(SUIT.SPADE,RANK.ACE);
	SUIT[] allSuit = SUIT.values();
	RANK[] allRank = RANK.values();
	
	for (int i=0;i<allSuit.length;i++){
		
		for (int j=0;j<allRank.length;j++){
			hi= new Card(allSuit[i],allRank[j]);
			hi.flip();
			System.out.println(hi);
		}
	}
	
	hi.flip();
	Card two = new Card(hi);

	System.out.println(hi.equals(two));
	Card three = new Card(SUIT.HEART, RANK.THREE);
	System.out.println(two.equals(three));
	System.out.println(two.compareTo(three));
	
	three = new Card(SUIT.SPADE,RANK.ACE);
	System.out.println(two.compareTo(three));
	System.out.println(two.equals(three));
	
	System.out.println(hi);
	hi.flip();
	System.out.println(hi);
	System.out.println(hi.getRank());
	System.out.println(hi.getFace());
	hi.flip();
	System.out.println(hi.getFace());
	System.out.println(hi.getSuit());
}
}

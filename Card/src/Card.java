

public class Card {

	public boolean face; //false = face down true = face up
	public SUIT suit;
	public RANK rank;
	
	//face defaults to false
	public Card (SUIT sut, RANK rak){
		suit = sut;
		rank = rak;
	}
	
	public Card (Card other){
		this(other.suit,other.rank);
	}
	
	public SUIT getSuit (){
		
		return suit;
		
	}
	
	public RANK getRank(){
		
		return rank;
		
	}
	
	public String getFace(){
		
		if (face){
			return "It is face up";
		}
		else {
			return "It is face down.";
		}
		
	}
	
	public void flip (){
		face=!face;
	}
	
	public String toString(){
		if (!face){
			return "Unknown";
		}
		
		return rank + " of " + suit + "S";
		
	}
	
	private int cardValue (){
		return (suit.ordinal()*13)+rank.ordinal();
		
	}
	
	public int compareTo(Card other){
		return this.cardValue()-other.cardValue();
	}
	
	public boolean equals(Card other){
		return (this.suit==other.suit)&&(this.rank==other.rank);
		//return this.cardValue()==other.cardValue();
	}
}

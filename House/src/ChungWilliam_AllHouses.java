import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/*
 * Name: William Chung
 * 
 * This class represents all Houses in the Westeros. Westeros
 * consists of large families known as houses that end up allying together
 * to protect themselves from other allied houses. Allied houses fall together from
 * a banner. Who all the houses with a map.
 */
public class ChungWilliam_AllHouses {

	private Map<String, House>theHouses;
	
	//All houses are on their own and they do not have a banner house
	public ChungWilliam_AllHouses( Collection<String> names) {
		
		theHouses = new HashMap<String, House>();
		for(String name:names) {
			theHouses.put(name, new House());
		}
	}
	
	//h2 will be the banner of h1's top banner
	public void combine(String h1, String h2) {
		getBanner(h1).banner=theHouses.get(h2);
	}
	
	//returns true if both houses fall under the same top level banner house
	public boolean sameBanner(String h1,String h2) {
		return getBanner(h1)==getBanner(h2);
	}
	
	//a house by itself is its own top level house
	private House getBanner(String aHouse) {
	
		House topBan= theHouses.get(aHouse);
	
		while(topBan.banner!=null) {
			topBan = topBan.banner;
		}
		
		return topBan;
	}
	
	//this is a class that refers to the direct banner associated with that house
	public class House{
		House banner;
	}
}

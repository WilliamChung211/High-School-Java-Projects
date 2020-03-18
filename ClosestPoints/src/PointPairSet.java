/*Name: William Chung
 * 
 * This class represents a small set of point pairs
*/
import java.util.*;


public class PointPairSet extends HashSet<PointPair> {

	//default constructor
	public PointPairSet(){
		
	}
	
	//copy constructor
	public PointPairSet( PointPairSet s){

		super(s);
	}
	
	//removes every point pair in the set
	public void clean(){
		
		Iterator iter = this.iterator();
		while(iter.hasNext()){
			iter.next();
			iter.remove();
		}
	}
	
	//adds all point pairs in one set to this set
	public void addAll(PointPairSet other){
		
		Iterator<PointPair> iter = other.iterator();
		
		while(iter.hasNext())
			add(iter.next());
	}

	//gets the distances from the first point pair in the set
	public double getDistance(){
		Iterator<PointPair> iter = this.iterator();
		return iter.next().distance();
	} 

}

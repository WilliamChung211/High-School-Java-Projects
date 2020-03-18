
/*
 * Name: William Chung
 * 
 * This class represents a pair of points
 */
public class PointPair {

	private Point p1;
	private Point p2;
	

	//two parameter constructor
	public PointPair( Point s1, Point s2){

		p1 = new Point(s1);
		p2 = new Point(s2);
	}
	
	//one parameter constructor
	public PointPair( PointPair p){

		p1 = new Point(p.p1);
		p2 = new Point(p.p2);
	}
	
	//mutates both points
	public void setPoints(Point s1, Point s2){

		p1 = s1;
		p2 = s2;
	}

	//finds distance from one point in the pair to the other
	public double distance(){

		return Math.sqrt(Math.pow(p1.getX()-p2.getX(), 2)+ Math.pow(p1.getY()-p2.getY(), 2));
	}


	//returns true if both points in pair are equal 
	public boolean equals(Object o){

		if(!(o instanceof PointPair))
		    return false;	
	
		PointPair  p = (PointPair)o;

		if(p.p1.equals(p1)&& p.p2.equals(p2))
			return true;

		if(p.p2.equals(p1)&& p.p1.equals(p2))
			return true;

		return false;
	}
	
	//hash method
	public int hashCode(){
		return p1.hashCode() + p2.hashCode();
	}
	
	//prints both points
	public String toString(){

		return "{ "+  p1.toString() + ", " + p2.toString()+ " }";
	}
	
}

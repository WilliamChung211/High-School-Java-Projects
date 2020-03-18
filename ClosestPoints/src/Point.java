/*
 * Name: This program represents a 2 dimensional point with
 * an x and y.
 */

import java.util.*;

public class Point {
	
	private int x; 
	private int y; 
	
	//default constructor
	public Point(){

		x = 0;
		y = 0;
	}
	
	//two variable constructor
	public Point(int someX, int someY){

		x = someX;
		y = someY;
	}
	
	//one variable constructor
	public Point(Point p){

		x = p.x;
		y = p.y;
	}
	

	//calculates distance from origin using the formula
	public double distance(Point p){

		return Math.sqrt(Math.pow(p.x-x, 2) + Math.pow(p.y-y, 2));
	}
	
	//accessor of x
	public int getX(){

		return x;
	}
	
	//accessor of y
	public int getY(){

		return y;
	}
	
	//mutator of x and y
	public void set(int someX, int someY){

		x = someX;
		y = someY;
	}
	
	//returns equals of x and y are equal
	public boolean equals(Object o){
	
		if(!(o instanceof Point))
			return false;

		Point p = (Point)o;
		return (x == p.x && y == p.y);
	}

	//hash method
	public int hashCode(){
		return 31*x +y;
	}
	
	//prints its x and y as a coordinate
	public String toString(){

		return "[" + x + ", " + y + "]";
	}
	
}

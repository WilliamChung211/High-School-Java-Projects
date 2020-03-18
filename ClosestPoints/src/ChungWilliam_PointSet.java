

import java.util.*;

/*
 * Name: William Chung
 * 
 * This class represents a set of unique points. It also
 * has a method to calculate a set of the point pairs with the
 * closest distance
 */
public class ChungWilliam_PointSet extends ArrayList<Point> {

	private Comparator<Point> compareMethod;

	//default constructor
	public ChungWilliam_PointSet(){

	}

	//copy constructor
	public ChungWilliam_PointSet(ChungWilliam_PointSet l){

		super(l);
	}

	//three paramter constructor
	public ChungWilliam_PointSet(int xMax, int yMax, int num){

		Random r = new Random();

		//gets num number of unique random points. 0 <x<=xMax. 0<y<=yMax
		for(int i = 1; i <= num; i++){

			int x = r.nextInt(xMax + 1);
			int y = r.nextInt(yMax + 1);
			Point toAdd = new Point(x,y);

			if(!contains(toAdd))
				this.add(new Point(x,y));
			else
				i--;

		}
		
	}

	//adds all points of all sets
	public ChungWilliam_PointSet union(ChungWilliam_PointSet set){

		ChungWilliam_PointSet ret = new ChungWilliam_PointSet(set);
		for(Point p: this){
				ret.add(p);
		}
		return ret;
	}

	//adds all points that both sets contain
	public ChungWilliam_PointSet intersection(ChungWilliam_PointSet set){

		ChungWilliam_PointSet ret = new ChungWilliam_PointSet();
		for(Point p : this){
			if(set.contains(p))
				ret.add(p);
		}
		return ret;
	}

	//returns a set of the pairs of points that are closest to each other through brute force
	public PointPairSet closestPointBF(){
		
		PointPairSet toRet = new PointPairSet();
		double minDis = Integer.MAX_VALUE;
		
		//goes through each pair 
		for(int i =0;i<size();i++) {
			for(int checkInd = i+1; checkInd<size();checkInd++)
			//adds the pair if it is the minimum distance. clears list if it is less than the current minimum one
			
				if(!get(i).equals(get(checkInd))) {
					PointPair pair = new PointPair(get(i), get(checkInd));
					double checkDis = pair.distance();
					if (checkDis<minDis) {
						toRet.clean();
						minDis = pair.distance();
					}
					
					if(pair.distance()==minDis) {
						toRet.add(pair);
					}
				}
				
		}
		
		return toRet;
	}

	//divide and conquer algorithm to find closest points in nlog(n) time
	public PointPairSet closestPointDC(){
		
		ChungWilliam_PointSet xSet = new ChungWilliam_PointSet(this);
		xSet.qsort(true);
		ChungWilliam_PointSet ySet = new ChungWilliam_PointSet(this);
		ySet.qsort(false);
		return closestPointsHelp(xSet,ySet, 0, size()-1);
	}
	
	
	private PointPairSet closestPointsHelp(ChungWilliam_PointSet xOrder, ChungWilliam_PointSet yOrder, int left, int right) {
		PointPairSet toRet = new PointPairSet();
		
		if(right-left==1){
			toRet.add(new PointPair(xOrder.get(left),xOrder.get(right)));
		}
		
		//we can use brute force if it is only 3 points, since it is still constant
		else if (right-left==2) {
			toRet=xOrder.closestPointBF();
		}
		
		else {
			
			int mid = (left+right)/2;
			
			PointPairSet leftOne = closestPointsHelp(xOrder,strip(xOrder.get(left).getX(), xOrder.get(mid).getX(), yOrder), left,mid);
			PointPairSet rightOne = closestPointsHelp(xOrder,strip(xOrder.get(mid+1).getX(), xOrder.get(right).getX(), yOrder), mid+1,right);
			double inMinDis;
			
			//gets the best distance from the two calls and adds all that have the best distance
			if(leftOne.getDistance()<rightOne.getDistance()) {
				toRet.addAll(leftOne);
				inMinDis = leftOne.getDistance();
			}
			else {
				toRet.addAll(rightOne);
				inMinDis = rightOne.getDistance();
				if(leftOne.getDistance()==rightOne.getDistance()){
					toRet.addAll(leftOne);
				}
			
			}
	
			//we gotta check if there are any best point pairs in between left and right so we isolate the points into strips
			ChungWilliam_PointSet isoSet = strip(xOrder.get(mid).getX() - inMinDis, inMinDis + xOrder.get(mid).getX(), yOrder );;
		
			//examines all the point pairs in the strips based on a ceiling
			for(int i =0;i<isoSet.size();i++) {
				
				for(int checkInd =i+1;checkInd<isoSet.size();checkInd++) {
					
					//exits out if we hit a ceiling
					if(isoSet.get(checkInd).getY()-isoSet.get(i).getY()>inMinDis) {
						checkInd = isoSet.size();
					}
					else {
						
						PointPair checkPair = new PointPair(isoSet.get(checkInd),isoSet.get(i));
						double checkDis = checkPair.distance();
						
						//we found a better min distance
						if (checkDis<inMinDis) {
							toRet.clean();
							inMinDis = checkDis;
						}
						
						//adds it to set if it is the min distance
						if(checkDis==inMinDis) {
							toRet.add(checkPair);
						}
					}
				}
			}
		}
		
		return toRet;
		
	}
	
	//gets all the points below a boundary in yOrder
	private ChungWilliam_PointSet strip(double lowBound, double upBound, ChungWilliam_PointSet yOrder) {
		ChungWilliam_PointSet toRet = new ChungWilliam_PointSet();
		for(int i =0;i<yOrder.size();i++) {
			if(yOrder.get(i).getX()>=lowBound&&yOrder.get(i).getX()<=upBound) {
				toRet.add(yOrder.get(i));
			}
		
		}
		
		return toRet;
	}
	


	// byX is true, sort by X
	// otherwise sort by Y
	//Sorts the array by X/Y values
	public void qsort(boolean byX){

		if(byX)
			compareMethod = new XComparator();
		else
			compareMethod = new YComparator();


		quickSort(0,size()-1);
	}

	//sorts all methods using the quick sort algorithm 
	private void quickSort(int from, int to){
		if(from >= to)
			return;

		int pivot = (from + to)/ 2;
		int i = from;
		int j = to;

		//swaps any element that needs to be to the left or right of the pivot
		while(i <= j){


			if(compareMethod.compare(get(i), get(pivot)) < 1)
				i++;
			else if( compareMethod.compare(get(j), get(pivot)) > -1)
				j--;
			else{

				Point temp = set(i, get(j));
				set(j, temp);
				i++;
				j--;
			}
		}

		//puts pivot in correct spot
		if( pivot < j){
			Point temp = set(j, get(pivot));
			set(pivot, temp);
			pivot = j;
		}
		else if(pivot > i){
			Point temp = set(i, get(pivot));
			set(pivot, temp);
			pivot = i;
		}


		//Repeat until fully sorted
		quickSort(from, pivot-1);
		quickSort(pivot+1,to);

	}

	

}

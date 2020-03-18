import java.util.LinkedList;
import java.util.Queue;

/*
 * Name: William Chung
 * 
 * This program has a divide and conquer algorithm that determines the total number of 
 * array elements that are out of order while also sorting the list
 */
public class ChungWilliam_CommonLists {

	public static int dissimilar(int[] data, int startLoc, int endLoc) {
		if(startLoc==endLoc) {
			return 0;
		}
	
		int middle = (startLoc + endLoc) / 2;
		int toRet = dissimilar(data,startLoc,middle)+ dissimilar(data,middle+1,endLoc);
		
		Queue <Integer>leftQueue = new LinkedList<Integer>();
		Queue <Integer>rightQueue = new LinkedList<Integer>();
		for(int i =startLoc;i<=middle;i++) {
			leftQueue.add(data[i]);
		}
		for(int i =middle+1;i<=endLoc;i++) {
			rightQueue.add(data[i]);
		}
		
		int ind =startLoc;
		
		//we keep comparing and adding minimums to sort. we also add out of order number if right is less than left.
		while(!leftQueue.isEmpty()&&!rightQueue.isEmpty()) {
			if(leftQueue.peek()<=rightQueue.peek()) {
				data[ind]=leftQueue.remove();
			}
			else {
				toRet+=leftQueue.size();
				data[ind]=rightQueue.remove();
			}
			ind++;
		}
		
		//we add the rest in there
		while(!rightQueue.isEmpty()){
				data[ind]=rightQueue.remove();
				ind++;
		}
		while(!leftQueue.isEmpty()) {
				data[ind]=leftQueue.remove();
				ind++;
		}
		
		
		return toRet;
	}
	
	public static void main(String []args) {
		
		int[]arr = {4,3,1,6,5,2};
		System.out.println(dissimilar(arr,0,arr.length-1));
		for(int i =0;i<arr.length;i++) {
			System.out.print(arr[i] + ",");
		}
	}
}


import java.util.ArrayList;
import java.util.PriorityQueue;
public class Client {

	public static void main(String[]args) {
		ChungWilliam_NodeHeap <Integer>tree = new ChungWilliam_NodeHeap<Integer>();
		PriorityQueue<Integer>PQ = new PriorityQueue<Integer>();
		
		ArrayList <Integer>list = new ArrayList<Integer>();
		
		for(int i =0;i<2;i++) {
			list.add((int)(Math.random()*200));
		}
		
		for(int i =0;i<2;i++) {
			tree.add(list.get(i));
			PQ.add(list.get(i));
		}
		
		System.out.println(list);
		System.out.println(tree);
		System.out.println(PQ);
		
	}
}

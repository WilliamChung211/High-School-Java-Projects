import java.util.*;

/*
 * Name: William Chung
 * 
 * This program represents houses banding together to determine
 * the shorts amount of roads required to reach all the houses.
 */
public class WinterReady {

	private ChungWilliam_AllHouses theHouses;
	private PriorityQueue<Road> allRoads;
	private Graph theGraph;

	public WinterReady(Graph graph) {
		theGraph = graph;
		Set<String> nodes = theGraph.getAllNodes();
		theHouses = new ChungWilliam_AllHouses(nodes);
		getAllRoads();
	}

	//Creates the allRoads instance variables
	private void getAllRoads() {
		allRoads = new PriorityQueue<Road>();
		Set<String> nodes = theGraph.getAllNodes();
		for (String node : nodes) {
			
			Set<String> neighbors = theGraph.getNeighbors(node);
			for (String neigh : neighbors) {
				allRoads.add(new Road(node, neigh, theGraph.getCost(node, neigh)));
			}
		}
	}

	//Implements Kruskal's algorithim and returns the edges in the min spanning tree
	public ArrayList<Road> getMinRoads() {
		ArrayList<Road> minRoads = new ArrayList<Road>();

		System.out.println(allRoads);
		printAllRoads();
		return minRoads;
	
	}

	public void printAllRoads() {
		while(!allRoads.isEmpty()){
			System.out.println(allRoads.remove());
		}
	}
	public class Road implements Comparable<Road> {
		private String source;
		private String dest;
		private int cost;

		public Road(String s, String d, int c) {
			source = s;
			dest = d;
			cost = c;
		}

		public boolean equals(Object other) {

			if (!(other instanceof Road))
				return false;

			Road otherE = (Road) other;

			return source.equals(otherE.source) && dest.equals(otherE.dest)
					|| dest.equals(otherE.source) && source.equals(otherE.dest);
		}

		public int compareTo(Road other) {
			return cost - other.cost;
		}

		public String toString() {
			return "[" + source + " , " + dest + " , " + cost + "]";
		}
	}

	public static void main(String[]args) {
		String[]arr = {"A", "B", "C", "D", "E", "F"};
		Graph grape = new Graph(arr);
		grape.addArc("A","B",2);
		grape.addArc("A","C",3);
		grape.addArc("B","C",9);
		grape.addArc("B","E",7);
		grape.addArc("C","D",10);
		grape.addArc("D","F",6);
		grape.addArc("E","F",5);
		System.out.println(new WinterReady(grape).getMinRoads());
	}
}


/*
 * Name: William Chung
 * 
 * This program represents the data structure of a graph
 * and a method for 
 * 
 * 1. 7. A to B to D
 * 2. 5. A to B to C to D
 */
import java.util.*;

public class Graph {
	private Map<String, Map<String, Integer>> graph; // Maps nodes to other nodes (Both HashMap if order does not
														// matter; Both TreeMap if it does)
	public void dijkstra(String start) {

		Set<String> visited = new HashSet<String>();
		
		// we use tree map so that they are in order when we print them out later
		TreeMap<String, String> previous = new TreeMap<String, String>();
		HashMap<String, Integer> cost = new HashMap<String, Integer>();
		cost.put(start, 0);
		Set<String> graphSet = graph.keySet();

		// the concept of infinity will be presented with the highest integer value
		for (String node : graphSet) {
			previous.put(node, "none");
			cost.put(node, Integer.MAX_VALUE);
		}

		cost.put(start, 0);

		// keeps running as long as not all nodes are in the visited set
		while (visited.size() != graph.size()) {

			// visits the smallest node not in set and checks its neighbors
			String smallestNode = smallestNode(cost, visited);
			visited.add(smallestNode);
			Map<String, Integer> node = graph.get(smallestNode);
			Set<String> neighSet = node.keySet();

			// checks if the new path it is checking for each neighbor is better than its original path
			for (String neighb : neighSet) {
				if (!visited.contains(neighb)) {
					int pathCost = cost.get(smallestNode) + node.get(neighb);
					if (pathCost < cost.get(neighb)) {
						cost.put(neighb, pathCost);
						previous.put(neighb, smallestNode);
					}
				}
			}

		}

		table(previous, cost);
	}

	private void table(Map<String, String> prev, Map<String, Integer> cost) {
		System.out.println("   |  C   |   P  |");

		Set<String> nodes = cost.keySet();
		for (String node : nodes) {
			System.out.println("---------------------");
			String printCost;
			if (cost.get(node) == Integer.MAX_VALUE) {
				printCost = "INF";
			} else {
				printCost = "" + cost.get(node);
			}
			System.out.println(node + "  |  " + printCost + "   |   " + prev.get(node) + "  |");
		}
	}

	// obtains the node with the smallest cost not in the visited set
	private String smallestNode(Map<String, Integer> cost, Set<String> visited) {
		String smallestNode = null;
		Integer smallestCost = Integer.MAX_VALUE;
		Set<String> nodeSet = graph.keySet();
		for (String node : nodeSet) {
			if (!visited.contains(node)) {

				int checkCost = cost.get(node);
				if (smallestCost >= checkCost) {
					smallestNode = node;
					smallestCost = checkCost;
				}
			}
		}

		return smallestNode;
	}

	// Creates an empty graph
	public Graph() {
		graph = new HashMap<String, Map<String, Integer>>();
	}

	public Graph(String[] names) {
		this();
		for (String node : names)
			addNode(node);
	}

	// Adds a node to the graph
	public void addNode(String name) {
		// Node cannot already exist
		if (graph.containsKey(name))
			throw new IllegalArgumentException("\"" + name + "\" already exists.");

		graph.put(name, new HashMap<String, Integer>());
	}

	// Creates an arc from one node to another
	public void addArc(String from, String to, int weight) {
		// Nodes must be different and not already connected
		checkDifferentNodes(from, to);
		checkNoConnection(from, to);

		addLink(from, to, weight);
	}

	// Creates an edge between two nodes
	public void addEdge(String node1, String node2, int weight) {
		// Nodes must be different and not already connected
		checkDifferentNodes(node1, node2);
		checkNoConnection(node1, node2);

		addLink(node1, node2, weight);
		addLink(node2, node1, weight);
	}

	// Returns whether or not two nodes are connected (one-directional)
	public boolean isConnected(String from, String to) {
		// Both nodes must exist
		exists(from);
		exists(to);

		return (graph.get(from).containsKey(to));
	}

	// Removes an arc from one node to another
	public void removeArc(String from, String to) throws IllegalArgumentException {
		// An arc, not an edge, must exist
		if (!isConnected(from, to) || isConnected(to, from))
			throw new IllegalArgumentException("There is no arc from \"" + from + "\" to \"" + to + ".");

		removeLink(from, to);
	}

	// Removes an edge between two nodes
	public void removeEdge(String node1, String node2) throws IllegalArgumentException {
		// An edge must exist
		if (!isConnected(node1, node2) || !isConnected(node2, node1))
			throw new IllegalArgumentException("There is no edge between \"" + node1 + "\" and \"" + node2 + ".");

		removeLink(node1, node2);
		removeLink(node2, node1);
	}

	public int getCost(String source, String dest) {
		return graph.get(source).get(dest);
	}

	public Set<String> getAllNodes() {
		return graph.keySet();
	}

	public Set<String> getNeighbors(String node) {
		return graph.get(node).keySet();
	}

	public int numNodes() {
		return graph.size();
	}

	// Throws exception if the node does not exist
	private void exists(String node) throws IllegalArgumentException {
		if (!graph.containsKey(node))
			throw new IllegalArgumentException("\"" + node + "\" does not exist.");
	}

	// Throws exception if the two nodes are the same
	private void checkDifferentNodes(String node1, String node2) throws IllegalArgumentException {
		if (node1.equals(node2))
			throw new IllegalArgumentException("\"" + node1 + "\" cannot be connected to itself.");
	}

	// Throws exception if the two nodes are connected
	private void checkNoConnection(String node1, String node2) throws IllegalArgumentException {
		if (isConnected(node1, node2) || isConnected(node2, node1))
			throw new IllegalArgumentException("\"" + node1 + "\" and \"" + node2 + "\" are already connected.");
	}

	// Adds a one-directional link from one node to another (no checks)
	private void addLink(String from, String to, int weight) {
		graph.get(from).put(to, weight);
	}

	// Removes a one-directional link from one node to another (no checks)
	private void removeLink(String from, String to) {
		graph.get(from).remove(to);
	}

	public void breathTraversal(String node) {
		Queue<String> printNodes = new LinkedList<String>();
		Set<String> visited = new HashSet<String>();
		exists(node);

		printNodes.add(node);
		visited.add(node);

		// keeps printing in order of the neighbor level
		do {

			String parentNode = printNodes.remove();
			System.out.println(parentNode);

			// when a neighbor is getting printed, it adds the neighbors neighbors to be
			// printed later
			Set<String> neighborSet = graph.get(parentNode).keySet();
			for (String neighbor : neighborSet) {
				if (visited.add(neighbor)) {
					printNodes.add(neighbor);
				}
			}

		} while (!printNodes.isEmpty());
	}

	public void depthTraversal(String node) {
		Set<String> visited = new HashSet<String>();
		exists(node);
		visited.add(node);
		depthTr(node, visited);

	}

	private void depthTr(String node, Set<String> visited) {

		System.out.println(node);

		// if it has no neighbors it is done
		if (graph.get(node).isEmpty()) {
			return;
		}

		Set<String> set = graph.get(node).keySet();

		// goes through each neighbor and then traverses the neighbor's neighbor and
		// repeats till it reaches the end
		for (String neighbor : set) {

			if (visited.add(neighbor)) {
				depthTr(neighbor, visited);
			}

		}

	}

}
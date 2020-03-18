import java.util.*;
import java.io.*;

public class WillTim_MetroTransit implements Runnable {
	private int id;
	private ArrayList<Station> stations;

	public WillTim_MetroTransit(String fileName, int numTrains) {
		Scanner fileIn = null;
		stations = new ArrayList<Station>();
		id = 0;

		try {
			fileIn = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.exit(-1);
		}

		int sInd = 0;
		while (fileIn.hasNextLine()) {
			stations.add(new Station(sInd, fileIn.nextLine()));
			sInd++;
		}

		ArrayList<Integer> random = new ArrayList<Integer>(stations.size());
		for (int i = 0; i < stations.size(); i++) {
			random.add(i);
		}

		for (int i = 0; i < numTrains; i++) {
			stations.get(random.remove((int) (Math.random() * random.size()))).current = new Train();
		}
	}

	public void run() {
		Random r = new Random();

		while (true) {
			try {
				Thread.sleep(r.nextInt(1000));
			} catch (InterruptedException e) {
				System.exit(-1);
			}

			int location = r.nextInt(stations.size());
			int destination;

			do {
				destination = r.nextInt(stations.size());
			} while (location == destination);

			synchronized(stations.get(location)) {
				stations.get(location).line.add(new Passenger(destination));
			}
		}

	}

	class Train {
		private ArrayList<LinkedList<Passenger>> passengers;
		private int capacity;
		private final int maxCapacity;
		boolean rightDir;

		public Train() {
			rightDir = (int) (Math.random() * 2) == 1;
			maxCapacity = (int) (Math.random() * 200) + 100;
			capacity = 0;
			passengers = new ArrayList<LinkedList<Passenger>>();
		}

		private Train move(int statInd) {

			Train toRet = this;

			int nextInd;
			if (statInd == 0 && !rightDir || statInd == stations.size() - 1 && rightDir) {
				rightDir = !rightDir;
			}
			if (rightDir) {
				nextInd = statInd + 1;
			} else {
				nextInd = statInd - 1;
			}

			Station nextStation = stations.get(nextInd);
			
			synchronized(nextStation) {
				if (nextStation.current == null) {
					nextStation.current = this;
					nextStation.clearTheTrain();
					
					nextStation.notify();
					stations.get(statInd).notify();
				} 
				
			}

			return deadLock(start);

		}
		
		private Train deadLock(int statInd, int nextInd) {
			Station currentSt = stations.get(statInd);
			Station nextSt = stations.get(nextInd);
			Train temp = nextSt.current;
			if(temp.rightDir!=this.rightDir) {
				nextSt.current = currentSt.current;
				currentSt.current = temp;
				currentSt.clearTheTrain();
				nextSt.clearTheTrain();
				return this;
			}
			try {
				nextSt.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			return null;
			
		}
		
		
	}

	class Station implements Runnable {
		private Train current;
		private Queue<Passenger> line;
		private String name;
		private int statInd;

		public Station(int ind, String n) {
			line = new LinkedList<Passenger>();
			name = n;
			statInd = ind;
		}

		public void run() {
			while (true) {

				synchronized (stations.get(statInd)) {
					if (current == null) {

						try {
							stations.get(statInd).wait();
						} catch (InterruptedException e) {
							System.exit(-1);
						}

					}
					
					while (!line.isEmpty()) {
						Passenger firInLine = line.remove();
						current.passengers.get(firInLine.destInd).add(firInLine);
					}
					
					current.move(statInd);
					
				}
			}
		}
		
		private void clearTheTrain () {
			LinkedList<Passenger> levPeeps = current.passengers.get(statInd);
			levPeeps.clear();
			
			while (current.capacity<=current.maxCapacity&&!line.isEmpty()) {
				Passenger firInLine = line.remove();
				current.passengers.get(firInLine.destInd).add(firInLine);
				current.capacity++;
			}
		}
		
	}

	class Passenger {
		private int theId;
		private int destInd;

		public Passenger(int d) {
			theId = id;
			destInd = d;
			id++;
		}
	}
}

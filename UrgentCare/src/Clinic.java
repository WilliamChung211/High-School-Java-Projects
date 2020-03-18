/*
 * Name: William Chung
 * 
 * This program represents a clinic with a waiting room
 * with n chairs and one doctor who is waiting to see the patients.
 * 
 */
public class Clinic implements Runnable {

	private ChungWilliam_LimitedPQ<Patient> waitPats;
	private final long showUpBound;
	private int nextPatID;
	private boolean docIsSleep;
	
	public Clinic(int max, int showBound) {
		waitPats = new ChungWilliam_LimitedPQ<Patient>(max);
		showUpBound = showBound;
		nextPatID = 1;
	}

	public void run() {

		Thread docMario = new Thread(new Doctor());
		docMario.start();
		
		while (true) {

			Patient newPat = new Patient();
			System.out.print("Patient " + newPat.patientID + " came with a priority of " + newPat.priority);
			Patient leavingPatient;
			
			//the patient comes to the waiting room at a random interval. we synch this so patients don't enter and leave the waiting room at the same time
			synchronized (waitPats) {
				leavingPatient = waitPats.add(newPat);
			}
				
			//we then check if the patient has enough priority to wait or just go to the hospital
			if (leavingPatient == newPat) {
				System.out.println(", but decided to go to the hospital instead");
			}

			else {
				System.out.println(" and waited in the waiting room");
				
				//if the doctor is sleeping while a new person entered the waiting room, we wake him up
				if (docIsSleep) {
					synchronized (waitPats) {
						waitPats.notify();
						System.out.println("The doctor woke up");
						docIsSleep = false;
					}
				}
			}
			
			try {
				Thread.sleep((int) (Math.random() * (showUpBound+1)));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
	}

	//this class represents a doctor that takes a random amount amount time to treat a patient
	class Doctor implements Runnable {
		private final int seeTime;

		public Doctor() {
			seeTime = (int) (Math.random() * 1000) + 4000;

		}

		public void run() {

			while (true) {
				
				if (waitPats.isEmpty()) {

					//if the waiting room is empty, the doctor takes a nap
					synchronized (waitPats) {
						try {
							System.out.println("The doctor fell asleep");
							docIsSleep = true;
							waitPats.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				} 
				else {
					
					//we synchronize the patient leaving the waiting room since we do not want patients entering and leaving the waiting room at the same time
					int name;
					synchronized (waitPats) {
						name = waitPats.remove().patientID;
					}
					System.out.println("Doctor is treating patient " + name);
					
					//this simulates how long it takes to treat the patient
					try {
						Thread.sleep(seeTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}

			}
		}

	}

	//This class represents a patient with a unique ID and priority who wants to enter the clinic
	class Patient implements Comparable<Patient> {

		private int patientID;
		private int priority;

		public Patient() {

			priority = (int) (Math.random() * 20) + 1;
			patientID = nextPatID;
			nextPatID++;
		}

		public int compareTo(Patient other) {
			return priority - other.priority;
		}
	}

	public static void main(String[] args) {
		new Thread(new Clinic(10,4000)).run();
	}

}

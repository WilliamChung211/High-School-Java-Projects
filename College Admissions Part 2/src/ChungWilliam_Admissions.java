import java.util.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/*
 * Name: William Chung
 * 
 * This program is for those lazy colleges who instead now conspire 
 * with each other using an algorithm to find a matching applicant. 
 * The  algorithm is that students will keep applying to their most
 * preferred college that they have not applied to till all students have matches.
 * Colleges can reject students and unmatch them if a more preferred student
 * applies to them. Also, the colleges and students are the same number.
 * 
 *  Thought Provoking Question Answer:
 *  The most number of unhappy pairs is zero because the if statements of the 
 *  algorithm makes it so that every college or student will get their most
 *  preferred option who is available.
 */

public class ChungWilliam_Admissions {

	private ArrayList<Student> studs;
	private ArrayList<College> colgs;
	private Queue<Student> unMacStuds;
	
	public ChungWilliam_Admissions(String stuNames, String colNames) {

		Scanner stuFile = null;
		Scanner colFile = null;

		try {
			stuFile = new Scanner(new File(stuNames));
			colFile = new Scanner(new File(colNames));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}

		studs = new ArrayList<Student>();
		colgs = new ArrayList<College>();

		//stores all the student names
		while (stuFile.hasNextLine()) {
			studs.add(new Student(stuFile.nextLine()));
		}

		//stores all the colleges names and generates their rankings of students
		while (colFile.hasNextLine()) {

			College coll = new College(colFile.nextLine());
			coll.generateRankings(studs);
			colgs.add(coll);
		}
		
		//this algorithm works with the same number of colleges and students
		if(studs.size()!=colgs.size()) {
			throw new IllegalArgumentException("Files must have same number of names");
		}
		
		unMacStuds = new LinkedList<Student>();
		 
		//makes the list of all unmatched students will generating their rankings
		for (int i = 0; i < studs.size(); i++) {
			studs.get(i).generateRankings(colgs);
			unMacStuds.add(studs.get(i));
		}

	}
	
	//runs the previously described algorithm while outputting the prospects and results
	public void match() {
	
		System.out.println("Student");
		System.out.println("---------");
		
		for (int i = 0; i < studs.size(); i++) {
			System.out.println(studs.get(i) + ": " + studs.get(i).getRankings());
		}
		
		System.out.println();
		System.out.println("Colleges");
		System.out.println("---------");
		
		for (int i = 0; i < studs.size(); i++) {
			System.out.println(colgs.get(i) + ": " + colgs.get(i).getRankings());
		}
		
		
		//keeps having the unmatched student apply to their next most preferred college
		while (!unMacStuds.isEmpty()) {

			
			Student current = unMacStuds.remove();
			
			//if the student is accepted to the college or more preferred, they are matched together
			if (current.apply()) {
				
				//makes the new student the new match and adds their old match back to the pool if they had one
				College match = (College) current.getMatch();
				match.acceptStudent(current);
				
			}
			
			//if the student is not preferred, then they are placed back in the unmatched pool
			else {
				unMacStuds.add(current);
			}
		}
		
		//outputs the results
		System.out.println();
		System.out.println(String.format(" %-8s %-4s", "Student", "College"));
		System.out.println("---------------------");
		
		for (int i = 0; i < studs.size(); i++) {
			System.out.println(String.format(" %-8s %-4s", studs.get(i), studs.get(i).getMatch()));
		}
		
	}

	//this class represent a university that can check if a student is preferred and accept them
	public class College extends Prospect {

		public College(String name) {
			super(name);
		}

		//returns true if the college prefers the other student over their current match or there is no current match
		public boolean isPreferred(Student other) {

			if (getMatch() == null) {
				return true;
			}

			Prospect match = getMatch();
			ArrayList<Prospect> rankings = getRankings();

			//checks to see who is higher in the college's rankings
			for (int i = 0; i < rankings.size(); i++) {
		
				if (rankings.get(i).equals(other)) {
					return true;
				} 
				else if (rankings.get(i).equals(match)) {
					return false;
				}
				
			}

			//this result will never happen
			return false;
		}

		//accepts the parameter as a match and updates the old match and adds them back in the pool
		public void acceptStudent(Student stu) {

			Student rejected = (Student) getMatch();
			setMatch(stu);

			if (rejected != null) {
				rejected.setMatch(null);
				unMacStuds.add(rejected);
			}
			
		}
	}

	//This class represent a a student applying to a school
	public class Student extends Prospect {
		
		private int numColsApp;
		
		public Student(String name) {
			super(name);
		}

		//applies to the student's next most preferred school and returns the result
		public boolean apply() {

			//a student will not apply to a school they already applied to
			College topCollege = (College) 	getRankings().get(numColsApp);
			numColsApp++;
			
			//if the student is accepted, it returns true and updates their match status
			if (topCollege.isPreferred(this)) {
				setMatch(topCollege);
				return true;
			}

			return false;
		}
	}

	//this class stores the name and rankings of a student or college object
	public class Prospect {
		private ArrayList<Prospect> ranking;
		private Prospect match = null;
		private String name;

		public Prospect(String name) {
			ranking = new ArrayList<Prospect>();
			this.name = name;

		}

		public void generateRankings(ArrayList<? extends Prospect> prospectNames) {

			ArrayList<Prospect> copy = new ArrayList<Prospect>();

			for (int i = 0; i < prospectNames.size(); i++)
				copy.add(prospectNames.get(i));

			while (copy.size() != 0) {
				ranking.add(copy.remove((int) (Math.random() * copy.size())));
			}
		}

		public void setMatch(Prospect val) {
			match = val;
		}

		public Prospect getMatch() {
			return match;
		}

		public ArrayList<Prospect> getRankings() {
			return ranking;
		}

		public String toString() {
			return name;
		}

		// assume everyone has a diff name
		public boolean equals(Object o) {
			if (!(o instanceof Prospect))
				return false;
			Prospect other = (Prospect) o;
			return name.equals(other.name);
		}
	}

	public static void main(String[] args) {
		new ChungWilliam_Admissions("names.txt", "schools.txt").match();
	}
}


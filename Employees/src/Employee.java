/*
 * Name: William Chung
 * This class represents an Employee that has a name, an ID number, a salary, and a deparment name.
 */

public class Employee {
	
	private String name; 
	public int IDnum;
	private static int nextID = 1;
	private int salary;
	private String depName;
	
	public Employee(String nam, int sal, String dName){
		
		changeSalary(sal);
		
		//makes a different ID number for each person
		IDnum = nextID;
		nextID++;
		
		name = nam;
		depName = dName;
		
	}
	
	public void changeSalary(int sal){
		
		if (sal<0){
			throw new IllegalArgumentException("Salary cannot be negative.");
		}
		
	
		salary = sal;
	}
	
	//Increases the Employee's new salary by a positive double parameter.
	public void changeSalary(double perc){
		
		if (perc<0){
			throw new IllegalArgumentException("We can only increase the salary");
		}
		
		salary *=(1+perc);
	}
	
	public int getSalary(){
		return salary;
	}
	
	public String getDep(){
		return depName;
	}
	
	public int getID(){
		return IDnum;
	}
	
	public String toString(){
		return "\n Name: " + name + "\n ID Number: " + IDnum + "\n Total Salary: " + getSalary() + "\n Department Name: " + depName;
	}
}

import java.util.NoSuchElementException;

/*
 * Name: William Chung
 * This class represents a manager who is a supervisor who keeps track of all employees within their department.
 * 
 * 1. We do not need to write a toString method for a client to print out the toString method of the Executive Class.
 * This is because since the Executive class is the subclass of the Manager class, it inherits all its methods including
 * the toString method.
 * 
 * 2. What is wrong is that the toString only shows the base manager salary without any bonus. This is because
 * the instance variable of salary is only the individual salary without any bonus. However, this misrepresents
 * what the executive actually earns because he earns much more with the employees and the bonus.
 * 
 * 3. Replacing the instance variable with the method  will change this because it will then use the getSalary methods
 * that were created which display the more accurate/specific version of the salary as it does the calculations.
 * This is because the toString will call the specific version of the getSalary method based on the class used.
 * 
 */

public class Manager extends Employee {

	private Employee[] employees;

	public Manager(String nam, int sal, String dName) {
		super(nam, sal, dName);
		employees = new Employee[0];
	}

	// Takes in an employee parameter and adds that Employee to the manager's list of Employees
	public void addEmployee(Employee employ) {

		// checks if the employee is in the same department
		if (!employ.getDep().equals(getDep())) {
			throw new IllegalArgumentException("Different Department from manager");
		}

		else {

			// resizes and copies the array and adds the other employee
			Employee[] temp = new Employee[employees.length + 1];

			for (int i = 0; i < employees.length; i++) {
				
				//checks if the employee was already there using their unique id number. if he is, nothing is added
				if(employees[i].getID()==employ.getID()) {
					return;
				}
				
				temp[i] = employees[i];
				
			}

			temp[employees.length] = employ;
			employees = temp;
		}

	}

	// Takes in an Employee ID and removes that employee from the manager list
	public void removeEmployee(int id) {

			//the array is made smaller and everything but that employee is copied
			Employee[] temp = new Employee[employees.length - 1];

			int ind =0;
			
			//checks when the employee ID is found the array
			for (; ind < temp.length&& id!=employees[ind].getID(); ind++) {
				temp[ind] = employees[ind];
			}
			
			//checks if the entire loop ran without the employee being found and throws an exception if so
			if (ind==temp.length) {
				throw new NoSuchElementException("The employee cannot be found.");
			}
				
			//if id found, array will then copy all the employees to the right of it
			for (; ind < temp.length; ind++) {
				temp[ind] = employees[ind + 1];
			}

			employees = temp;
		
	}

	//returns total of salaries for all the Manager's subordinates plus the Manager's individual salary
	public int getSalary() {
		int totSal = super.getSalary();

		for (int i = 0; i < employees.length; i++) {
			totSal += employees[i].getSalary();
		}

		return totSal;
	}

	public String toString() {
		String info = super.toString() + "\n\n Employees:";

		for (int i = 0; i < employees.length; i++) {
			info += "\n" + employees[i];
		}

		return info;
	}
}

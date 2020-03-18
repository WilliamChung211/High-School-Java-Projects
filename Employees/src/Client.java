public class Client {

	public static void main(String[] args) {
		
		Employee Steve = new Employee("Steve", 20, "Computer");
		Manager Frank = new Manager("Frank", 50, "Computer");
		Executive John = new Executive("John", 70, "Computer", .66);
		Frank.addEmployee(Steve);
		Frank.addEmployee(new Employee("Lyle", 30, "Computer"));
		Frank.addEmployee(new Employee("Lylez", 20, "Computer"));
		Frank.addEmployee(new Employee("Lylet", 15, "Computer"));
		Frank.addEmployee(new Employee("Lylem", 10, "Computer"));
		System.out.println(Frank);
		Frank.addEmployee(new Employee("Lyle", 15, "Computer"));
		Frank.addEmployee(new Employee("Jake", 25, "Computer"));
		Frank.addEmployee(new Employee("Tom", 35, "Computer"));

		Frank.addEmployee(new Employee("Drew", 4, "Computer"));
		Frank.removeEmployee(1);

		John.addEmployee(Frank);
		System.out.println(John);

	}
}

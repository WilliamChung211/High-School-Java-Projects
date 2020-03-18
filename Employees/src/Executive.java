/*
 * Name: William Chung
 * This class represents an executive which is a manager who earns a manger's salary plus a percentage bonus on top.
 */
public class Executive extends Manager {

	private double bonus;
	
	public Executive(String nam, int sal, String dName, double bon){
		super(nam, sal, dName);
		bonus = bon;
		
	}
	
	public int getSalary(){
		return (int)(super.getSalary()*(1+bonus));
	}
}

import java.util.ArrayList;


public class Movie {

	private String name;
	private String fName;
	private ArrayList<Screening> screens;
	
	public Movie(String mName, String file){
		screens = new ArrayList<Screening>();
		name = mName;
		fName = file;
	}
	
	public void add(Screening screen){
		
		int ind=0;
		while( ind<screens.size() &&(screens.get(ind).getTime().compareTo(screen.getTime())<=0)){
			ind++;
		}
		
		screens.add(ind,screen);
	}
	
	
	public ArrayList<Screening> getScreens(){
		return screens;
	}
	
	public String getName(){
		return name; 
	}
	
	public String getFile(){
		return fName; 
	}
}

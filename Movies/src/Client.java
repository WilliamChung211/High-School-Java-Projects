
public class Client {

	public static void main(String[]args){
		Movie movie = new Movie("Ave", "Steve");
		movie.add(new Screening(new Time(20,3)));
		movie.add(new Screening(new Time(3,3)));
		System.out.println(movie);
	}
	
}

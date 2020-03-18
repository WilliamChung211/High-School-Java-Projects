import java.util.Iterator;

public class Client {
	public static void main(String[] args) {
		// Takes in the two files

		FileArrayList actors = new FileArrayList("actors.txt");
		FileArrayList movies = new FileArrayList("movies.txt");
		Iterator<String> actorIter = actors.iterator();

		actorIter.next();
		actorIter.remove();
		actorIter.next();
		actorIter.remove();
		// Goes through each actor in the actor file
		for (int i = 0; i < 15; i++) {

			Iterator<String> movIter = movies.iterator();
			String actor = actorIter.next();
			System.out.println(actor);
		}

		actorIter.remove();
		// actorIter.next();

		System.out.println("GAPPPP");
		for (String hi : actors) {
			System.out.println(hi);
		}
	}
}
import java.util.Iterator;

public class SplitClient {

	public static void main(String[] args) {
		FileLinkedList actors = new FileLinkedList("actors.txt");
		FileLinkedList movies = new FileLinkedList("movies.txt");
		Iterator<String> actorIter = actors.iterator();

		while (actorIter.hasNext()) {
			Iterator<String> movIter = movies.iterator();
			String actor = actorIter.next();
			System.out.println(actor + ":");

			while (movIter.hasNext()) {
				String movie = movIter.next();
				String []movAct = (movie.substring(38,84).split(", "));
				int index = find(movAct,actor);
				if(index!=-1){
					System.out.println("    " + movie.substring(5, 38));
				}
			
			}

		}

	
	}

	public static void printAll(String[]movies){
		for(int i =0;i<movies.length;i++){
			System.out.println(movies[i]);
		}
	}
	public static int find(String[] movies, String find){


		for(int i =0;i<movies.length;i++){
			if(movies[i].equals(find)){

				return i;
			}
		}
		return -1;
		
	}
}

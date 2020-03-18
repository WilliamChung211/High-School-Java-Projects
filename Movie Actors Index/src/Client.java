import java.util.Iterator;

/*
 * Name: William Chung
 * This program has two FileLinkedList objects and two iterators and
 * use classic String methods such as substring and split to show
 * every movie that an artist appeared in given the file data.
 */

public class Client {

public static void main(String[] args) {
		
		//Takes in the two files
		FileLinkedList actors = new FileLinkedList("actors.txt");
		FileLinkedList movies = new FileLinkedList("movies.txt");
		Iterator<String> actorIter = actors.iterator();

		//Goes through each actor in the actor file
		while (actorIter.hasNext()) {
			
			Iterator<String> movIter = movies.iterator();
			String actor = actorIter.next();
			
			System.out.println(actor + ":");
			
			//Goes through every movie and its info in the movie file
			while (movIter.hasNext()) {
				
				String movInfo = movIter.next();
				
				//gets the list of actors who acted in the movie
				String []movActors = movInfo.substring(38,84).split(", ");
			
				//If the specific artist acted in the movie, it prints out the movie name
				if(findActor(movActors,actor)){
					System.out.println("    " + movInfo.substring(5, 38));
				}
				
			}
		}
}

		//returns true only if the specified actor's name is the complete same match as an actor's name in the list
		private static boolean findActor(String[]actList, String actName) {
			
			//gets rid of the white space for the last actor name in the list
			String lastAct = actList[actList.length-1];
			int index = lastAct.indexOf(" ");
			actList[actList.length-1]= lastAct.substring(0, index)+lastAct.substring(index,lastAct.substring(index+1).indexOf(" ")+index+1);
			
			//checks to see if there is a complete match with the actor's name in the actor list
			for(int i =0;i<actList.length;i++) {
				
				if(actList[i].equals(actName)) {
					return true;
				}
				
			}
			
			return false;
		
	}

}

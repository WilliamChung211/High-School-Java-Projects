/*
 * Name: William Chung
 * This is a subclass of the Book clas that represents a magazine which is a book 
 * that also maintains a genre, such a sports, fashion, news, etc.
 */
public class Magazine extends Book {
	
	private String genre;
	
	public Magazine(String tit, double pric, int quant, String gen){
		super(tit,pric,quant);
		genre = gen;
	}

	public String toString(){
		return super.toString() + "\n Genre: " + genre;
	}
}

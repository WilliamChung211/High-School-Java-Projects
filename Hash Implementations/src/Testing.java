
public class Testing implements Hashable{

	@Override
	public int computeHash(String name) {
		return Math.abs((Integer.parseInt(name)-2)*4);
	}

	
}

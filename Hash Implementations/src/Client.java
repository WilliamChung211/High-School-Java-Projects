
public class Client {

	static private boolean box;
	public static void main(String[]args) {
		ChungWilliam_HashProbing hi = new ChungWilliam_HashProbing(10, new TestProbable(),97);


		hi.add(2);
		hi.add(12);
		hi.add(22);
		hi.remove(12);
		hi.remove(22);
		System.out.println(hi.remove(2));
		System.out.println(hi);
		}
}

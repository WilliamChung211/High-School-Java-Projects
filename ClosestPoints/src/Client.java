
public class Client {

	public static void main(String[] args) {
	
	for(int ind =3;ind<100;ind++) {
		for (int i = 0; i < 999; i++) {
			ChungWilliam_PointSet set = new ChungWilliam_PointSet(10+ind, 10+ind, ind);
		
			
			PointPairSet pps = set.closestPointBF();
			PointPairSet dNC = set.closestPointDC();
			
			if(!pps.equals(dNC)) {
				System.out.println(set);
				System.out.println(pps);
				System.out.println(dNC);
				System.out.println("THE STATEMENT BELOW IS A LIEEEEEEE!!!!!!");
				ind = 100;
				break;
			}
		}
	
		System.out.println("WE GOOD!!! " +  ind);
		
	}
	}
}

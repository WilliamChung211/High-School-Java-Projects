import java.util.ArrayList;
import java.util.Collection;

public class Client {

	public static void main(String []args) {
		
		ArrayList<String>list = new ArrayList<String>();
		String[]houses = {"Stark", "Karstark", "Flint","Umber", "Mormont", "Hi", "Tully", "Mormonth", "Manderly", "Wull", "Westeron", "Reed", "Liddle", "Glover"};
		for(String name: houses) {
			list.add(name);
		}
		ChungWilliam_AllHouses allH = new ChungWilliam_AllHouses(list);
		allH.combine("Stark", "Karstark");
		allH.combine("Stark", "Umber");
		allH.combine("Stark", "Tully");
		allH.combine("Stark", "Reed");
		allH.combine("Karstark", "Flint");
		allH.combine("Umber", "Mormont");
		allH.combine("Tully", "Manderly");
		allH.combine("Tully", "Wull");
		allH.combine("Manderly", "Westeron");
		allH.combine("Reed", "Liddle");
		allH.combine("Liddle", "Glover");
		System.out.println(allH.sameBanner("Karstark", "Stark"));
	}
}

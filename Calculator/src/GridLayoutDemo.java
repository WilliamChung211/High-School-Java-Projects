
import javax.swing.*;
import java.awt.*;
public class GridLayoutDemo extends JFrame {

	public GridLayoutDemo(){
		
		setSize(300,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setBackground(Color.blue);
		
		
		setLayout(new GridLayout(3,2,5,5));
		
		
		
		int counter = 1;
		for(int row = 0; row < 3; row++){
			for( int col = 0; col < 2; col++){
				add(new JButton(counter+""));
				counter++;
			}
		}
	
		
		setVisible(true);
	}
	
	public static void main(String[] args){
		new GridLayoutDemo();
	}
}


import javax.swing.*;
import java.awt.*;

public class NullLayout extends JFrame {

	public NullLayout(){
		
		setSize(400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(null);
		
		JButton button = new JButton("Press Me!");
		button.setBounds(40,40,100,30);
		
			
		add(button);
		setVisible(true);
	}
	
	public static void main(String[] args){
		new NullLayout();
	}
}

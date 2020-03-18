
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ButtonDemo extends JFrame implements ActionListener{

	private JButton first;
	private JButton second;
	private JLabel display;
	
	
	public ButtonDemo(){
		
		
		setTitle("Button Example");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(240,120);
		
		//Values displayed on top of the buttons
		first = new JButton("First");
		second = new JButton("Second");
		
		//second.setEnabled(false);
		
		//initial value stored in JLabel
		display = new JLabel("Press a Button");
		
		//register event handling with the buttons and this program
	  	  first.addActionListener(this);
	   	 second.addActionListener(this);
		
		//change the layout to insert widgets from left to right
		setLayout(new FlowLayout());
		
		//add all widgets
		add(first);
		add(second);
		add(display);
		
		setVisible(true);
	}
	
	//called any time either button one or button two is pressed
	public void actionPerformed(ActionEvent ae){
		
		if(ae.getActionCommand().equals("First")){
			display.setText("First Button was Pressed");
			
		}
		else{
			display.setText("Second Button was Pressed");
			
		}
	}
	
	public static void main(String[] args){
		ButtonDemo bd = new ButtonDemo();
	}
}

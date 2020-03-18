
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CheckBoxDemo extends JFrame implements ActionListener{

	private JCheckBox duck;
	private JCheckBox squirtle;
	private JCheckBox pika;
	private JLabel theLabel;
	
	public CheckBoxDemo(){
		setTitle("Checkbox Demo");
		setSize(500,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		theLabel = new JLabel("I choose:");
		theLabel.setFont(new Font("Comic Sans", Font.PLAIN,18));
		theLabel.setBounds(150,125,350,100);
		
		duck = new JCheckBox("Psyduck");
		pika = new JCheckBox("Pikachu");
		squirtle = new JCheckBox("Squirtle");
		
		duck.addActionListener(this);
		pika.addActionListener(this);
		squirtle.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,1));
		panel.add(duck);
		panel.add(pika);
		panel.add(squirtle);
		panel.setBounds(175,50,150,80);
		
		this.add(panel);
		this.add(theLabel);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae){
		
		String text = "I choose: ";
		if(duck.isSelected()){
			text += "Psyduck ";
		}
		
		if(pika.isSelected()){
			text += "Pikachu ";
		}
		
		if(squirtle.isSelected()){
			text += "Squirtle ";
		}
		
		theLabel.setText(text);
	}
	
	public static void main(String[] args){
		new CheckBoxDemo();
	}
}

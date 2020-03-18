
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

//this version will update the combo box on the fly


public class BetterComboBoxDemo extends JFrame implements ActionListener {

	private JLabel theLabel;
	private JComboBox theBox;
	private Color[] colors = {Color.red,Color.blue,Color.green,Color.yellow};
	private String[] colorNames = {"Red","Blue","Green","Yellow"};
	private JButton button;
	private DefaultComboBoxModel theModel;
	
	public BetterComboBoxDemo(){
		
		setTitle("Combo Box Demo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,325);
		setLayout(null);
		
		getContentPane().setBackground(Color.WHITE);		
		
		theModel = new DefaultComboBoxModel(colorNames);
		
		theBox = new JComboBox(theModel);
		theBox.addActionListener(this);
		theBox.setBounds(70,60,150,25);
			
		button = new JButton("Add magenta");
		button.setBounds(90,175,110,25);
		add(button);
		button.addActionListener(this);		
		
		
		theLabel = new JLabel("Selected");
		theLabel.setBounds(105,100,100,25);
		
		add(theBox);
		add(theLabel);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae){
	
		if(ae.getActionCommand().equals("Add magenta")){
			
			Color[] temp = new Color[colors.length+1];
			for(int i = 0; i < colors.length; i++)
				temp[i] = colors[i];
			colors = temp;
			colors[colors.length-1] = Color.MAGENTA;
			
			theModel.addElement("Magenta");
			button.setEnabled(false);
		
		}
		else {	
			
			int selected = theBox.getSelectedIndex();			
			theLabel.setForeground((colors[selected]));
		}
	}
	
	public static void main(String[] args){
		new BetterComboBoxDemo();
	}
	
}

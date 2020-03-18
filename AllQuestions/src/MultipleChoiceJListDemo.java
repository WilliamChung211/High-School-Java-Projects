import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

// handles multiple choices.  Does NOT set List Selection....
public class MultipleChoiceJListDemo extends JFrame implements ListSelectionListener{
	
	private JList list;
	private JLabel jlab;
	
	private final String[] shows = {"Lost", "The Shield", "Greek", "30 Rock", "The Office", "House", "One Tree Hill", "MTV Hits"};
	
	public MultipleChoiceJListDemo(){
		
		setTitle("Multiple Choice");
		setSize(200,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		jlab = new JLabel("Make a Selection: ");
		
		list = new JList(shows);  // one string element per "line" in the JList
		
		
		list.addListSelectionListener(this);  // register for listening
		
		JScrollPane jscp = new JScrollPane(list);  // wrap that List and let it scroll!!!!!
		jscp.setBounds(50,15,100,100);
		
		jlab.setBounds(25,120,150,60);
		
		add(jscp);
		add(jlab);
		
		setVisible(true);
	}
	
	// THIS is the primary method that changes.  Now you will retrieve
	// an array of locations...
	public void valueChanged(ListSelectionEvent le){
		
		int[] indicies = list.getSelectedIndices();
		String temp= "";
		
		for( int i = 0; i < indicies.length; i++)
			temp += shows[indicies[i]] + "<br>";
	
		jlab.setText("<html>Selections :<br> " + temp + "</html>");
	}
	
	public static void main(String[] args){
		MultipleChoiceJListDemo d = new MultipleChoiceJListDemo();
	}
}

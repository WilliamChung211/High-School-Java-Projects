
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MenuDemo extends JFrame implements ActionListener{

	
	private JMenuItem jmiOpen;
	private JMenuItem jmiClose;
	private JMenuItem jmiExit;
	private JMenuItem jmiRed;
	
	public MenuDemo(){
		
		setTitle("Menu Demo");
		setSize(400,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// store all menu options in a menu bar.
		
		JMenuBar jmb = new JMenuBar();
		// jmb typically contains JMenu objects
		// JMenu objects may contain MenuItem objects or other JMenu objects
		
		JMenu jmFile = new JMenu("File");
		
		jmiOpen = new JMenuItem("Open");
		jmiClose = new JMenuItem("Close");
		jmiExit = new JMenuItem("Exit");
		
		// add File items to JMenu
		jmFile.add(jmiOpen);
		jmFile.add(jmiClose);
		jmFile.add(jmiExit);
		
		// can imbed menus within menus
		JMenu jmOptions = new JMenu("Options");
		JMenu jmCol = new JMenu("Color");
			
		jmiRed = new JMenuItem("Red");
		
		jmCol.add(jmiRed);
		jmOptions.add(jmCol);
		
		// add all JMenu objects to the menu bar
		
		jmb.add(jmFile);
		jmb.add(jmOptions);
		
		setJMenuBar(jmb);
		
		//all JMenu Items should trigger events.
		jmiOpen.addActionListener(this);
		jmiClose.addActionListener(this);
		jmiExit.addActionListener(this);
		jmiRed.addActionListener(this);
		
		setVisible(true);
	}
	
	// handles all events being triggered.  I'm only handling exiting...
	public void actionPerformed(ActionEvent ae){
		if(ae.getActionCommand().equals("Exit"))
			System.exit(0);
	}
	
	public static void main(String[] args){
		MenuDemo m = new MenuDemo();
	}
}

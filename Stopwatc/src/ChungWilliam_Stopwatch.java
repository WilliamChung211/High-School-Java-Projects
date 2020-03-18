/*
 * Name: William Chung
 * 
 * This class represents a stopwatch. It is a GUI that displays the elapsed time between
 * two button presses (the start and stop)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ChungWilliam_Stopwatch extends JFrame implements ActionListener{

	private JButton start;
	private JButton stop;
	private JLabel display;
	private long initialTime;

	//makes the start button enabled only with directions displayed.
	public ChungWilliam_Stopwatch(){

		setTitle("Stopwatch");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(230,120);

		start = new JButton("Start");
		stop = new JButton("Stop");

		stop.setEnabled(false);
		display = new JLabel("Press Start to begin timing.");

		start.addActionListener(this);
		stop.addActionListener(this);
		setLayout(new FlowLayout());

		add(start);
		add(stop);
		add(display);

		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae){

		//stores the time start button was pressed and enables the other button while disabling start button.
		if(ae.getActionCommand().equals("Start")){
			
			display.setText("The stopwatch is currently running");
			stop.setEnabled(true);
			start.setEnabled(false);
			initialTime = System.currentTimeMillis();
			
		}

		//calculates the elapsed time by usining the current time minus the initial time and displays
		else{
			
			//formats time to seconds and 3 decimal places 
			double time = (System.currentTimeMillis()-initialTime)/1000.0;
			String text =  String.format(" Elapsed Time: %.3f seconds", time);
			display.setText(text);
			start.setEnabled(true);
			stop.setEnabled(false);
			
		}

	}

	public static void main(String[] args){
		ChungWilliam_Stopwatch watch = new ChungWilliam_Stopwatch();
	}
	
}



import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class TimerAndKeyListenerDemo extends JFrame implements KeyListener{

	private JLabel message;
	private JPanel colorPanel;
	private Timer timer;		//be sure you are using the javax.swing.Timer class. (not util)
	private int counter;

	public TimerAndKeyListenerDemo(){

		setSize(400,300);
		this.getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Key Demo");
		setLayout(null);

		this.addKeyListener(this);

		colorPanel = new JPanel();
		colorPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		colorPanel.setBounds(90,50,200,100);

		message = new JLabel("Key: ");
		message.setBounds(125,175,100,40);
		message.setFont(new Font("Times New Roman",Font.PLAIN,28));

		//this timer will refresh every 5 miliseconds
		//the timer still needs to be started in order for 
		//anything to happen
		timer = new Timer(5, new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {

				//oscillates the timer back and forth
				if(counter%2 == 0)
					colorPanel.setBackground(Color.yellow);
				else
					colorPanel.setBackground(Color.cyan);
				counter++;
				
				//stop the timer aftter a count of 1000
				if(counter == 1000){
					timer.stop();
					counter = 0;
				}
			}
		});

		add(colorPanel);
		add(message);
		setVisible(true);
	}	

	public void keyPressed(KeyEvent arg0) {

		char key = arg0.getKeyChar();

		message.setText("Key: "+key);

		if("aeiou".indexOf(key) != -1)
			colorPanel.setBackground(Color.green);
		else
			colorPanel.setBackground(Color.red);

		//the timer begins doing work with the space bar
		//is pressed
		if(arg0.getKeyCode() == KeyEvent.VK_SPACE){
			
			timer.start();
		}
	}

	public void keyReleased(KeyEvent arg0) {


	}

	public void keyTyped(KeyEvent arg0) {


	}

	public static void main(String[] args){
		new TimerAndKeyListenerDemo();
	}

}

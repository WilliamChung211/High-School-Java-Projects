import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
//import java.awt.event.*;
import javax.swing.event.*;

/*
 * Name: William Chung and George Li
 * 
 * This program allows the user to view the list of all staff members
 * for the feeder schools of the WHS cluster
 */
public class View extends JFrame implements ListSelectionListener{

	private JList schools;
	private DefaultListModel schoolModel;
	
	private JList staff;
	private DefaultListModel staffModel;
	
	
	private Map<String,ArrayList<String>> directory;
	
	public View(){

		setSize(600,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("US News and World Report Top Schools");
		setLayout(null);
		
		PicPanel mainPanel = new PicPanel("diploma.jpg");
		mainPanel.setBackground(Color.white);
		mainPanel.setBounds(0,0,600,300);
		mainPanel.setLayout(null);
		
		schoolModel = new DefaultListModel();
		schools = new JList(schoolModel);
		schools.addListSelectionListener(this);
		JScrollPane departJSP = new JScrollPane(schools);
		departJSP.setBounds(50,40,200,200);
		departJSP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Schools"));
		departJSP.setOpaque(false);
		mainPanel.add(departJSP);
		
		staffModel = new DefaultListModel();
		staff = new JList(staffModel);
		staff.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane schoolsJSP = new JScrollPane(staff);
		schoolsJSP.setBounds(300,40,250,200);
		schoolsJSP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Employees"));
		schoolsJSP.setOpaque(false);
		mainPanel.add(schoolsJSP);
		

		add(mainPanel);
		directory = new HashMap<String,ArrayList<String>>();
		loadDir();
		
		setVisible(true);
	}
	
	//loads up all the schools and its respective staff members
	private void loadDir(){
		
		Scanner catIn = null;
		Scanner schoolsIn = null;
	
		try{
			catIn = new Scanner(new File("schools"));
			schoolsIn = new Scanner(new File("sites"));
		}catch(FileNotFoundException e){
			System.out.println("At least one text file not found");
			System.exit(-1);
		}
		
		
		ArrayList<Thread> allThreads = new ArrayList<Thread>();
		int i =0;
		
		//goes through each school and adds and starts a thread for each school
		while(catIn.hasNextLine()){
			String catName = catIn.nextLine();
			String schoolSite = schoolsIn.nextLine();
			schoolModel.addElement(catName);
			allThreads.add(new Thread(new Scrape(schoolSite,catName)) );
			allThreads.get(i).start();
			i++;
		}
		
		//makes sure all threads are done adding to the directory before it launches
		for(Thread nextThread: allThreads) {
			try {
				nextThread.join();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		directory = Scrape.getDirectory();
	}


	public void valueChanged(ListSelectionEvent le){

		staffModel.removeAllElements();

		int location = schools.getSelectedIndex();
		if(location == -1)
			return;

		String cat = (String)schoolModel.get(location);
		ArrayList<String> allSchools = directory.get(cat);

		for(String school: allSchools){
			staffModel.addElement(school);
		}
	}



	
	class PicPanel extends JPanel{

		private BufferedImage image;
		private int w,h;
		public PicPanel(String fname){

			try {
				image = ImageIO.read(new File(fname));
				w = image.getWidth();
				h = image.getHeight();

			} catch (IOException ioe) {
				System.out.println("Could not read in the pic");
				System.exit(0);
			}

		}

		public Dimension getPreferredSize() {
			return new Dimension(w,h);
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(image,0,0,this);
		}
	}

	public static void main(String[] args){
		new View();
		
	}
}

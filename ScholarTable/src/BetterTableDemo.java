import java.util.Scanner;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;


public class BetterTableDemo extends JFrame implements ActionListener{

	private DefaultTableModel theModel;

	private JButton theButton;
	private JTable theTable;
	
	public BetterTableDemo() throws FileNotFoundException{
	
		setTitle("Table Demo");
		setSize(200,260);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		getContentPane().setBackground(Color.white);
		
		Scanner file = null;
		
//		either way want to create a model
		//this anonymous inner overriding is only necessary
		//if I don't want the user to edit the cells
		
		String[] colNames = {"Name","Grade"};
		
	    	theModel = new DefaultTableModel(colNames,0)		
		{
	    	//only include if you don't want user
	    	//to be allowed to edit cells
			public boolean isCellEditable(int row, int column){
				return false;
			}
			
		};
		
		file = new Scanner(new File("grades.txt"));
		int numStudents = 0;
		
		while(file.hasNextLine()){
			String name = file.nextLine();
			int grade = file.nextInt();
			
			file.nextLine();  // ignore scenario
			
			Object[] row = {name,grade};
			theModel.addRow(row);
		}
		
		// create the frame		
		 
	     theTable = new JTable(theModel);
	     
		
		//don't let them reorder the columns
		theTable.getTableHeader().setReorderingAllowed(false);
		

		JScrollPane jsp = new JScrollPane(theTable);
		jsp.setBounds(20,20,150,125);
		
		add(jsp);
		
	
		
		theButton = new JButton("All A's");
		theButton.setBounds(55,170,80,20);
		theButton.addActionListener(this);
		add(theButton);
		
		setVisible(true);
		
		
	}
	
	public void actionPerformed(ActionEvent ae){
		
		DefaultTableCellRenderer theRenderer = new DefaultTableCellRenderer(){
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col){
				Component toReturn = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

				toReturn.setForeground(Color.red);
				toReturn.setBackground(new Color(144,246,238));
				
				return toReturn;
			}
		};
				
		//changed the renderer (how it's painted)
		//for just the second column.
		theTable.getColumnModel().getColumn(1).setCellRenderer(theRenderer);
		
		theTable.repaint(); //show this off by commenting out last question
		
		/*for(int i = 0; i < theModel.getRowCount(); i++){
			theModel.setValueAt(100, i, 1);
			
		}
	*/
		
		theButton.setEnabled(false);
	}
	
	
	
	public static void main(String[] args) throws FileNotFoundException {
	
		
		new BetterTableDemo();
	}
	
}

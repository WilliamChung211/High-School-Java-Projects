
import java.util.*;
import java.awt.event.ActionEvent;
import java.io.*;

import javax.swing.*;

//this code demonstrates how the user can navigate and graphically select
//a file.  This can be used for opening, and a small modification
//can make this work for saving.

public class FileChooserDemo {

	public static void main(String[] args){
		
		
		JFileChooser jfc = new JFileChooser();
		jfc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		

		// pop up the file menu box, "null" means we don't want to associate it with a component

		//if you want a Save Box to show instead
		//call the method showSaveDialog....
		int result = jfc.showOpenDialog(null);

		//the box will stay open until the user hits ok, cancel, x....

		//if the user selectes "Ok", extract the file 

		File f=null;
		Scanner sc = null;
		if(result == JFileChooser.APPROVE_OPTION){
			f = jfc.getSelectedFile();

			//read and display the data from the file

			try{
				sc = new Scanner(f);
			}catch(FileNotFoundException e){
				System.out.println("File Not Found!");
				System.exit(-1);
			}


			while(sc.hasNextLine()){
				System.out.println(sc.nextLine());
			}
		}
		
		public void ActionPerformed(ActionEvent ae){
			if(ae.getActionCommand().equals("Exit"))
				System.exit(0);
		}
	}
}

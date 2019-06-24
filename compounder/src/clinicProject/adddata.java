package clinicProject;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class adddata extends JApplet implements Runnable {
JTextField txadd;
JButton btadd;
JLabel lb;
JPanel p1;
Thread Th;
	public void init() 
	{
		 lb=new JLabel();
		 lb.setIcon(new ImageIcon("download.png"));
			
		 setLayout(new FlowLayout());
		add(lb);
		
		
		/*try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
			
			
			//Data base creating
			Statement sta=con.createStatement();
			sta.executeUpdate("create database if not exists Patient_History");
			sta.execute("use Patient_History");
			//creating table
			
			sta.executeUpdate("create table if not exists History(date varchar(11),name varchar(100) , age varchar(10) , mobile_No varchar(13) ,Gender varchar(7),History_of_Prev_opretion varchar(7),History_of_any_treatment varchar(7),VisionR varchar(10),VisionL varchar(10),complain_with_Duration varchar(255),History_Of_other_diseases varchar(255),IOP_R varchar(10),IOP_L varchar(10),Diagnostic varchar(255),Treatment varchar(255))");
			//ResultSet rs=sta.executeQuery("");
	        
			
		
			
			con.close();
		} catch (ClassNotFoundException ee) {
			// TODO Auto-generated catch block
			ee.printStackTrace();
		} catch (SQLException ee) {
			// TODO Auto-generated catch block
			ee.printStackTrace();
		
	}*/
		
	}
	public void start()
	{
		Th=new Thread(this);
		Th.start();
	}
	
	public void run()
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lb.setIcon(new ImageIcon("filenew.png"));
		validate();
	}
	}


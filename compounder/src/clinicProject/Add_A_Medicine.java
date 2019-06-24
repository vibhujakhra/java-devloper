package clinicProject;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Add_A_Medicine extends JDialog implements ActionListener{
	
	JTextField txadd;
	JButton btadd;
	JLabel lbadd;
	JPanel p1;
		public  Add_A_Medicine(){
			setSize(500,200);
			setVisible(true);
			txadd=new JTextField(30);
			btadd=new JButton("ADD");
			lbadd=new JLabel("Enter Medicine to Add");

			p1=new JPanel();
			p1.setLayout(new GridLayout(2, 2,10,10));
			p1.add(lbadd);
			p1.add(txadd);
			p1.add(new JLabel(""));
			p1.add(btadd);
			add(p1);
			btadd.addActionListener(this);
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Object src=arg0.getSource();
			if (src==btadd)
			{
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
					
					
					//Data base creating
					Statement sta=con.createStatement();
					sta.executeUpdate("create database if not exists clinicDB");	

					//excute data base 
					sta.execute("use clinicDB");
					
					
					
					PreparedStatement psta=con.prepareStatement("insert into Treatment(Medicines) values(?)");
					psta.setString(1, txadd.getText());
					
					
					psta.executeUpdate();
					
					
					con.close();
					JOptionPane.showMessageDialog(null,"Your Entry Saved");
					txadd.setText("");
				} catch (ClassNotFoundException ee) {
					// TODO Auto-generated catch block
					ee.printStackTrace();
				} catch (SQLException ee) {
					// TODO Auto-generated catch block
					ee.printStackTrace();
				}
			}

		}
}

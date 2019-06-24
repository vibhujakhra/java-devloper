package clinicProject;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class by_name extends JDialog{

	JLabel lbname,lbmobile;
	JTextField txname,txmobile;
	JButton btshow;
	JPanel p1,p2,pmain;
	JScrollPane jspmain;
	public by_name(){
		
		setVisible(true);
		setSize(700,800);
		
		lbname=new JLabel("Enter Name");
		txname=new JTextField();
		
		lbmobile=new JLabel("Enter Mobile No");
		txmobile=new JTextField();
		
		btshow=new JButton("Show");
		
		//setbounds 
		lbname.setBounds(10, 10, 150, 25);
		txname.setBounds(400, 10, 200, 30);
		lbmobile.setBounds(10, 40, 150, 25);
		txmobile.setBounds(400, 40, 200, 30);
		btshow.setBounds(250, 90, 100, 40);
		
		p1=new JPanel();
		p1.setLayout(null);
		p1.add(lbname);
		p1.add(txname);
		p1.add(lbmobile);
		p1.add(txmobile);
		p1.add(btshow);
		
		pmain=new JPanel();
		pmain.setLayout(null);
		p1.setBounds(10, 10, 700, 200);
		pmain.add(p1);
		
		pmain.setPreferredSize(new Dimension(700, 1000));
		jspmain=new JScrollPane(pmain);
		add(jspmain,BorderLayout.CENTER);
		
	}
}

package clinicProject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class By_date extends JDialog implements ActionListener {
	JDateChooser date_from, date_TO;
	JCheckBox chPaid, chFree;
	JButton btShow;
	JLabel lbchoose, lbTo, lbFrom, lbtotalFree, lbTotalpaid;
	JTable tb;
	DefaultTableModel dtb;
	int countFree = 0, countPaid = 0;
	JPanel p1, p2, p3, p4, mainPanel;
	JScrollPane jspmain, jsptable;
	String Date_to, Date_from;

	public By_date() {
		setVisible(true);
		setSize(900, 500);

		date_from = new JDateChooser();
		date_TO = new JDateChooser();

		chPaid = new JCheckBox("Paid");
		chPaid.setSelected(true);
		chFree = new JCheckBox("Free");
		chFree.setSelected(true);

		btShow = new JButton("Show");

		lbFrom = new JLabel("From");
		lbTo = new JLabel("To");
		lbchoose = new JLabel("Choose Date");
		lbTotalpaid = new JLabel("Total Paid Viewed-" + countPaid);
		lbtotalFree = new JLabel("Total Free Viewed-" + countFree);

		p1 = new JPanel();
		// p1.setBackground(Color.GRAY);
		p1.setLayout(null);
		lbchoose.setBounds(10, 20, 100, 25);
		p1.add(lbchoose);
		lbFrom.setBounds(250, 20, 30, 25);
		p1.add(lbFrom);
		date_from.setBounds(350, 20, 150, 30);
		p1.add(date_from);
		lbTo.setBounds(550, 20, 20, 25);
		p1.add(lbTo);
		date_TO.setBounds(580, 20, 150, 30);
		p1.add(date_TO);
		chPaid.setBounds(400, 80, 100, 100);
		p1.add(chPaid);
		chFree.setBounds(500, 80, 100, 100);
		p1.add(chFree);
		btShow.setBounds(650, 110, 70, 30);
		p1.add(btShow);
		lbTotalpaid.setBounds(150, 150, 200, 30);
		p1.add(lbTotalpaid);
		lbtotalFree.setBounds(150, 190, 200, 30);
		p1.add(lbtotalFree);

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		p1.setBounds(20, 20, 800, 250);
		mainPanel.add(p1);

		mainPanel.setPreferredSize(new Dimension(1000, 800));
		jspmain = new JScrollPane(mainPanel);

		add(jspmain, BorderLayout.CENTER);

		btShow.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		Object src = ae.getSource();
		if (src == btShow) {
			Object date1 = date_TO.getDate();
			Object date2 = date_from.getDate();
			System.out.println(Date_to);

			DateFormat DBDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Object Date_to = DBDateFormat.format(date1);
			Object Date_from = DBDateFormat.format(date2);
			System.out.println(Date_to);
			boolean paid = chPaid.isSelected();
			boolean free = chFree.isSelected();

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");

				// Data base creating
				Statement sta = con.createStatement();
				sta.executeUpdate("create database if not exists Patient_History");
				sta.execute("use Patient_History");
				// creating table
				sta.executeUpdate(
						"create table if not exists History(date date,name varchar(100) , age varchar(10) , mobile_No varchar(13) ,Gender varchar(7),History_of_Prev_opretion varchar(7),History_of_any_treatment varchar(7),VisionR varchar(10),VisionL varchar(10),complain_with_Duration varchar(255),History_Of_other_diseases varchar(255),IOP_R varchar(10),IOP_L varchar(10),Diagnostic varchar(255),Treatment varchar(255))");

				PreparedStatement psta = con
						.prepareStatement("select count(*) from history where (date >=? and date<=?) and type='Paid'");
				psta.setObject(1, Date_from);
				psta.setObject(2, Date_to);

				ResultSet rs = psta.executeQuery();
				int c = 0;
				while (rs.next()) {
					c = rs.getInt(1);
				}
				lbTotalpaid.setText("Total Paid Viewed : " + c);

				psta = con
						.prepareStatement("select count(*) from history where (date >=? and date<=?) and type='Free'");
				psta.setObject(1, Date_from);
				psta.setObject(2, Date_to);

				rs = psta.executeQuery();
				c = 0;
				while (rs.next()) {
					c = rs.getInt(1);
				}
				lbtotalFree.setText("Total Free Viewed : " + c);

				String str = null;

				if (paid && free)
					str = "select count(*) from history where date >=? and date<=?";
				else if (paid)
					str = "select count(*) from history where (date >=? and date<=?) and type='Paid'";

				else if (free)
					str = "select count(*) from history where (date >=? and date<=?) and type='Free'";
				psta = con.prepareStatement(str);
				psta.setObject(1, Date_from);
				psta.setObject(2, Date_to);

				rs = psta.executeQuery();
				c = 0;
				while (rs.next()) {
					c = rs.getInt(1);
				}

				if (c == 0)
					return;

				validate();
				if (paid && free)
					str = "select * from history where date >=? and date<=?";
				else if (paid)
					str = "select * from history where (date >=? and date<=?) and type='Paid'";

				else if (free)
					str = "select * from history where (date >=? and date<=?) and type='Free'";
				psta = con.prepareStatement(str);
				psta.setObject(1, Date_from);
				psta.setObject(2, Date_to);

				rs = psta.executeQuery();

				Object[][] rows = new Object[c][4];
				Object[] col = { "date", "Type", "Name", "Mobile No." };

				int i = 0;
				while (rs.next()) {

					System.out.println(rs.getObject(1));

					rows[i][0] = rs.getObject(1);
					rows[i][1] = rs.getString(2);
					rows[i][2] = rs.getString(3);
					rows[i][3] = rs.getString(4);
					i++;
				}

				System.out.println("*********************");

				dtb = new DefaultTableModel(rows, col);
				tb = new JTable(dtb);
				for (i = 0; i < c; i++) {
					tb.setRowHeight(i, 50);
					// tb.setRowHeight(i,50);
				}
				for (i = 0; i < 4; i++) {

					tb.getColumnModel().getColumn(i).setPreferredWidth(100);
					// tb.getColumnModel().getColumn(i).setPreferredWidth(100);

				}

				jsptable = new JScrollPane(tb);

				p2 = new JPanel();
				p2.setLayout(null);
				jsptable.setBounds(10, 10, 800, 1000);
				p2.add(jsptable);

				p2.setBounds(20, 300, 800, 1000);
				mainPanel.add(p2);

				validate();

				con.close();
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

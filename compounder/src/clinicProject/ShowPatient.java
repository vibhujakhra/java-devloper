package clinicProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ShowPatient extends JDialog {

	TextField txname, txage, txrigtheye, txlefteye, txIOPrigth, txIOPleft, searchbox1, searchbox2, searchbox3,
			txdygnosis, searchbox4, txmobile, txdate;
	JLabel lbname, lbage, lbvisoin, lbrighteye, lblefteye, lbcomplainWithDuration1, lbcomplainWithDuration2,
			lbhpreviousOperation1, lbhpreviousOperation2, lbhanyteratment, lbIOP, lbIOPrigth, lbIOPlrft, lbdygnosis,
			lbteratment1, lbteratment2, lbsex, lbhOfOtherDisese, lbimage1, lbimage2, lbimage3, lbimage4, lbmobile,
			lbpatientHistoryView;
	JList l1, l2, l3, l4, l5, l6;
	JRadioButton rdHPrevoisOperationYes, rdHPrevoisOperationNo, rdHtreatmentYes, rdHtreatmentNo, rdsexmale, rdsexFemale,
			rdsextransgender, rdpaid, rdfree;
	JPanel p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, pStart,
			ppatientHistoryView, panelmain;
	JScrollPane jsleftpmain, jsprigth, jspl1, jspl2, jspl3, jspl4, jspl5, jspl6;
	JButton btsave, btpatientHistroyView;
	JTable tbglassleft, tbglassRight;
	DefaultListModel demolist1, demolist2, demolist3, demolist4, demolist5, demolist6;
	JSplitPane split1, split2, split3;
	JTabbedPane tbGlass, tbmain;

	ButtonGroup btgroup1, btgroup2, btgroup3, btGroup4;
	JPanel image, pGlassType;
	JCheckBox chbifocl, chkryptok, chexecutive, chphoto_grey, chE_white, chconstnt, chdistance, chnear;
	JLabel lbtypeOfGlass, lbColuur, lbUse;

	String name, mobileNo;

	public ShowPatient() {
		setVisible(true);
		setSize(1200, 700);
		if (Desktop1_Main.truefalse) {
			name = Desktop1_Main.name;
			mobileNo = Desktop1_Main.mobile_no;
		}
		if (DemoMainProject.truefalse) {
			name = DemoMainProject.name;
			mobileNo = DemoMainProject.mobile_no;
		}

		Font f1 = new Font("brodway", Font.BOLD, 17);
		Font f2 = new Font("Arial", Font.BOLD, 16);

		txname = new TextField(20);
		txname.setSize(40, 10);
		txname.setFont(f2);

		txage = new TextField(5);
		txage.setFont(f2);
		txmobile = new TextField(11);
		txmobile.setFont(f2);
		txrigtheye = new TextField(10);
		// txrigtheye.setBackground(Color.LIGHT_GRAY);
		txrigtheye.setFont(f2);
		txlefteye = new TextField(10);
		// txlefteye.setBackground(Color.LIGHT_GRAY);
		txlefteye.setFont(f2);
		txIOPrigth = new TextField(20);
		// txIOPrigth.setBackground(Color.LIGHT_GRAY);
		txIOPrigth.setFont(f2);
		txIOPrigth = new TextField(20);
		// txIOPrigth.setBackground(Color.LIGHT_GRAY);
		txIOPrigth.setFont(f2);
		txIOPleft = new TextField(20);
		// txIOPleft.setBackground(Color.LIGHT_GRAY);
		txIOPleft.setFont(f2);
		searchbox1 = new TextField(40);
		// searchbox1.setBackground(Color.LIGHT_GRAY);
		searchbox1.setFont(f2);
		searchbox2 = new TextField(40);
		// searchbox2.setBackground(Color.LIGHT_GRAY);
		searchbox2.setFont(f2);
		searchbox3 = new TextField(40);
		// searchbox3.setBackground(Color.LIGHT_GRAY);
		searchbox3.setFont(f2);
		txdygnosis = new TextField(30);
		// txdygnosis.setBackground(Color.LIGHT_GRAY);
		txdygnosis.setFont(f2);
		searchbox4 = new TextField(40);
		// searchbox4.setBackground(Color.LIGHT_GRAY);
		searchbox4.setFont(f2);
		txdate = new TextField();
		txdate.setFont(f2);
		// txdate.setBackground(Color.lightGray);

		lbname = new JLabel("Name");
		lbname.setFont(f1);
		lbage = new JLabel("Age");
		lbage.setFont(f1);
		lbsex = new JLabel("Gender");
		lbmobile = new JLabel("Mobile Number");
		lbmobile.setFont(f1);
		lbsex.setFont(f1);
		lbvisoin = new JLabel("Vision");
		// lbvisoin.setIcon(new ImageIcon("Untitled.png"));
		lbvisoin.setFont(f1);
		lbrighteye = new JLabel("Right Eye");
		lbrighteye.setFont(f1);
		lblefteye = new JLabel("Left Eye");
		lblefteye.setFont(f1);
		lbhOfOtherDisese = new JLabel("History Of Other Diseases");
		lbhOfOtherDisese.setFont(f1);
		lbcomplainWithDuration1 = new JLabel("Complain With Duration");
		lbcomplainWithDuration1.setFont(f1);
		lbcomplainWithDuration2 = new JLabel("Complain With Duration");
		lbcomplainWithDuration2.setFont(f1);
		lbhpreviousOperation1 = new JLabel("History Of Previous Operation");
		lbhpreviousOperation1.setFont(f1);
		lbhpreviousOperation2 = new JLabel("History Of Previous Operation");
		lbhpreviousOperation2.setFont(f1);
		lbhanyteratment = new JLabel("History Of Any Treatment");
		lbhanyteratment.setFont(f1);
		lbIOP = new JLabel("Intra Ocular Pressure");
		lbIOP.setFont(f1);
		lbIOPrigth = new JLabel("Right Eye");
		lbIOPrigth.setFont(f1);
		lbIOPlrft = new JLabel("Left Eye");
		lbIOPlrft.setFont(f1);
		lbdygnosis = new JLabel("Diagnostic");
		lbdygnosis.setFont(f1);
		lbteratment1 = new JLabel("Treatment");
		lbteratment1.setFont(f1);
		lbteratment2 = new JLabel("Treatment");
		lbteratment2.setFont(f1);
		lbimage1 = new JLabel("");
		lbimage1.setIcon(new ImageIcon("Untitled.png"));
		lbimage2 = new JLabel("");
		lbimage3 = new JLabel("");
		lbimage4 = new JLabel("");
		lbpatientHistoryView = new JLabel("Patient history found click show button for view");
		lbtypeOfGlass = new JLabel("Type Of Glass-");
		lbtypeOfGlass.setFont(f1);
		lbColuur = new JLabel("Colour-");
		lbColuur.setFont(f1);
		lbUse = new JLabel("Use-");
		lbUse.setFont(f1);

		rdHPrevoisOperationYes = new JRadioButton("Yes");
		// rdHPrevoisOperationYes.setBackground(Color.GRAY);
		rdHPrevoisOperationYes.setFont(f2);
		rdHPrevoisOperationNo = new JRadioButton("No");
		// rdHPrevoisOperationNo.setBackground(Color.GRAY);
		rdHPrevoisOperationNo.setFont(f2);
		rdHtreatmentYes = new JRadioButton("Yes");
		// rdHtreatmentYes.setBackground(Color.GRAY);
		rdHtreatmentYes.setFont(f2);
		rdHtreatmentNo = new JRadioButton("No");
		// rdHtreatmentNo.setBackground(Color.GRAY);
		rdHtreatmentNo.setFont(f2);
		rdsexmale = new JRadioButton("Male");
		// rdsexmale.setBackground(Color.GRAY);
		rdsexmale.setFont(f2);
		rdsexFemale = new JRadioButton("Female");
		// rdsexFemale.setBackground(Color.GRAY);
		rdsexFemale.setFont(f2);
		rdsextransgender = new JRadioButton("Trans-Gender");
		// rdsextransgender.setBackground(Color.GRAY);
		rdsextransgender.setFont(f2);
		rdpaid = new JRadioButton("Paid");
		rdpaid.setFont(f2);
		// rdpaid.setBackground(Color.GRAY);
		rdpaid.setSelected(true);
		rdfree = new JRadioButton("Free");
		rdfree.setFont(f2);
		// rdfree.setBackground(Color.GRAY);

		btgroup1 = new ButtonGroup();
		btgroup1.add(rdsexmale);
		btgroup1.add(rdsexFemale);

		btgroup2 = new ButtonGroup();
		btgroup2.add(rdHPrevoisOperationYes);
		btgroup2.add(rdHPrevoisOperationNo);

		btgroup3 = new ButtonGroup();
		btgroup3.add(rdHtreatmentYes);
		btgroup3.add(rdHtreatmentNo);

		btGroup4 = new ButtonGroup();
		btGroup4.add(rdpaid);
		btGroup4.add(rdfree);

		chbifocl = new JCheckBox("Bifocal");
		// chbifocl.setBackground(Color.GRAY);
		chkryptok = new JCheckBox("Krypton");
		// chkryptok.setBackground(Color.GRAY);
		chexecutive = new JCheckBox("Executive");
		// chexecutive.setBackground(Color.GRAY);
		chphoto_grey = new JCheckBox("Photo Grey");
		// chphoto_grey.setBackground(Color.GRAY);
		chE_white = new JCheckBox("E.White");
		// chE_white.setBackground(Color.GRAY);
		chconstnt = new JCheckBox("Constant");
		// chconstnt.setBackground(Color.GRAY);
		chdistance = new JCheckBox("Distance");
		// chdistance.setBackground(Color.GRAY);
		chnear = new JCheckBox("Near");
		// chnear.setBackground(Color.GRAY);

		btsave = new JButton("Save & Print");
		btpatientHistroyView = new JButton("Show");

		demolist1 = new DefaultListModel<>();
		demolist2 = new DefaultListModel<>();
		demolist3 = new DefaultListModel<>();
		demolist4 = new DefaultListModel<>();
		demolist5 = new DefaultListModel<>();
		demolist6 = new DefaultListModel<>();

		l1 = new JList(demolist2);
		l1.setFont(f2);
		// l1.setBackground(Color.GRAY);
		jspl1 = new JScrollPane(l1);
		l2 = new JList(demolist1);
		l2.setFont(f2);
		// l2.setBackground(Color.GRAY);
		jspl2 = new JScrollPane(l2);
		l3 = new JList(demolist3);
		l3.setFont(f2);
		// l3.setBackground(Color.GRAY);
		jspl3 = new JScrollPane(l3);
		l4 = new JList(demolist4);
		l4.setFont(f2);
		// l4.setBackground(Color.GRAY);
		jspl4 = new JScrollPane(l4);
		l5 = new JList(demolist5);
		l5.setFont(f2);
		// l5.setBackground(Color.GRAY);
		jspl5 = new JScrollPane(l5);
		l6 = new JList(demolist6);
		l6.setFont(f2);
		// l6.setBackground(Color.GRAY);
		jspl6 = new JScrollPane(l6);

		// name age
		p1 = new JPanel();
		p1.setLayout(null);
		// p1.setBackground(Color.GRAY);
		lbname.setBounds(50, 20, 150, 25);
		txname.setBounds(400, 20, 300, 30);
		// mobile
		lbmobile.setBounds(50, 80, 150, 25);
		txmobile.setBounds(400, 80, 300, 30);
		// age
		lbage.setBounds(50, 140, 100, 25);
		txage.setBounds(400, 140, 100, 30);
		// gender
		lbsex.setBounds(50, 200, 100, 25);
		rdsexmale.setBounds(400, 160, 100, 100);
		rdsexFemale.setBounds(500, 160, 100, 100);
		p1.add(lbname);
		p1.add(txname);
		p1.add(lbmobile);
		p1.add(txmobile);
		p1.add(lbage);
		p1.add(txage);
		p1.add(lbsex);
		p1.add(rdsexmale);
		p1.add(rdsexFemale);

		p2 = new JPanel();
		p2.setLayout(null);

		// p2.setBackground(Color.GRAY);
		// vision
		lbrighteye.setBounds(250, 20, 150, 25);
		txrigtheye.setBounds(400, 20, 100, 30);
		lbvisoin.setBounds(50, 35, 150, 25);
		lblefteye.setBounds(250, 75, 150, 25);
		txlefteye.setBounds(400, 75, 100, 30);
		// complain with duration
		lbcomplainWithDuration1.setBounds(50, 150, 250, 25);
		searchbox1.setBounds(450, 150, 300, 30);
		jspl2.setBounds(450, 180, 300, 150);

		p2.add(lbrighteye);
		p2.add(txrigtheye);
		p2.add(lbvisoin);
		p2.add(lblefteye);
		p2.add(txlefteye);
		p2.add(lbcomplainWithDuration1);
		p2.add(searchbox1);
		p2.add(jspl2);

		p3 = new JPanel();
		p3.setLayout(null);
		// p3.setBackground(Color.GRAY);
		// history of other disese
		lbhOfOtherDisese.setBounds(50, 20, 250, 25);
		searchbox2.setBounds(450, 20, 300, 30);
		jspl3.setBounds(450, 50, 300, 150);
		// history of privious operation
		lbhpreviousOperation1.setBounds(50, 230, 300, 25);
		rdHPrevoisOperationYes.setBounds(400, 205, 100, 100);
		rdHPrevoisOperationNo.setBounds(500, 205, 100, 100);

		// history of any treatmen
		lbhanyteratment.setBounds(50, 350, 250, 25);
		rdHtreatmentYes.setBounds(400, 330, 100, 100);
		rdHtreatmentNo.setBounds(500, 330, 100, 100);
		p3.add(lbhOfOtherDisese);
		p3.add(searchbox2);
		p3.add(jspl3);
		p3.add(lbhpreviousOperation1);
		p3.add(rdHPrevoisOperationYes);
		p3.add(rdHPrevoisOperationNo);
		p3.add(lbhanyteratment);
		p3.add(rdHtreatmentYes);
		p3.add(rdHtreatmentNo);

		p4 = new JPanel();
		p4.setLayout(null);
		// p4.setBackground(Color.GRAY);
		// IOP
		lbIOPrigth.setBounds(400, 20, 150, 25);
		txIOPrigth.setBounds(550, 20, 100, 30);
		lbIOP.setBounds(50, 35, 250, 25);
		lbIOPlrft.setBounds(400, 75, 150, 25);
		txIOPleft.setBounds(550, 75, 100, 30);

		// dygnosis
		lbdygnosis.setBounds(50, 150, 150, 25);
		txdygnosis.setBounds(400, 150, 300, 25);

		p4.add(lbIOPrigth);
		p4.add(txIOPrigth);
		p4.add(lbIOP);
		p4.add(lbIOPlrft);
		p4.add(txIOPleft);
		p4.add(lbdygnosis);
		p4.add(txdygnosis);

		p5 = new JPanel();
		p5.setLayout(null);
		// p5.setBackground(Color.GRAY);
		// treatment
		lbteratment1.setBounds(50, 20, 150, 25);
		searchbox3.setBounds(400, 20, 300, 25);
		jspl5.setBounds(400, 45, 300, 150);
		rdpaid.setBounds(400, 250, 100, 100);
		rdfree.setBounds(500, 250, 100, 100);
		// save and print
		btsave.setBounds(400, 450, 150, 50);

		p5.add(lbteratment1);
		p5.add(searchbox3);
		p5.add(jspl5);
		p5.add(rdpaid);
		p5.add(rdfree);
		p5.add(btsave);

		panelmain = new JPanel();
		// panelmain.setBackground(Color.GRAY);
		panelmain.setLayout(null);
		p1.setBounds(50, 20, 700, 260);
		panelmain.add(p1);
		p2.setBounds(50, 280, 700, 300);
		panelmain.add(p2);
		p3.setBounds(50, 590, 700, 460);
		panelmain.add(p3);
		p4.setBounds(50, 1060, 700, 200);
		panelmain.add(p4);
		p5.setBounds(50, 1300, 700, 550);
		panelmain.add(p5);

		panelmain.setPreferredSize(new Dimension(1800, 2000));

		// glass table
		DefaultTableModel tbmodel = new DefaultTableModel();
		tbmodel.setColumnIdentifiers(new Object[] { "Sph", "Cyl", "Axis" });
		for (int count = 0; count < 2; count++) {
			tbmodel.insertRow(count, new Object[] { "", "", "" });
		}

		tbglassleft = new JTable(tbmodel);
		tbglassRight = new JTable(tbmodel);
		tbglassleft.setEnabled(false);
		tbglassRight.setEnabled(false);
		for (int i = 0; i < 3; i++) {
			tbglassleft.setRowHeight(i, 50);
			tbglassRight.setRowHeight(i, 50);

			tbglassRight.getColumnModel().getColumn(i).setPreferredWidth(100);
			tbglassleft.getColumnModel().getColumn(i).setPreferredWidth(100);

		}

		p19 = new JPanel();
		// p19.setBackground(Color.GRAY);
		JPanel pglassTable = new JPanel();
		// pglassTable.setBackground(Color.GRAY);
		JPanel pHeader, pTable, Ptype;

		JLabel lbr, lbl;
		lbl = new JLabel("Left Eye");
		lbl.setBounds(230, 50, 100, 25);
		lbl.setFont(f1);
		lbr = new JLabel("Right Eye");
		lbr.setBounds(680, 50, 100, 25);
		lbr.setFont(f1);

		pHeader = new JPanel();
		// pHeader.setBackground(Color.GRAY);
		pHeader.setLayout(null);
		pHeader.add(lbl);
		pHeader.add(lbr);

		JScrollPane jspRight = new JScrollPane(tbglassRight);
		pglassTable.add(jspRight);
		jspRight.setBounds(50, 10, 450, 150);
		JScrollPane jspLeft = new JScrollPane(tbglassleft);
		pglassTable.add(jspLeft);
		jspLeft.setBounds(500, 10, 450, 150);

		lbtypeOfGlass.setBounds(10, 10, 200, 25);
		chbifocl.setBounds(220, 10, 100, 25);
		chkryptok.setBounds(320, 10, 100, 25);
		chexecutive.setBounds(420, 10, 100, 25);
		lbColuur.setBounds(10, 40, 200, 25);
		chphoto_grey.setBounds(220, 40, 100, 25);
		chE_white.setBounds(320, 40, 100, 25);
		lbUse.setBounds(10, 70, 200, 25);
		chconstnt.setBounds(220, 70, 100, 25);
		chdistance.setBounds(320, 70, 100, 25);
		chnear.setBounds(420, 70, 100, 25);

		Ptype = new JPanel();
		// Ptype.setBackground(Color.GRAY);
		Ptype.setLayout(null);
		Ptype.add(lbtypeOfGlass);
		Ptype.add(chbifocl);
		Ptype.add(chkryptok);
		Ptype.add(chexecutive);
		Ptype.add(lbColuur);
		Ptype.add(chphoto_grey);
		Ptype.add(chE_white);
		Ptype.add(lbUse);
		Ptype.add(chconstnt);
		Ptype.add(chdistance);
		Ptype.add(chnear);

		pTable = new JPanel();
		pTable.setBackground(Color.GRAY);
		pTable.setLayout(null);
		pTable.add(jspLeft);
		pTable.add(jspRight);

		pglassTable.setLayout(null);
		pHeader.setBounds(250, 50, 1000, 100);
		pTable.setBounds(250, 160, 1000, 200);
		Ptype.setBounds(250, 370, 1000, 110);
		pglassTable.add(pHeader);
		pglassTable.add(pTable);
		pglassTable.add(Ptype);

		pglassTable.setPreferredSize(new Dimension(1500, 700));

		JScrollPane jsp1 = new JScrollPane(pglassTable);

		p19.add(jsp1);

		JPanel ppatientHistoryView = new JPanel();
		ppatientHistoryView.setVisible(true);
		// ppatientHistoryView.setBackground(Color.GRAY);

		ppatientHistoryView.add(new Label("Date-"));

		ppatientHistoryView.add(txdate);

		p18 = new JPanel();
		// p18.setBackground(Color.GRAY);
		p18.setLayout(new GridLayout(10, 1));
		p18.add(ppatientHistoryView);
		p18.add(new JLabel(""));
		p18.add(lbcomplainWithDuration2);
		p18.add(l1);
		p18.add(lbhpreviousOperation2);
		p18.add(l4);
		p18.add(new JLabel(""));
		p18.add(lbteratment2);
		p18.add(l6);

		jsprigth = new JScrollPane(p18);
		jsleftpmain = new JScrollPane(panelmain);
		// jsleftpmain.setBackground(Color.GRAY);
		// setBackground(Color.GRAY);

		split1 = new JSplitPane(split1.HORIZONTAL_SPLIT);
		split1.setResizeWeight(0.60);
		split1.add(jsleftpmain);
		split1.add(jsprigth);

		tbGlass = new JTabbedPane();
		tbGlass.addTab("TREATMENT", split1);
		tbGlass.add("PRESCRIPTION FOR GLASS", p19);

		add(tbGlass);
		setFont(f1);
		chbifocl.setEnabled(false);
		chkryptok.setEnabled(false);
		chexecutive.setEnabled(false);
		chphoto_grey.setEnabled(false);
		chE_white.setEnabled(false);
		chconstnt.setEnabled(false);
		chdistance.setEnabled(false);
		chnear.setEnabled(false);

		btsave.setVisible(false);
		txname.setEnabled(false);
		txage.setEnabled(false);
		txrigtheye.setEnabled(false);
		txlefteye.setEnabled(false);
		txIOPrigth.setEnabled(false);
		txIOPleft.setEnabled(false);
		searchbox1.setEnabled(false);
		searchbox2.setEnabled(false);
		searchbox3.setEnabled(false);
		txdygnosis.setEnabled(false);
		searchbox4.setEnabled(false);
		txmobile.setEnabled(false);
		txdate.setEnabled(false);

		l1.setEnabled(false);
		l2.setEnabled(false);
		l3.setEnabled(false);
		l4.setEnabled(false);
		l5.setEnabled(false);
		l6.setEnabled(false);

		rdHPrevoisOperationYes.setEnabled(false);
		rdHPrevoisOperationNo.setEnabled(false);
		rdHtreatmentYes.setEnabled(false);
		rdHtreatmentNo.setEnabled(false);
		rdsexmale.setEnabled(false);
		rdsexFemale.setEnabled(false);
		rdsextransgender.setEnabled(false);
		rdpaid.setEnabled(false);
		rdfree.setEnabled(false);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");

			// Data base creating
			Statement sta = con.createStatement();
			sta.executeUpdate("create database if not exists Patient_History");
			sta.execute("use Patient_History");
			// creating table
			// sta.executeUpdate(
			// "create table if not exists History(date varchar(11),name varchar(100) , age
			// varchar(10) , mobile_No varchar(13) ,Gender
			// varchar(7),History_of_Prev_opretion varchar(7),History_of_any_treatment
			// varchar(7),VisionR varchar(10),VisionL varchar(10),complain_with_Duration
			// varchar(255),History_Of_other_diseases varchar(255),IOP_R varchar(10),IOP_L
			// varchar(10),Diagnostic varchar(255),Treatment varchar(255))");
			PreparedStatement psta = con.prepareStatement("select * from history where name=? and mobile_No=?");
			psta.setString(1, name + "");
			psta.setString(2, mobileNo + "");
			ResultSet rs = psta.executeQuery();

			while (rs.next()) {
				txdate.setText(rs.getObject("date").toString());
				txname.setText(rs.getString("name"));
				txage.setText(rs.getString("age"));
				{
					if (rs.getString("Gender").equals("male")) {
						rdsexmale.setSelected(true);
					} else {
						rdsexFemale.setSelected(true);
					}
				}
				{
					if (rs.getString("History_of_Prev_opretion").equals("yes")) {
						rdHPrevoisOperationYes.setSelected(true);
					} else {
						rdHPrevoisOperationNo.setSelected(true);
					}
				}
				{
					if (rs.getString("History_of_any_treatment").equals("yes")) {
						rdHtreatmentYes.setSelected(true);
					} else {
						rdHtreatmentNo.setSelected(true);
					}
				}
				txrigtheye.setText(rs.getString("VisionR"));

				{
					String[] parts = rs.getString("complain_with_Duration").split(",");
					for (int i = 0; i < parts.length; i++) {
						demolist2.addElement(parts[i]);
					}
				}
				{

					String[] parts = rs.getString("History_Of_other_diseases").split(",");
					for (int i = 0; i < parts.length; i++) {
						demolist4.addElement(parts[i]);
					}
				}
				{

					String[] parts = rs.getString("Treatment").split(",");
					for (int i = 0; i < parts.length; i++) {
						demolist6.addElement(parts[i]);
					}
				}
				txlefteye.setText(rs.getString("VisionL"));
				txIOPrigth.setText(rs.getString("IOP_R"));
				txIOPleft.setText(rs.getString("IOP_L"));
				txdygnosis.setText(rs.getString("Diagnostic"));
				tbglassRight.getModel().setValueAt(rs.getString("SphR"), 0, 0);
				tbglassRight.getModel().setValueAt(rs.getString("CylR"), 0, 1);
				tbglassRight.getModel().setValueAt(rs.getString("AxisR"), 0, 2);
				tbglassRight.getModel().setValueAt(rs.getString("SphL"), 0, 0);
				tbglassRight.getModel().setValueAt(rs.getString("CylL"), 0, 1);
				tbglassRight.getModel().setValueAt(rs.getString("AxisL"), 0, 2);
				txmobile.setText(rs.getString("mobile_No"));
				{
					if (rs.getString("Type").equals("Paid")) {
						rdpaid.setSelected(true);
					} else {
						rdfree.setSelected(true);
					}
				}
				// Type,Bifocal,Krypotok,Excutive,Photo_grey,E_white,Constant,Distance,Near
				{
					if (rs.getString("Bifocal").equals("Yes")) {
						chbifocl.setSelected(true);
					} else {
						chbifocl.setSelected(false);
					}
				}
				{
					if (rs.getString("Krypotok").equals("Yes")) {
						chkryptok.setSelected(true);
					} else {
						chkryptok.setSelected(false);
					}
				}
				{
					if (rs.getString("Excutive").equals("Yes")) {
						chexecutive.setSelected(true);
					} else {
						chexecutive.setSelected(false);
					}
				}
				{
					if (rs.getString("Photo_grey").equals("Yes")) {
						chphoto_grey.setSelected(true);
					} else {
						chphoto_grey.setSelected(false);
					}
				}
				{
					if (rs.getString("E_white").equals("Yes")) {
						chE_white.setSelected(true);
					} else {
						chE_white.setSelected(false);
					}
				}
				{
					if (rs.getString("Constant").equals("Yes")) {
						chconstnt.setSelected(true);
					} else {
						chconstnt.setSelected(false);
					}
				}
				{
					if (rs.getString("Distance").equals("Yes")) {
						chdistance.setSelected(true);
					} else {
						chdistance.setSelected(false);
					}
				}
				{
					if (rs.getString("Near").equals("Yes")) {
						chnear.setSelected(true);
					} else {
						chnear.setSelected(false);
					}
				}

			}

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

package clinicProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;

public class Desktop1_Main extends JApplet implements Runnable, ActionListener, ItemListener, KeyListener,
		FocusListener, TextListener, ListSelectionListener, ListDataListener, MenuListener, MouseListener {

	TextField txname, txage, txrigtheye, txlefteye, txIOPrigth, txIOPleft, searchbox1, searchbox2, searchbox3,
			txdygnosis, searchbox4, txmobile;
	JLabel lbname, lbage, lbvisoin, lbrighteye, lblefteye, lbcomplainWithDuration1, lbcomplainWithDuration2,
			lbhpreviousOperation1, lbhpreviousOperation2, lbhanyteratment, lbIOP, lbIOPrigth, lbIOPlrft, lbdygnosis,
			lbteratment1, lbteratment2, lbsex, lbhOfOtherDisese, lbimage1, lbimage2, lbimage3, lbimage4, lbmobile,
			lbpatientHistoryView;
	JList l1, l2, l3, l4, l5, l6;
	JRadioButton rdHPrevoisOperationYes, rdHPrevoisOperationNo, rdHtreatmentYes, rdHtreatmentNo, rdsexmale, rdsexFemale,
			rdsextransgender, rdpaid, rdfree, rdtemp1, rdtemp2, rdtemp3, rdtemp4;
	JPanel p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, pStart,
			ppatientHistoryView, panelmain, panelToPrint;
	JScrollPane jsleftpmain, jsprigth, jspl1, jspl2, jspl3, jspl4, jspl5, jspl6;
	JMenuBar mb;
	JMenu mfile, medit, mPatientSearch, mpatient;
	JMenuItem minew, mibyName, misave, miexit, mibydate, miupdte_remove, miadd;
	JButton btsubmit, btpatientHistroyView;
	JTable tbglassleft, tbglassRight;
	DefaultListModel demolist1, demolist2, demolist3, demolist4, demolist5, demolist6;
	JSplitPane split1, split2, split3;
	JTabbedPane tbGlass, tbmain;
	JDialog d;
	Thread th;
	ButtonGroup btgroup1, btgroup2, btgroup3, btGroup4;
	JPanel image, pGlassType;
	JCheckBox chbifocl, chkryptok, chexecutive, chphoto_grey, chE_white, chconstnt, chdistance, chnear;
	JLabel lbtypeOfGlass, lbColuur, lbUse;
	JPanel idpanel;
	PrinterJob pj = PrinterJob.getPrinterJob();
	DefaultTableModel tbmodel = new DefaultTableModel();

	int Unique_id = 0;
	public static boolean truefalse = false;
	public static String name, mobile_no;
	JPanel treatment, glass;

	public boolean cheakItemToAdd(String item) {

		boolean cheak = true;
		for (int i = 0; i < l1.getModel().getSize(); i++) {
			if (item.equals(l1.getModel().getElementAt(i).toString())) {
				cheak = false;
			}

		}
		return cheak;

	}

	public boolean DateCheak(Object date) {
		if (date.toString().equals(LocalDate.now().toString())) {
			return true;
		} else {
			return false;
		}
	}

	public void Eraseall() {
		// clearing text boxes
		txname.setText("");
		txage.setText("");
		txrigtheye.setText("");
		txlefteye.setText("");
		searchbox1.setText("");
		searchbox2.setText("");
		txdygnosis.setText("");
		txmobile.setText("");

		// clearing list
		l1.removeAll();
		demolist1.removeAllElements();
		l2.removeAll();
		demolist2.removeAllElements();
		l3.removeAll();
		demolist3.removeAllElements();
		l4.removeAll();
		demolist4.removeAllElements();

		// deselecting radio_buttons
		rdtemp1.setSelected(true);
		rdtemp2.setSelected(true);
		rdtemp3.setSelected(true);

		// clearing glass table
		int rowCount = tbmodel.getRowCount();
		// Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			tbmodel.removeRow(i);
		}
		tbmodel.setColumnIdentifiers(new Object[] { "Sph", "Cyl", "Axis" });
		for (int count = 0; count < 2; count++) {
			tbmodel.insertRow(count, new Object[] { "", "", "" });
		}
		for (int i = 0; i < 3; i++) {
			tbglassleft.setRowHeight(i, 50);
			tbglassRight.setRowHeight(i, 50);

			tbglassRight.getColumnModel().getColumn(i).setPreferredWidth(100);
			tbglassleft.getColumnModel().getColumn(i).setPreferredWidth(100);

		}
		tbglassleft.setModel(tbmodel);
		tbglassRight.setModel(tbmodel);

		// clearing cheackboxes
		chbifocl.setSelected(false);
		chkryptok.setSelected(false);
		chexecutive.setSelected(false);
		chphoto_grey.setSelected(false);
		chE_white.setSelected(false);
		chconstnt.setSelected(false);
		chdistance.setSelected(false);
		chnear.setSelected(false);
		ppatientHistoryView.setVisible(false);
		validate();
		repaint();

	}

	public boolean checkHistory(String name, String mobile_no) {
		boolean found = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");

			// Data base creating
			Statement sta = con.createStatement();
			sta.executeUpdate("create database if not exists Patient_History");
			sta.execute("use Patient_History");

			// creating table
			sta.executeUpdate(
					"create table if not exists History(date date,Type varchar(100),name varchar(100) , age varchar(10) , mobile_No varchar(13) ,Gender varchar(7),History_of_Prev_opretion varchar(7),History_of_any_treatment varchar(7),VisionR varchar(10),VisionL varchar(10),complain_with_Duration varchar(255),History_Of_other_diseases varchar(255),IOP_R varchar(10),IOP_L varchar(10),Diagnostic varchar(255),Treatment varchar(255),SphR varchar(50),CylR varchar(50),AxisR varchar(50),SphL varchar(50),CylL varchar(50),AxisL varchar(50),Bifocal varchar(100),Krypotok varchar(100),Excutive varchar(100),Photo_grey varchar(100),E_white varchar(100),Constant varchar(100),Distance varchar(100),Near varchar(100))");
			PreparedStatement psta = con.prepareStatement("select * from history where name=? and mobile_No=?");
			psta.setString(1, name + "");
			psta.setString(2, mobile_no + "");
			ResultSet rs = psta.executeQuery();

			while (rs.next()) {

				found = true;

			}
			if (!found) {
				found = false;
			}

		} catch (ClassNotFoundException ee) {
			// TODO Auto-generated catch block
			ee.printStackTrace();
		} catch (SQLException ee) {
			// TODO Auto-generated catch block
			ee.printStackTrace();

		}
		return found;
	}

	public void init() {

		setSize(1500, 800);
		setVisible(true);
		setLocation(500, 500);

		th = new Thread(this);
		th.start();
		try {
			th.sleep(3000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		remove(image);
		validate();
		repaint();

		Font f1 = new Font("brodway", Font.BOLD, 17);
		Font f2 = new Font("Arial", Font.BOLD, 16);

		txname = new TextField(20);
		txname.setSize(40, 10);
		txname.setFont(f2);
		// txname.setBackground(Color.LIGHT_GRAY);
		txname.setPreferredSize(new Dimension(40, 10));
		txname.setMaximumSize(new Dimension(40, 10));
		txage = new TextField(5);
		// txage.setBackground(Color.LIGHT_GRAY);
		txage.setFont(f2);
		txmobile = new TextField(11);
		// txmobile.setBackground(Color.LIGHT_GRAY);
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
		rdtemp1 = new JRadioButton("");
		rdtemp2 = new JRadioButton("");
		rdtemp3 = new JRadioButton("");
		rdtemp4 = new JRadioButton("");

		btgroup1 = new ButtonGroup();
		btgroup1.add(rdsexmale);
		btgroup1.add(rdsexFemale);
		btgroup1.add(rdtemp1);

		btgroup2 = new ButtonGroup();
		btgroup2.add(rdHPrevoisOperationYes);
		btgroup2.add(rdHPrevoisOperationNo);
		btgroup2.add(rdtemp2);

		btgroup3 = new ButtonGroup();
		btgroup3.add(rdHtreatmentYes);
		btgroup3.add(rdHtreatmentNo);
		btgroup3.add(rdtemp3);

		btGroup4 = new ButtonGroup();
		btGroup4.add(rdpaid);
		btGroup4.add(rdfree);
		// btgroup2.add(rdtemp2);

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

		btsubmit = new JButton("Submit");
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
		l6.setEnabled(false);
		l6.setFont(f2);
		// l6.setBackground(Color.GRAY);
		jspl6 = new JScrollPane(l6);

		minew = new JMenuItem("New");
		minew.setIcon(new ImageIcon("filenew.png"));
		mibyName = new JMenuItem("By Name");
		misave = new JMenuItem("Save");
		misave.setIcon(new ImageIcon("filesave.png"));
		miexit = new JMenuItem("Exit");
		mibydate = new JMenuItem("By Date");

		miupdte_remove = new JMenuItem("Update or Remove");
		miadd = new JMenuItem("Add a Medicine");

		mPatientSearch = new JMenu("search");
		mPatientSearch.add(mibyName);
		mPatientSearch.add(mibydate);

		mfile = new JMenu("File");
		mfile.add(minew);
		mfile.addSeparator();
		mfile.add(misave);
		mfile.addSeparator();
		mfile.add(miexit);
		mfile.addMenuListener(this);

		medit = new JMenu("Edit");
		medit.add(miupdte_remove);
		medit.add(miadd);
		medit.addMenuListener(this);

		mpatient = new JMenu("Patient");

		mb = new JMenuBar();
		mb.add(mfile);
		mb.add(medit);
		mb.add(mPatientSearch);

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
		btsubmit.setBounds(350, 450, 150, 50);
		p3.add(lbhOfOtherDisese);
		p3.add(searchbox2);
		p3.add(jspl3);
		p3.add(lbhpreviousOperation1);
		p3.add(rdHPrevoisOperationYes);
		p3.add(rdHPrevoisOperationNo);
		p3.add(lbhanyteratment);
		p3.add(rdHtreatmentYes);
		p3.add(rdHtreatmentNo);
		p3.add(btsubmit);

		panelmain = new JPanel();
		// panelmain.setBackground(Color.GRAY);
		panelmain.setLayout(null);
		p1.setBounds(50, 20, 700, 260);
		panelmain.add(p1);
		p2.setBounds(50, 280, 700, 300);
		panelmain.add(p2);
		p3.setBounds(50, 600, 700, 550);
		panelmain.add(p3);

		panelmain.setPreferredSize(new Dimension(1800, 1250));

		// glass table
		DefaultTableModel tbmodel = new DefaultTableModel();
		tbmodel.setColumnIdentifiers(new Object[] { "Sph", "Cyl", "Axis" });
		for (int count = 0; count < 2; count++) {
			tbmodel.insertRow(count, new Object[] { "", "", "" });
		}

		tbglassleft = new JTable(tbmodel);
		tbglassleft.setEnabled(false);
		tbglassRight = new JTable(tbmodel);
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
		chbifocl.setEnabled(false);
		chkryptok.setBounds(320, 10, 100, 25);
		chkryptok.setEnabled(false);
		chexecutive.setBounds(420, 10, 100, 25);
		chexecutive.setEnabled(false);
		lbColuur.setBounds(10, 40, 200, 25);
		chphoto_grey.setBounds(220, 40, 100, 25);
		chphoto_grey.setEnabled(false);
		chE_white.setBounds(320, 40, 100, 25);
		chE_white.setEnabled(false);
		lbUse.setBounds(10, 70, 200, 25);
		chconstnt.setBounds(220, 70, 100, 25);
		chconstnt.setEnabled(false);
		chdistance.setBounds(320, 70, 100, 25);
		chdistance.setEnabled(false);
		chnear.setBounds(420, 70, 100, 25);
		chnear.setEnabled(false);

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
		pglassTable.setEnabled(false);
		pHeader.setBounds(250, 50, 1000, 100);
		pTable.setBounds(250, 160, 1000, 200);
		Ptype.setBounds(250, 370, 1000, 110);
		pglassTable.add(pHeader);
		pglassTable.add(pTable);
		pglassTable.add(Ptype);

		pglassTable.setPreferredSize(new Dimension(1500, 700));

		JScrollPane jsp1 = new JScrollPane(pglassTable);

		p19.add(jsp1);

		ppatientHistoryView = new JPanel();
		ppatientHistoryView.setVisible(false);
		ppatientHistoryView.setBackground(Color.GREEN);
		ppatientHistoryView.add(lbpatientHistoryView);
		ppatientHistoryView.add(btpatientHistroyView);

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

		setJMenuBar(mb);

		add(tbGlass);
		setFont(f1);

		searchbox1.addTextListener(this);
		searchbox1.addKeyListener(this);
		l2.addKeyListener(this);
		searchbox2.addTextListener(this);
		searchbox2.addKeyListener(this);
		l3.addKeyListener(this);
		searchbox3.addTextListener(this);
		searchbox3.addKeyListener(this);
		l5.addKeyListener(this);
		l2.addMouseListener(this);
		l3.addMouseListener(this);

		minew.addActionListener(this);
		misave.addActionListener(this);
		miexit.addActionListener(this);
		miupdte_remove.addActionListener(this);
		miadd.addActionListener(this);
		btsubmit.addActionListener(this);
		btpatientHistroyView.addActionListener(this);
		txmobile.addFocusListener(this);
		mibyName.addActionListener(this);
		mibydate.addActionListener(this);
		minew.addActionListener(this);
		validate();

	}

	public void run() {
		lbimage1 = new JLabel("");
		lbimage1.setIcon(new ImageIcon("eye.jpg"));
		image = new JPanel();
		image.setPreferredSize(new Dimension(1500, 500));
		image.setLayout(new BorderLayout());
		image.add(lbimage1, BorderLayout.CENTER);
		add(image, BorderLayout.CENTER);

		validate();
	}

	@Override
	public void menuCanceled(MenuEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void menuDeselected(MenuEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void menuSelected(MenuEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contentsChanged(ListDataEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void intervalAdded(ListDataEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void intervalRemoved(ListDataEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void textValueChanged(TextEvent e) {
		// TODO Auto-generated method stub

		if (demolist1 != null) {
			demolist1.removeAllElements();
		}
		Object src = e.getSource();
		if (src == searchbox1) {
			if (searchbox1.getText().equals("") == false) {

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");

					// Data base creating
					Statement sta = con.createStatement();
					sta.executeUpdate("create database if not exists clinicDB");
					sta.execute("use clinicDB");

					String getString = searchbox1.getText();

					ResultSet rs = sta
							.executeQuery("select Complaints from Complaint_with_Duration where Complaints like '"
									+ getString + "%'");

					while (rs.next()) {
						demolist1.addElement(rs.getString("Complaints"));

					}

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

		if (demolist3 != null) {
			demolist3.removeAllElements();
		}
		if (src == searchbox2) {
			if (searchbox2.getText().equals("") == false) {

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");

					// Data base creating
					Statement sta = con.createStatement();
					sta.executeUpdate("create database if not exists clinicDB");
					sta.execute("use clinicDB");

					String getString2 = searchbox2.getText();

					ResultSet rs = sta.executeQuery(
							"select Diseses_Name from diseases where Diseses_Name like '" + getString2 + "%'");

					while (rs.next()) {
						demolist3.addElement(rs.getString("Diseses_Name"));

					}

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

		if (demolist5 != null) {
			demolist5.removeAllElements();
		}
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub

		Object src = e.getSource();
		if (src == txmobile) {
			this.truefalse = true;
			this.name = txname.getText().toString();
			this.mobile_no = txmobile.getText().toString();

			if (checkHistory(name, mobile_no)) {
				ppatientHistoryView.setVisible(true);
			} else {
				ppatientHistoryView.setVisible(false);
			}

		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		Object src = e.getSource();
		if (src == l2) {
			if (e.getKeyCode() == 10) {

				if (cheakItemToAdd(l2.getSelectedValue().toString())) {
					demolist2.addElement(l2.getSelectedValue().toString());

				}
				searchbox1.setText("");
				searchbox1.requestFocus();

			}
		}
		if (src == l3) {
			if (e.getKeyCode() == 10) {

				if (cheakItemToAdd(l3.getSelectedValue().toString())) {
					demolist4.addElement(l3.getSelectedValue().toString());
				}
				searchbox2.setText("");
				searchbox2.requestFocus();

			}
		}

		if (src == searchbox1) {
			// JOptionPane.showMessageDialog(null, e.getKeyChar());
			if (e.getKeyCode() == 40) {
				l2.requestFocus();
				l2.setSelectedIndex(0);

			}
		}
		if (src == searchbox2) {
			if (e.getKeyCode() == 40) {
				l3.requestFocus();
				l3.setSelectedIndex(0);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		Object src = e.getSource();
		if (src == btpatientHistroyView) {
			d = new ShowPatient();
		}
		if (src == btsubmit) {

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");

				// Data base creating
				Statement sta = con.createStatement();
				sta.executeUpdate("create database if not exists clinicdb");
				sta.execute("use clinicdb");

				ResultSet rs = sta.executeQuery("select date from id_System");
				Object date = null;
				while (rs.next()) {
					date = rs.getObject("date");
				}

				if (DateCheak(date)) {
					// creating table

					sta.executeUpdate(
							"create table if not exists id_System(Unique_Id int auto_increment,Date date,name varchar(100) , age varchar(10) , mobile_No varchar(13) ,Gender varchar(7),History_of_Prev_opretion varchar(7),History_of_any_treatment varchar(7),VisionR varchar(10),VisionL varchar(10),complain_with_Duration varchar(255),History_Of_other_diseases varchar(255),primary key(Unique_Id))");

					String l1_items = "", l4_items = "";

					for (int i = 0; i < l1.getModel().getSize(); i++) {
						l1_items += l1.getModel().getElementAt(i).toString();
						if (i != l1.getModel().getSize() - 1)
							l1_items += ",";

					}

					for (int i = 0; i < l4.getModel().getSize(); i++) {
						l4_items += l4.getModel().getElementAt(i).toString();
						if (i != l4.getModel().getSize() - 1)
							l4_items += ",";
					}

					PreparedStatement psta = con.prepareStatement(
							"insert into id_System(name, age, mobile_No,Gender,History_of_Prev_opretion,History_of_any_treatment,VisionR,VisionL,complain_with_Duration,History_Of_other_diseases,Date) values(?,?,?,?,?,?,?,?,?,?,?)");

					psta.setString(1, txname.getText());
					psta.setString(2, txage.getText());
					psta.setString(3, txmobile.getText());

					{
						if (rdsexmale.isSelected()) {
							psta.setString(4, "male");
						} else {
							psta.setString(4, "female");
						}
					}

					{
						if (rdHPrevoisOperationYes.isSelected()) {
							psta.setString(5, "yes");
						} else {
							psta.setString(5, "no");
						}
					}

					{
						if (rdHtreatmentYes.isSelected()) {
							psta.setString(6, "yes");
						} else {
							psta.setString(6, "no");
						}
					}

					psta.setString(7, txrigtheye.getText());
					psta.setString(8, txlefteye.getText());
					psta.setString(9, l1_items);
					psta.setString(10, l4_items);
					psta.setObject(11, LocalDate.now());

					psta.executeUpdate();
					rs = sta.executeQuery("select count(*) from id_System");
					int c = 0;
					while (rs.next()) {
						c = rs.getInt(1);
					}
					Unique_id = c;

					JOptionPane.showMessageDialog(null, "Unique ID=>  " + Unique_id + "\n please Give it to Patient");
				} else {
					sta.executeUpdate("DROP TABLE id_System");
					sta.executeUpdate(
							"create table if not exists id_System(Unique_Id int auto_increment,Date date,name varchar(100) , age varchar(10) , mobile_No varchar(13) ,Gender varchar(7),History_of_Prev_opretion varchar(7),History_of_any_treatment varchar(7),VisionR varchar(10),VisionL varchar(10),complain_with_Duration varchar(255),History_Of_other_diseases varchar(255),primary key(Unique_Id))");

					String l1_items = "", l4_items = "";

					for (int i = 0; i < l1.getModel().getSize(); i++) {
						l1_items += l1.getModel().getElementAt(i).toString();
						if (i != l1.getModel().getSize() - 1)
							l1_items += ",";

					}

					for (int i = 0; i < l4.getModel().getSize(); i++) {
						l4_items += l4.getModel().getElementAt(i).toString();
						if (i != l4.getModel().getSize() - 1)
							l4_items += ",";
					}

					PreparedStatement psta = con.prepareStatement(
							"insert into id_System(name, age, mobile_No,Gender,History_of_Prev_opretion,History_of_any_treatment,VisionR,VisionL,complain_with_Duration,History_Of_other_diseases,Date) values(?,?,?,?,?,?,?,?,?,?,?)");

					psta.setString(1, txname.getText());
					psta.setString(2, txage.getText());
					psta.setString(3, txmobile.getText());

					{
						if (rdsexmale.isSelected()) {
							psta.setString(4, "male");
						}

						else {
							psta.setString(4, "female");
						}
					}

					{
						if (rdHPrevoisOperationYes.isSelected()) {
							psta.setString(5, "yes");
						} else {
							psta.setString(5, "no");
						}
					}

					{
						if (rdHtreatmentYes.isSelected()) {
							psta.setString(6, "yes");
						} else {
							psta.setString(6, "no");
						}
					}

					psta.setString(7, txrigtheye.getText());
					psta.setString(8, txlefteye.getText());
					psta.setString(9, l1_items);
					psta.setString(10, l4_items);
					psta.setObject(11, LocalDate.now());

					psta.executeUpdate();
					rs = sta.executeQuery("select count(*) from id_System");
					int c = 0;
					while (rs.next()) {
						c = rs.getInt(1);
					}
					Unique_id = c;

					JOptionPane.showMessageDialog(null, "Unique ID=>  " + Unique_id + "\n please Give it to Patient");
				}
				con.close();

			} catch (ClassNotFoundException ee) {
				// TODO Auto-generated catch block
				ee.printStackTrace();
			} catch (SQLException ee) {
				// TODO Auto-generated catch block
				ee.printStackTrace();

			}
			Eraseall();

		}
		if (src == minew) {

			Eraseall();

		}
		if (src == btpatientHistroyView) {

			d = new ShowPatient();
		}
		if (src == mibydate) {
			d = new By_date();
		}
		if (src == mibyName) {

			d = new by_name();
		}
		if (src == minew) {
			Eraseall();
			validate();
			repaint();
		}
		if (src == miadd) {
			d = new Add_A_Medicine();
		}
		if (src == miupdte_remove) {
			d = new Update_remove();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (src == l2) {
			int itemindex = l2.getSelectedIndex();
			if (cheakItemToAdd(l2.getModel().getElementAt(itemindex).toString())) {
				demolist2.addElement(l2.getModel().getElementAt(itemindex).toString());
			}
			searchbox1.setText("");
			searchbox1.requestFocus();
		}
		if (src == l3) {
			int itemindex2 = l3.getSelectedIndex();
			if (cheakItemToAdd(l3.getModel().getElementAt(itemindex2).toString())) {
				demolist4.addElement(l3.getModel().getElementAt(itemindex2).toString());

			}
			searchbox2.setText("");
			searchbox2.requestFocus();
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}

package clinicProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
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

public class DemoMainProject extends JApplet implements Runnable, ActionListener, ItemListener, KeyListener,
		FocusListener, TextListener, ListSelectionListener, ListDataListener, MenuListener, MouseListener {

	TextField txname, txage, txrigtheye, txlefteye, txIOPrigth, txIOPleft, searchbox1, searchbox2, searchbox3,
			txUnique_id, txdygnosis, searchbox4, txmobile;
	JLabel lbname, lbage, lbvisoin, lbrighteye, lblefteye, lbcomplainWithDuration1, lbcomplainWithDuration2,
			lbhpreviousOperation1, lbhpreviousOperation2, lbhanyteratment, lbIOP, lbIOPrigth, lbIOPlrft, lbdygnosis,
			lbteratment1, lbteratment2, lbsex, lbhOfOtherDisese, lbimage1, lbimage2, lbimage3, lbimage4, lbmobile,
			lbpatientHistoryView, lbUnique_id;
	JList l1, l2, l3, l4, l5, l6;
	JRadioButton rdHPrevoisOperationYes, rdHPrevoisOperationNo, rdHtreatmentYes, rdHtreatmentNo, rdsexmale, rdsexFemale,
			rdsextransgender, rdpaid, rdfree, rdtemp1, rdtemp2, rdtemp3, rdtemp4;
	JPanel p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, pStart,
			ppatientHistoryView, panelmain, panelToPrint;
	JScrollPane jsleftpmain, jsprigth, jspl1, jspl2, jspl3, jspl4, jspl5, jspl6;
	JMenuBar mb;
	JMenu mfile, medit, mPatientSearch, mpatient;
	JMenuItem minew, mibyName, misave, miexit, mibydate, miupdte_remove, miadd;
	JButton btsave, btpatientHistroyView, btUnique_id;
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
	JPanel idpanel, mainprint;
	PrinterJob pj = PrinterJob.getPrinterJob();
	DefaultTableModel tbmodel = new DefaultTableModel();
	JPanel pHeader, pTable, Ptype, pglassTable;

	public static boolean truefalse = false;
	public static String name, mobile_no;
	public JPanel treatment, glass;
	JPanel[] printFinal;

	public void printComponenet(Component[] p) {

		/*
		 * JPanel[] p = new JPanel[2]; p[1] = p1; p[2] = p2;
		 */
		pj.setJobName(" Print Component ");

		pj.setPrintable(new Printable() {
			public int print(Graphics pg, PageFormat pf, int pageNum) {
				if (pageNum < p.length) {
					Graphics2D g2 = (Graphics2D) pg;
					g2.translate(pf.getImageableX(), pf.getImageableY());
					g2.scale(0.45, 0.70);
					p[pageNum].paint(g2);

					return Printable.PAGE_EXISTS;

				}

				return Printable.NO_SUCH_PAGE;
			}
		});
		if (pj.printDialog() == false)
			return;

		try {
			pj.print();
		} catch (PrinterException ex) {
			ex.printStackTrace();
		}
	}

	public void Eraseall() {
		// clearing text boxes
		txname.setText("");
		txage.setText("");
		txrigtheye.setText("");
		txlefteye.setText("");
		txIOPrigth.setText("");
		txIOPleft.setText("");
		searchbox1.setText("");
		searchbox2.setText("");
		searchbox3.setText("");
		txUnique_id.setText("");
		txdygnosis.setText("");
		searchbox4.setText("");
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
		l5.removeAll();
		demolist5.removeAllElements();
		l6.removeAll();
		demolist6.removeAllElements();

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

	public boolean cheakItemToAdd(String item) {

		boolean cheak = true;
		for (int i = 0; i < l1.getModel().getSize(); i++) {
			if (item.equals(l1.getModel().getElementAt(i).toString())) {
				cheak = false;
			}

		}
		return cheak;

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

		txUnique_id = new TextField(10);
		txUnique_id.setFont(f2);
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

		lbUnique_id = new JLabel("Enter Unique ID");
		lbUnique_id.setFont(f1);
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

		btUnique_id = new JButton("Show");
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
		mPatientSearch.addMenuListener(this);

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
		lbUnique_id.setBounds(50, 5, 150, 20);
		txUnique_id.setBounds(350, 5, 150, 20);
		btUnique_id.setBounds(550, 5, 70, 20);
		lbname.setBounds(50, 35, 150, 25);
		txname.setBounds(400, 35, 300, 30);
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
		p1.add(lbUnique_id);
		p1.add(txUnique_id);
		p1.add(btUnique_id);
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

		tbmodel.setColumnIdentifiers(new Object[] { "Sph", "Cyl", "Axis" });
		for (int count = 0; count < 2; count++) {
			tbmodel.insertRow(count, new Object[] { "", "", "" });
		}

		tbglassleft = new JTable(tbmodel);
		tbglassRight = new JTable(tbmodel);

		for (int i = 0; i < 3; i++) {
			tbglassleft.setRowHeight(i, 50);
			tbglassRight.setRowHeight(i, 50);

			tbglassRight.getColumnModel().getColumn(i).setPreferredWidth(100);
			tbglassleft.getColumnModel().getColumn(i).setPreferredWidth(100);

		}

		p19 = new JPanel();
		// p19.setBackground(Color.GRAY);
		pglassTable = new JPanel();
		// pglassTable.setBackground(Color.GRAY);

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
		btUnique_id.addActionListener(this);
		l2.addMouseListener(this);
		l3.addMouseListener(this);
		l5.addMouseListener(this);
		txname.addTextListener(this);
		txmobile.addTextListener(this);

		minew.addActionListener(this);
		misave.addActionListener(this);
		miexit.addActionListener(this);
		miupdte_remove.addActionListener(this);
		miadd.addActionListener(this);
		btsave.addActionListener(this);
		btpatientHistroyView.addActionListener(this);
		txmobile.addFocusListener(this);
		mibyName.addActionListener(this);
		mibydate.addActionListener(this);
		minew.addActionListener(this);
		validate();
	}

	@Override
	public void focusGained(FocusEvent e) {

	}

	@Override
	public void focusLost(FocusEvent e) {
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
		if (src == l5) {
			if (e.getKeyCode() == 10) {

				if (cheakItemToAdd(l5.getSelectedValue().toString())) {
					demolist6.addElement(l5.getSelectedValue().toString());

				}
				searchbox3.setText("");
				searchbox3.requestFocus();

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
		if (src == searchbox3) {

			if (e.getKeyCode() == 40) {
				l5.requestFocus();
				l5.setSelectedIndex(0);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void itemStateChanged(ItemEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object src = e.getSource();
		if (src == miexit) {

			System.exit(0);
		}
		if (src == miupdte_remove) {

			d = new Update_remove();
		}
		if (src == miadd) {
			d = new Add_A_Medicine();
		}
		if (src == btsave) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");

				// Data base creating
				Statement sta = con.createStatement();
				sta.executeUpdate("create database if not exists Patient_History");
				sta.execute("use Patient_History");

				String l1_items = "", l4_items = "", l6_items = "";

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
				for (int i = 0; i < l6.getModel().getSize(); i++) {
					l6_items += l6.getModel().getElementAt(i).toString();
					if (i != l6.getModel().getSize() - 1)
						l6_items += ",";
				}

				PreparedStatement psta = con.prepareStatement(
						"insert into History(date,name,age,mobile_No,Gender,History_of_Prev_opretion,History_of_any_treatment,VisionR,VisionL,complain_with_Duration,History_Of_other_diseases,IOP_R,IOP_L,Diagnostic,Treatment,SphR,CylR,AxisR,SphL,CylL,AxisL,Type,Bifocal,Krypotok,Excutive,Photo_grey,E_white,Constant,Distance,Near) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

				psta.setObject(1, LocalDate.now());
				psta.setString(2, txname.getText());
				psta.setString(3, txage.getText());
				psta.setString(4, txmobile.getText());

				{
					if (rdsexmale.isSelected()) {
						psta.setString(5, "male");
					} else {
						psta.setString(5, "female");
					}
				}

				{
					if (rdHPrevoisOperationYes.isSelected()) {
						psta.setString(6, "yes");
					} else {
						psta.setString(6, "no");
					}
				}

				{
					if (rdHtreatmentYes.isSelected()) {
						psta.setString(7, "yes");
					} else {
						psta.setString(7, "no");
					}
				}

				psta.setString(8, txrigtheye.getText());
				psta.setString(9, txlefteye.getText());
				psta.setString(10, l1_items);
				psta.setString(11, l4_items);
				psta.setString(12, txIOPrigth.getText());
				psta.setString(13, txIOPleft.getText());
				psta.setString(14, txdygnosis.getText());
				psta.setString(15, l6_items);
				psta.setString(16, tbglassRight.getModel().getValueAt(0, 0).toString());
				psta.setString(17, tbglassRight.getModel().getValueAt(0, 1).toString());
				psta.setString(18, tbglassRight.getModel().getValueAt(0, 2).toString());
				psta.setString(19, tbglassleft.getModel().getValueAt(0, 0).toString());
				psta.setString(20, tbglassleft.getModel().getValueAt(0, 1).toString());
				psta.setString(21, tbglassleft.getModel().getValueAt(0, 2).toString());
				{
					if (rdpaid.isSelected()) {
						psta.setString(22, "Paid");
					} else {
						psta.setString(22, "Free");
					}

				}
				{
					if (chbifocl.isSelected()) {
						psta.setString(23, "Yes");
					} else {
						psta.setString(23, "No");
					}
				}

				{
					if (chkryptok.isSelected()) {
						psta.setString(24, "Yes");
					} else {
						psta.setString(24, "No");
					}
				}
				{
					if (chexecutive.isSelected()) {
						psta.setString(25, "Yes");
					} else {
						psta.setString(25, "No");
					}
				}
				{
					if (chphoto_grey.isSelected()) {
						psta.setString(26, "Yes");
					} else {
						psta.setString(26, "No");
					}
				}
				{
					if (chE_white.isSelected()) {
						psta.setString(27, "Yes");
					} else {
						psta.setString(27, "No");
					}
				}
				{
					if (chconstnt.isSelected()) {
						psta.setString(28, "Yes");
					} else {
						psta.setString(28, "No");
					}
				}
				{
					if (chdistance.isSelected()) {
						psta.setString(29, "Yes");
					} else {
						psta.setString(29, "No");
					}
				}
				{
					if (chnear.isSelected()) {
						psta.setString(30, "Yes");
					} else {
						psta.setString(30, "No");
					}
				}

				psta.executeUpdate();

				con.close();

			} catch (ClassNotFoundException ee) {
				// TODO Auto-generated catch block
				ee.printStackTrace();
			} catch (SQLException ee) {
				// TODO Auto-generated catch block
				ee.printStackTrace();
			}

			// panel for printing
			// treatment panel=>
			JLabel lbpname, lbpage, lbpdate, lbpiopR, lbpiopL, lbpiop, lbpgender;
			JList plist;
			JPanel treatment;
			Font f = new Font("Arial", Font.BOLD, 28);
			Font f2 = new Font("Arial", Font.BOLD, 35);
			lbpname = new JLabel("name:-" + txname.getText());
			lbpname.setFont(f);
			lbpage = new JLabel("Age:-" + txage.getText());
			lbpage.setFont(f);
			lbpdate = new JLabel("Date:-" + LocalDate.now());
			lbpdate.setFont(f);
			if (rdsexmale.isSelected()) {
				lbpgender = new JLabel("Gender:-Male");
			} else {
				lbpgender = new JLabel("Gender:-Female");
			}
			lbpgender.setFont(f);
			lbpiop = new JLabel("IOP:-");
			lbpiop.setFont(f);
			lbpiopR = new JLabel("Right:-" + txIOPrigth.getText());
			lbpiopR.setFont(f);
			lbpiopL = new JLabel("Left:-" + txIOPleft.getText());
			lbpiopL.setFont(f);
			DefaultListModel listmodel = new DefaultListModel<>();
			plist = new JList(listmodel);
			plist.setFont(f);
			plist = l6;
			treatment = new JPanel();
			treatment.setBackground(Color.WHITE);
			treatment.setLayout(null);
			lbpname.setBounds(50, 250, 400, 50);
			lbpdate.setBounds(1100, 250, 300, 50);
			lbpgender.setBounds(50, 400, 400, 50);
			lbpage.setBounds(700, 400, 250, 50);
			lbpiopR.setBounds(200, 500, 200, 40);
			lbpiop.setBounds(50, 550, 100, 50);
			lbpiopL.setBounds(200, 610, 200, 40);
			plist.setBounds(400, 800, 800, 1500);
			plist.setFont(f2);

			treatment.add(lbpname);
			treatment.add(lbpage);
			treatment.add(lbpdate);
			treatment.add(lbpiopR);
			treatment.add(lbpiopL);
			treatment.add(lbpiop);
			treatment.add(lbpgender);
			treatment.add(plist);
			mainprint = new JPanel();
			mainprint.setLayout(null);
			treatment.setBounds(10, 10, 1500, 1700);
			mainprint.add(treatment);

			mainprint.setPreferredSize(new Dimension(1600, 1800));

			glass = new JPanel();
			glass = pglassTable;
			glass.setBackground(Color.WHITE);

			glass.setBounds(10, 10, 800, 900);

			glass.setPreferredSize(new Dimension(800, 900));

			printFinal = new JPanel[2];
			printFinal[0] = treatment;
			printFinal[1] = glass;
			printComponenet(printFinal);
			Eraseall();

		}
		if (src == btUnique_id) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");

				// Data base creating
				Statement sta = con.createStatement();
				sta.executeUpdate("create database if not exists clinicdb");
				sta.execute("use clinicdb");
				PreparedStatement psta = con.prepareStatement("select * from id_System where Unique_Id=?");
				psta.setInt(1, Integer.parseInt(txUnique_id.getText()));
				ResultSet rs = psta.executeQuery();

				while (rs.next()) {

					txname.setText(rs.getString("name"));
					txmobile.setText(rs.getString("mobile_No"));
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
					txlefteye.setText(rs.getString("VisionL"));

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

				}
				con.close();
				txmobile.requestFocus();
				txIOPrigth.requestFocus();

			} catch (ClassNotFoundException ee) {
				// TODO Auto-generated catch block
				ee.printStackTrace();
			} catch (SQLException ee) {
				// TODO Auto-generated catch block
				ee.printStackTrace();

			}
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

	}

	@Override
	public void textValueChanged(TextEvent e) {
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
		if (src == searchbox3) {
			if (searchbox3.getText().equals("") == false) {

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");

					// Data base creating
					Statement sta = con.createStatement();
					sta.executeUpdate("create database if not exists clinicDB");
					sta.execute("use clinicDB");

					String getString3 = searchbox3.getText();

					ResultSet rs = sta
							.executeQuery("select Medicines from Treatment where Medicines like '" + getString3 + "%'");

					while (rs.next()) {
						demolist5.addElement(rs.getString("Medicines"));

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

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {

	}

	@Override
	public void contentsChanged(ListDataEvent e) {

	}

	@Override
	public void intervalAdded(ListDataEvent e) {

	}

	@Override
	public void intervalRemoved(ListDataEvent e) {

	}

	@Override
	public void menuCanceled(MenuEvent e) {

	}

	@Override
	public void menuDeselected(MenuEvent e) {

	}

	@Override
	public void menuSelected(MenuEvent e) {

		Object src = e.getSource();

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
	/*
	 * public static void main(String[] args) { new DemoMainProject(); }
	 */

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
		if (src == l5) {
			int itemindex3 = l5.getSelectedIndex();
			if (cheakItemToAdd(l5.getModel().getElementAt(itemindex3).toString())) {
				demolist6.addElement(l5.getModel().getElementAt(itemindex3).toString());
			}
			searchbox3.setText("");
			searchbox3.requestFocus();
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

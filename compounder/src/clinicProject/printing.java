package clinicProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.time.LocalDate;

import javax.swing.DefaultListModel;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class printing extends JApplet implements ActionListener {
	JLabel lbpname, lbpage, lbpdate, lbpiopR, lbpiopL, lbpiop, lbpgender;
	JList plist;
	JPanel panel;
	JButton bt;
	JPanel main;
	PrinterJob pj = PrinterJob.getPrinterJob();

	public void printComponenet(JPanel p) {

		pj.setJobName(" Print Component ");

		pj.setPrintable(new Printable() {
			public int print(Graphics pg, PageFormat pf, int pageNum) {
				if (pageNum > 0) {
					System.out.println("No such pages");
					return Printable.NO_SUCH_PAGE;
				}

				java.awt.print.Paper card = pf.getPaper();
				card.setImageableArea(0, 0, 153, 243);
				card.setSize(595, 842);

				pf.setPaper(card);
				pf.setOrientation(PageFormat.PORTRAIT);
				Graphics2D g2 = (Graphics2D) pg;
				g2.translate(pf.getImageableX(), pf.getImageableY());

				g2.scale(0.65, 0.97);
				p.paint(g2);
				System.out.println("avelaible");
				return Printable.PAGE_EXISTS;
			}
		});
		if (pj.printDialog() == false)
			return;

		try {
			pj.print();
		} catch (PrinterException ex) {
			// handle exception
		}
	}

	public void init() {

		Font f = new Font("Arial", Font.BOLD, 16);
		lbpname = new JLabel("name:-");
		lbpname.setFont(f);
		lbpage = new JLabel("Age:-");
		lbpage.setFont(f);
		lbpdate = new JLabel("Date:-" + LocalDate.now());
		lbpdate.setFont(f);
		lbpgender = new JLabel("Gender:-");
		lbpgender.setFont(f);
		lbpiop = new JLabel("IOP:-");
		lbpiop.setFont(f);
		lbpiopR = new JLabel("Right:-");
		lbpiopR.setFont(f);
		lbpiopL = new JLabel("Left:-");
		lbpiopL.setFont(f);
		DefaultListModel listmodel = new DefaultListModel<>();
		plist = new JList(listmodel);
		plist.setFont(f);
		listmodel.add(0, "Vibhu");
		listmodel.add(1, "jakhra");
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.RED);
		lbpname.setBounds(50, 200, 300, 30);
		lbpdate.setBounds(500, 200, 250, 30);
		lbpgender.setBounds(50, 240, 200, 25);
		lbpage.setBounds(375, 240, 200, 25);
		lbpiopR.setBounds(150, 280, 150, 25);
		lbpiop.setBounds(50, 310, 100, 25);
		lbpiopL.setBounds(150, 340, 150, 25);
		plist.setBounds(100, 400, 500, 400);

		panel.add(lbpname);
		panel.add(lbpage);
		panel.add(lbpdate);
		panel.add(lbpiopR);
		panel.add(lbpiopL);
		panel.add(lbpiop);
		panel.add(lbpgender);
		panel.add(plist);
		main = new JPanel();
		main.setLayout(null);
		panel.setBounds(10, 10, 800, 900);
		main.add(panel);

		main.setPreferredSize(new Dimension(900, 1000));
		bt = new JButton("print");
		add(bt);
		bt.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (src == bt) {
			printComponenet(panel);
		}
	}

}
package clinicProject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Update_remove extends JDialog implements ActionListener, TextListener, KeyListener {

	JLabel lbsearch, lbupdate;
	TextField txseacrh, txupdate;
	JButton btsave, btupdate, btremove;
	JPanel p_update, p_show, p_main, p_afterUpdate, p_save;
	JList lsearch;
	DefaultListModel dlist;
	JScrollPane jsp;

	public Update_remove() {

		setVisible(true);
		setSize(500, 500);
		lbsearch = new JLabel("Enter a Medicine Name");
		lbupdate = new JLabel("Update As");

		txseacrh = new TextField(20);
		Font f = new Font("Arial", Font.BOLD, 20);
		txseacrh.setFont(f);
		txupdate = new TextField(20);
		txupdate.setFont(f);

		btsave = new JButton("Save");
		btremove = new JButton("Remove");
		btupdate = new JButton("Update");

		dlist = new DefaultListModel<>();

		lsearch = new JList<>(dlist);
		lsearch.setFont(f);

		jsp = new JScrollPane(lsearch);

		p_show = new JPanel();
		p_show.setLayout(null);
		lbsearch.setBounds(20, 20, 150, 25);
		p_show.add(lbsearch);
		txseacrh.setBounds(200, 20, 200, 25);
		p_show.add(txseacrh);
		jsp.setBounds(200, 45, 200, 100);
		p_show.add(jsp);

		p_update = new JPanel();
		p_update.setLayout(null);
		btupdate.setBounds(20, 20, 100, 30);
		p_update.add(btupdate);
		btremove.setBounds(300, 20, 100, 30);
		p_update.add(btremove);

		p_afterUpdate = new JPanel();
		p_afterUpdate.setLayout(null);
		lbupdate.setBounds(20, 20, 150, 25);
		p_afterUpdate.add(lbupdate);
		txupdate.setBounds(200, 20, 200, 25);
		p_afterUpdate.add(txupdate);
		btsave.setBounds(150, 55, 100, 30);
		p_afterUpdate.add(btsave);

		p_main = new JPanel();
		p_main.setLayout(null);
		p_show.setBounds(20, 20, 500, 200);
		p_main.add(p_show);
		p_update.setBounds(20, 210, 500, 100);
		p_main.add(p_update);

		p_main.setPreferredSize(new Dimension(500, 500));

		validate();
		add(p_main, BorderLayout.CENTER);
		btupdate.addActionListener(this);
		btsave.addActionListener(this);
		btremove.addActionListener(this);
		txseacrh.addTextListener(this);
		txseacrh.addKeyListener(this);
		lsearch.addKeyListener(this);
		btsave.addActionListener(this);
		btremove.addActionListener(this);
		btupdate.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		Object src = ae.getSource();
		if (src == btupdate) {
			p_update.remove(btupdate);
			p_update.remove(btremove);
			p_main.remove(p_update);
			p_afterUpdate.setBounds(20, 210, 500, 100);
			p_main.add(p_afterUpdate);
			validate();
			validate();
			repaint();

		}
		if (src == btsave) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");

				// Data base creating
				Statement sta = con.createStatement();
				// excute data base
				sta.execute("use clinicDB");

				PreparedStatement psta = con.prepareStatement("update Treatment set Medicines=? where Medicines=?");
				psta.setString(1, txupdate.getText());
				psta.setString(2, txseacrh.getText());
				psta.executeUpdate();
				JOptionPane.showMessageDialog(null, "your info updated");
				txseacrh.setText("");
				dlist.removeAllElements();
				txupdate.setText("");

				con.close();
			} catch (ClassNotFoundException ee) {
				// TODO Auto-generated catch block
				ee.printStackTrace();
			} catch (SQLException ee) {
				// TODO Auto-generated catch block
				ee.printStackTrace();
			}

		}
		if (src == btremove) {
			try {

				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
				Statement sta = con.createStatement();
				sta.execute("use clinicDB");

				PreparedStatement psta = con.prepareStatement("delete from Treatment where Medicines=?");
				psta.setString(1, txseacrh.getText());
				psta.executeUpdate();
				JOptionPane.showMessageDialog(null, "your entry deleted");
				con.close();
			} catch (ClassNotFoundException ee) {
				// TODO: handle exception
				ee.printStackTrace();
			} catch (SQLException ee) {
				ee.printStackTrace();
			}
		}

	}

	@Override
	public void textValueChanged(TextEvent te) {
		// TODO Auto-generated method stub

		Object src = te.getSource();
		if (dlist != null)
			dlist.removeAllElements();
		if (src == txseacrh) {
			if (txseacrh.getText().equals("") == false) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");

					Statement sta = con.createStatement();
					sta.executeUpdate("create database if not exists clinicDB");

					// excute data base
					sta.execute("use clinicDB");
					String getString = txseacrh.getText();

					ResultSet rs = sta
							.executeQuery("select Medicines from Treatment where Medicines like '" + getString + "%'");

					while (rs.next()) {
						dlist.addElement(rs.getString("Medicines"));
						JOptionPane.showMessageDialog(null, rs.getString("Medicines"));
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
	public void keyPressed(KeyEvent ke) {
		// TODO Auto-generated method stub
		Object src = ke.getSource();
		if (src == lsearch) {
			if (ke.getKeyCode() == 10) {
				txseacrh.setText(lsearch.getSelectedValue().toString());
				dlist.removeAllElements();

				lsearch.removeAll();
				validate();
				JOptionPane.showMessageDialog(null, "" + lsearch.getSelectedIndex());
			}
		}

		if (src == txseacrh) {
			if (ke.getKeyCode() == 40) {
				lsearch.requestFocus();

			}
		}

	}

	@Override
	public void keyReleased(KeyEvent ke) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent ke) {
		// TODO Auto-generated method stub

	}

}

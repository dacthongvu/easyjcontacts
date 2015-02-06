package app.gui;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BoxLayout;

public class MainWindow extends JPanel {
	private JMenuBar menuBar;
	private JLabel title;
	private JComboBox list;
	private JTable table;
	private JMenu[] menus;
	private JMenuItem[] menuItems1, menuItems2, menuItems3;
	private JLabel[] labels;
	private JTextField[] fields;
	private JButton[] buttons1, buttons2;
	private String[] headers;
	private final int MENUS = 3;
	private final int MENU_ITEMS_1 = 4;
	private final int MENU_ITEMS_2 = 4;
	private final int MENU_ITEMS_3 = 2;
	private final int LABELS = 4;
	private final int FIELDS = 3;
	private final int BUTTS_1 = 4;
	private final int BUTTS_2 = 4;

	public MainWindow() {

		menuBar = new JMenuBar();

		title = new JLabel();
		title.setForeground(Color.RED);
		title.setFont(new Font("Serif", Font.BOLD, 36));

		list = new JComboBox();
		list.setPreferredSize(new Dimension(200, 20));

		menus = new JMenu[MENUS];
		menuItems1 = new JMenuItem[MENU_ITEMS_1];
		menuItems2 = new JMenuItem[MENU_ITEMS_2];
		menuItems3 = new JMenuItem[MENU_ITEMS_3];
		labels = new JLabel[LABELS];
		fields = new JTextField[FIELDS];
		buttons1 = new JButton[BUTTS_1];
		buttons2 = new JButton[BUTTS_2];

		for(int i = 0; i < menus.length; i++) {
				menus[i] = new JMenu();
				menuBar.add(menus[i]);
		}

		for(int i = 0; i < menuItems1.length; i++) {
				menuItems1[i] = new JMenuItem();
				menus[0].add(menuItems1[i]);
		}

		for(int i = 0; i < menuItems2.length; i++) {
				menuItems2[i] = new JMenuItem();
				menus[1].add(menuItems2[i]);
		}

		for(int i = 0; i < menuItems3.length; i++) {
				menuItems3[i] = new JMenuItem();
				menus[2].add(menuItems3[i]);
		}

		for(int i = 0; i < menus.length; i++) {

		}

		for(int i = 0; i < buttons1.length; i++) {
			buttons1[i] = new JButton();
			buttons1[i].setPreferredSize(new Dimension(120, 20));
		}

		for(int i = 0; i < buttons2.length; i++) {
			buttons2[i] = new JButton();
			buttons2[i].setPreferredSize(new Dimension(120, 20));
		}

		for(int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel();
			labels[i].setPreferredSize(new Dimension(60, 20));
		}

		for(int i = 0; i < fields.length; i++) {
			fields[i] = new JTextField();
			fields[i].setPreferredSize(new Dimension(180, 20));
		}

		JPanel p0 = new JPanel();
		p0.setLayout(new FlowLayout());
		p0.add(title);

		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(4, 1, 0, 5));
		for(int i = 0; i < buttons1.length; i++) {
			p1.add(buttons1[i]);
		}

		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(4, 1, 0, 5));
		for(int i = 0; i < labels.length; i++) {
			p2.add(labels[i]);
		}

		JPanel p3 = new JPanel();
		p3.setLayout(new GridLayout(4, 1, 0, 5));
		p3.add(fields[0]);
		p3.add(list);
		p3.add(fields[1]);
		p3.add(fields[2]);

		JPanel p4 = new JPanel();
		p4.setLayout(new GridLayout(4, 1, 0, 5));
		for(int i = 0; i < buttons2.length; i++) {
			p4.add(buttons2[i]);
		}

		JPanel p5 = new JPanel();
		p5.setLayout(new BoxLayout(p5, BoxLayout.X_AXIS));
		p5.add(p1);
		p5.add(Box.createRigidArea(new Dimension(30, 0)));
		p5.add(p2);
		p5.add(Box.createRigidArea(new Dimension(10, 0)));
		p5.add(p3);
		p5.add(Box.createRigidArea(new Dimension(30, 0)));
		p5.add(p4);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(p0);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(p5);
		add(Box.createRigidArea(new Dimension(0, 20)));
	}

//	Methods for menus
	public JMenu[] getMenus() {
		return menus;
	}

	public void setMenus(String[] s) throws IllegalArgumentException {
		if(s.length != menus.length)
			throw new IllegalArgumentException("Invalid argument!");

		for(int i = 0; i < menus.length; i++) {
			menus[i].setText(s[i]);
		}
	}

//	Methods for menuItems1
	public JMenuItem[] getMenuItems1() {
		return menuItems1;
	}

	public void setMenuItems1(String[] s) throws IllegalArgumentException {
		if(s.length != menuItems1.length)
			throw new IllegalArgumentException("Invalid argument!");

		for(int i = 0; i < menuItems1.length; i++) {
			menuItems1[i].setText(s[i]);
		}
	}

//	Methods for menuItems2
	public JMenuItem[] getMenuItems2() {
		return menuItems2;
	}

	public void setMenuItems2(String[] s) throws IllegalArgumentException {
		if(s.length != menuItems2.length)
			throw new IllegalArgumentException("Invalid argument!");

		for(int i = 0; i < menuItems2.length; i++) {
			menuItems2[i].setText(s[i]);
		}
	}

//	Methods for menuItems3
	public JMenuItem[] getMenuItems3() {
		return menuItems3;
	}

	public void setMenuItems3(String[] s) throws IllegalArgumentException {
		if(s.length != menuItems3.length)
			throw new IllegalArgumentException("Invalid argument!");

		for(int i = 0; i < menuItems3.length; i++) {
			menuItems3[i].setText(s[i]);
		}
	}

//	Methods for buttons1
	public JButton[] getButtons1() {
		return buttons1;
	}

	public void setButtons1(String[] s) throws IllegalArgumentException {
		if(s.length != buttons1.length)
			throw new IllegalArgumentException("Invalid argument!");

		for(int i = 0; i < buttons1.length; i++) {
			buttons1[i].setText(s[i]);
		}
	}

//	Methods for buttons2
	public JButton[] getButtons2() {
		return buttons2;
	}

	public void setButtons2(String[] s) throws IllegalArgumentException {
		if(s.length != buttons2.length)
			throw new IllegalArgumentException("Invalid argument!");

		for(int i = 0; i < buttons2.length; i++) {
			buttons2[i].setText(s[i]);
		}
	}

//	Methods for labels
	public JLabel[] getLabels() {
		return labels;
	}

	public void setLabels(String[] s) throws IllegalArgumentException {
		if(s.length != labels.length)
			throw new IllegalArgumentException("Invalid argument!");

		for(int i = 0; i < labels.length; i++) {
			labels[i].setText(s[i]);
		}
	}

//	Methods for fields
	public JTextField[] getFields() {
		return fields;
	}

	public String[] getFieldValues() {
		return new String[] {fields[0].getText(),
								String.valueOf(list.getSelectedItem()),
								fields[1].getText(),
								fields[2].getText()};
	}

	public void setFieldValues(String[] s) throws IllegalArgumentException {
		if(s.length != 4)
			throw new IllegalArgumentException("Invalid argument!");

		fields[0].setText(s[0]);
		list.setSelectedItem(s[1]);
		fields[1].setText(s[2]);
		fields[2].setText(s[3]);
	}

//	methods for menuBar
	public JMenuBar getMenuBar() {
		return menuBar;
	}

//	methods for title
	public void setTitle(String s) {
		title.setText(s);
	}

//	methods for list
	public JComboBox getList() {
		return list;
	}

	public void setListItems(String[] s) {
		for(int i = 0; i < s.length; i++) {
			list.addItem(s[i]);
		}
	}

	public void enableButt(int i, boolean b) {
		switch(i) {
			case 1:
					menuItems1[0].setEnabled(b);
					buttons1[0].setEnabled(b);
					break;
			case 2:
					menuItems1[1].setEnabled(b);
					buttons1[1].setEnabled(b);
					break;
			case 3:
					menuItems1[2].setEnabled(b);
					buttons1[2].setEnabled(b);
					break;
			case 4:
					menuItems1[3].setEnabled(b);
					buttons1[3].setEnabled(b);
					break;
			case 5:
					menuItems2[0].setEnabled(b);
					buttons2[0].setEnabled(b);
					break;
			case 6:
					menuItems2[1].setEnabled(b);
					buttons2[1].setEnabled(b);
					break;
			case 7:
					menuItems2[2].setEnabled(b);
					buttons2[2].setEnabled(b);
					break;
			case 8:
					menuItems2[3].setEnabled(b);
					buttons2[3].setEnabled(b);
					break;
		}
	}
}
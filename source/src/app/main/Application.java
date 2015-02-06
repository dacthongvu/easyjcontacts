package app.main;

import app.model.*;
import app.gui.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Application extends JFrame {
	private final String TITLE = "Easy JContacts";
	private final String[] CHOICES1 = {"Please Select", "Male", "Female"};
	private final String[] HEADERS = {"Name", "Sex", "Phone", "Email"};
	private final int RECORDS = 100;
	private final String PATH = "../data/";
	private int numOfRecs, markedRow;
	private boolean modified, edit, open;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private File file;
	private JMenuItem[] menus1, menus2, menus3;
	private JButton[] buttons1, buttons2;
	private JTextField[] fields;
	private JComboBox list;
	private JTable dataTable;
	private MainWindow window;
	private Contact[] records;
	private Contact record;


	public Application() {
		super("Easy JContacts");

		window = new MainWindow();

		resetApp();
		window.setTitle(TITLE);
		window.setListItems(CHOICES1);

		window.setMenus(new String[] {"File", "Record", "Help"});
		window.setMenuItems1(new String[] {"Open File", "Close File", "Save File", "Exit Program"});
		window.setMenuItems2(new String[] {"New Record", "Add Record", "Edit Record", "Delete Record"});
		window.setMenuItems3(new String[] {"Help Topics", "About"});
		window.setLabels(new String[] {"Name", "Sex", "Phone", "Email"});

		dataTable = new JTable(new String[RECORDS][HEADERS.length], HEADERS);
		dataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(dataTable);
		dataTable.addKeyListener(new TableKeyHandler());
		dataTable.addMouseListener(new TableMouseHandler());

		fields = window.getFields();
		list = window.getList();

		menus1 = window.getMenuItems1();
		menus1[0].addActionListener(new OpenHandler());
		menus1[1].addActionListener(new CloseHandler());
		menus1[2].addActionListener(new SaveHandler());
		menus1[3].addActionListener(new ExitHandler());

		menus2 = window.getMenuItems2();
		menus2[0].addActionListener(new NewHandler());
		menus2[1].addActionListener(new AddHandler());
		menus2[2].addActionListener(new EditHandler());
		menus2[3].addActionListener(new DelHandler());

		menus3 = window.getMenuItems3();
		menus3[0].addActionListener(new HelpHandler());
		menus3[1].addActionListener(new AboutHandler());

		window.setButtons1(new String[] {"Open File", "Close File", "Save File", "Exit Program"});
		window.setButtons2(new String[] {"New Record", "Add Record", "Edit Record", "Delete Record"});

		buttons1 = window.getButtons1();
		buttons1[0].addActionListener(new OpenHandler());
		buttons1[1].addActionListener(new CloseHandler());
		buttons1[2].addActionListener(new SaveHandler());
		buttons1[3].addActionListener(new ExitHandler());

		buttons2 = window.getButtons2();
		buttons2[0].addActionListener(new NewHandler());
		buttons2[1].addActionListener(new AddHandler());
		buttons2[2].addActionListener(new EditHandler());
		buttons2[3].addActionListener(new DelHandler());

		setJMenuBar(window.getMenuBar());
		getContentPane().add(window, BorderLayout.NORTH);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if(modified) {
					int cfm = checkSave();

					if(cfm == 2) {
						return;
					}
					else {
						if(cfm == 0) {
							try {
								saveFile();
							}
							catch(IOException exp) {
							}
						}
					}
				}

				System.exit(0);
			}
		});
		setSize(550, 450);

		fields[0].requestFocus();
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

//	General methods

	public void resetApp() {
		record = new Contact();
		records = new Contact[RECORDS];
		numOfRecs = 0;
		markedRow = -1;
		modified = false;
		edit = false;
		open = false;
		window.enableButt(2, false);
		window.enableButt(3, false);
		window.enableButt(7, false);
		window.enableButt(8, false);
	}
	public void setTitle() {
		if(!open)
			this.setTitle("Easy JContacts");
		else {
			if(!modified)
				this.setTitle("Easy JContacts - File: " + file.getName());
			else
				this.setTitle("Easy JContacts - File: " + file.getName() + "*");
		}
	}

	public void updateTable() {
		for(int i = 0; i < numOfRecs; i++) {
			if(i == markedRow)
				dataTable.setValueAt(records[i].getName() + "*", i, 0);
			else
				dataTable.setValueAt(records[i].getName(), i, 0);

			dataTable.setValueAt(records[i].getSex(), i, 1);
			dataTable.setValueAt(records[i].getPhone(), i, 2);
			dataTable.setValueAt(records[i].getEmail(), i, 3);
		}

		for(int i = numOfRecs; i < RECORDS; i++) {
			dataTable.setValueAt(null, i, 0);
			dataTable.setValueAt(null, i, 1);
			dataTable.setValueAt(null, i, 2);
			dataTable.setValueAt(null, i, 3);
		}

		checkRow();
	}

	public void checkRow() {
		if(dataTable.getSelectedRow() < numOfRecs && dataTable.getSelectedRow() > -1) {
			window.enableButt(7, true);
			window.enableButt(8, true);
		}
		else {
			window.enableButt(7, false);
			window.enableButt(8, false);
		}
	}

	public void setFields() {
		int i = dataTable.getSelectedRow();
		markedRow = i;
		updateTable();
		window.setFieldValues(new String[] {records[i].getName(), records[i].getSex(), records[i].getPhone(), records[i].getEmail()});
		menus2[1].setText("Save Record");
		buttons2[1].setText("Save Record");
		window.enableButt(6, true);
		fields[0].requestFocus();
	}

	public void resetFields() {
		markedRow = -1;
		updateTable();
		window.setFieldValues(new String[] {"", "Please Select", "", ""});
		menus2[1].setText("Add Record");
		buttons2[1].setText("Add Record");
		fields[0].requestFocus();
		setTitle();
		updateTable();
	}

	public boolean validateField() {
		if((fields[0].getText()).equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter the Name field",
											"Input Error", JOptionPane.ERROR_MESSAGE);
			fields[0].requestFocus();
			return false;
		}

		if(list.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Please select from the Sex list",
											"Input Error", JOptionPane.ERROR_MESSAGE);
			list.requestFocus();
			return false;
		}

		if((fields[1].getText()).equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter the Phone field",
											"Input Error", JOptionPane.ERROR_MESSAGE);
			fields[1].requestFocus();
			return false;
		}

		if((fields[2].getText()).equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter the Email field",
											"Input Error", JOptionPane.ERROR_MESSAGE);
			fields[2].requestFocus();
			return false;
		}

		return true;
	}


// Methods for records

	public void newRecord() {
		edit = false;
		resetFields();

	}

	public void addRecord() {
		if(validateField()) {
			modified = true;
			if(!edit) {
				record = new Contact(window.getFieldValues());
				records[numOfRecs] = record;
				numOfRecs++;
			}
			else {
				int i = dataTable.getSelectedRow();
				records[i].setContact(window.getFieldValues());
				edit = false;
			}
			window.enableButt(3, true);
			resetFields();
		}
	}

	public void editRecord() {
		edit = true;
		setFields();
	}

	public void delRecord() {
		modified = true;
		int i = dataTable.getSelectedRow();

		for(int c = i; c < numOfRecs - 1; c++) {
			records[c] = records[c + 1];
		}

		records[numOfRecs - 1] = new Contact();
		numOfRecs--;
		window.enableButt(3, true);
		resetFields();
	}

//Methods for files

	public void openFile() throws IOException {
		JFileChooser fc = new JFileChooser(new File(PATH));
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int cmd = fc.showOpenDialog(this);

		if(cmd == JFileChooser.CANCEL_OPTION)
				return;

		file = fc.getSelectedFile();

		resetApp();
		input = new ObjectInputStream(new FileInputStream(file));

		try {
			while(numOfRecs >= 0) {
				records[numOfRecs] = (Contact) input.readObject();
				numOfRecs++;
			}
		}

		catch(EOFException exp) {
			input.close();
		}
		catch(ClassNotFoundException exp) {
		}

		open = true;
		window.enableButt(2, true);
		resetFields();
	}

	public void saveFile() throws IOException {
		if(!open) {
			JFileChooser fc = new JFileChooser(new File(PATH));
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int cmd = fc.showSaveDialog(this);

			if(cmd == JFileChooser.CANCEL_OPTION)
				return;

			file = fc.getSelectedFile();

			if(file.exists()) {
				int cfm = JOptionPane.showConfirmDialog(this, "This file already exists, overwrite it?",
													 "Overwrite Confirm", 0);
				if(cfm == 1)
					return;
			}
		}

		output = new ObjectOutputStream(new FileOutputStream(file));

		for(int i = 0; i < numOfRecs; i++) {
			output.writeObject(records[i]);
			output.flush();
		}
		modified = false;
		open = true;
		window.enableButt(2, true);
		window.enableButt(3, false);
		setTitle();
		updateTable();
	}

	public void closeFile() {
		resetApp();
		resetFields();
	}

	public int checkSave() {
		return JOptionPane.showConfirmDialog(this, "Do you want to save changes to file?", "Save changes", 1);
	}


//	Handlers for records

	private class NewHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			newRecord();
		}
	}

	private class AddHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			addRecord();
		}
	}

	private class EditHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			editRecord();
		}
	}

	private class DelHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int cfm = JOptionPane.showConfirmDialog(Application.this, "Are you sure to delete this record?",
													 "Delete Confirm", 0);
			if(cfm == 0) {
				delRecord();
			}
		}
	}


//	Handlers for files
	private class OpenHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(modified) {
				int cfm = checkSave();

				if(cfm == 2) {
					return;
				}
				else {
					if(cfm == 0) {
						try {
							saveFile();
						}
						catch(IOException exp) {
						}
					}
					else {
						closeFile();
					}
				}
			}

			try {
				openFile();
			}
			catch(IOException exp) {
			}
		}
	}

	private class SaveHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				saveFile();
			}
			catch(IOException exp) {
				System.err.println("Cannot save!");
			}
		}
	}

	private class CloseHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(modified) {
				int cfm = checkSave();

				if(cfm == 2) {
					return;
				}
				else {
					if(cfm == 0) {
						try {
							saveFile();
						}
						catch(IOException exp) {
						}
					}
				}
			}
			closeFile();
		}
	}

	private class ExitHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(modified) {
				int cfm = checkSave();

				if(cfm == 2) {
					return;
				}
				else {
					if(cfm == 0) {
						try {
							saveFile();
						}
						catch(IOException exp) {
						}
					}
				}
			}
			System.exit(0);
		}
	}

	private class TableKeyHandler extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			checkRow();
		}

		public void keyReleased(KeyEvent e) {
			checkRow();
		}
	}

	private class TableMouseHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			checkRow();
		}
	}

	private class HelpHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(Application.this, "Sorry, help is not available in this release." +
											"\nVersion 1.0\n(C) 2005 by Vu Dac Thong. All rights reserved.",
											"Help Topics", JOptionPane.ERROR_MESSAGE);
		}
	}

	private class AboutHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(Application.this, "This application allows you to manage Contacts" +
											"\nVersion 1.0\n(C) 2005 by Vu Dac Thong. All rights reserved.",
											"About Easy JContacts", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public static void main(String[] args) {
			new Application();
	}
}
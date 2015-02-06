package app.model;

import java.io.*;

public class Contact implements Serializable {
	public static final int SIZE = 72;
	private final int RAND = 100;
	private int id;
	private String name;
	private String sex;
	private String phone;
	private String email;

	public Contact(String[] sa) {
		if(sa.length == 4) {
			id = 1 + (int) (Math.random() * RAND);
			setContact(sa[0], sa[1], sa[2], sa[3]);
		}
	}

	public Contact(String n, String s, String d, String c) {
		id = 1 + (int) (Math.random() * RAND);
		setContact(n, s, d, c);
	}

	public Contact() {
		this("", "", "", "");
		id = 0;
	}

	public int getID() {
		return id;
	}

	public void setName(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}

	public void setSex(String s) {
		sex = s;
	}

	public String getSex() {
		return sex;
	}

	public void setPhone(String d) {
		phone = d;
	}

	public String getPhone() {
		return phone;
	}

	public void setEmail(String c) {
		email = c;
	}

	public String getEmail() {
		return email;
	}

	public void setContact(String n, String s, String d, String c) {
		setName(n);
		setSex(s);
		setPhone(d);
		setEmail(c);
	}

	public void setContact(String sa[]) {
		if(sa.length == 4) {
			setName(sa[0]);
			setSex(sa[1]);
			setPhone(sa[2]);
			setEmail(sa[3]);
		}

	}

	public String toString() {
		return getID() + "\t" + getName() + "\t" + getSex() + "\t" + getPhone() + "\t" + getEmail();
	}
}
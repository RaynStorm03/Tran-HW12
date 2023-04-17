package serialization;

import java.io.Serializable;

public class Person implements Serializable {
	String name;
	private String phone;
	private String DOB;
	private String email;
	
	public Person(String name, String phone, String dOB, String email) {
		super();
		this.name = name;
		this.phone = phone;
		this.DOB = dOB;
		this.email = email;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Name: " + name + "\t Phone: " + phone + "\t DOB: " + DOB + "\t Email: " + email;
	}
	
	
}

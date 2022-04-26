package com.auth.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Employee extends User {

	private String firstName;
	private String lastName;
	private String NIC;

	public Employee() {
		super();
	}

	public Employee(Integer iD, String contactNo, String email, String password, String type, String nIC,
			String firstName, String lastName) {
		super(iD, type, email, password, contactNo);
		this.firstName = firstName;
		this.lastName = lastName;
		this.NIC = nIC;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNIC() {
		return NIC;
	}

	public void setNIC(String nIC) {
		NIC = nIC;
	}

	@Override
	public String toString() {
		return "Doctor [firstName=" + firstName + ", lastName=" + lastName + ", NIC=" + NIC + "]";
	}

}

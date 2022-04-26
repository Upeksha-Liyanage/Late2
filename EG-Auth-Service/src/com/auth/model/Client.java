package com.auth.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Client extends User {

	private String firstName;
	private String lastName;
	private String NIC;
	private String address;
	private String electroId;

	public Client() {
		super();
	}

	public Client(Integer iD, String contactNo, String email, String password, String type, String nIC,
			String address, String firstName, String lastName, String electroId) {
		super(iD, type, email, password, contactNo);
		this.firstName = firstName;
		this.lastName = lastName;
		this.NIC = nIC;
		this.address = address;
		this.electroId = electroId;
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


	public String getElectroId() {
		return electroId;
	}

	public void setElectroId(String electroId) {
		this.electroId = electroId;
	}

	public String getNIC() {
		return NIC;
	}

	public void setNIC(String nIC) {
		NIC = nIC;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Patient [firstName=" + firstName + ", lastName=" + lastName + ", electroId="
				+ electroId + ", NIC=" + NIC + ", address=" + address + "]";
	}

}

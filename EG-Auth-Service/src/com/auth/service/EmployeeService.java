package com.auth.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.auth.database.DBConnection;
import com.auth.model.Employee;

public class EmployeeService {

	/* User type - Employee */

//Insert Employee
	public String insertEmployee(String type, String email, String password, String contactNo, String firstName,
			String lastName, String NIC) {

		String output = "";
		try {
			Connection con = DBConnection.connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " insert into employee (`id`,`type`,`email`,`password`,`contactNo`,`firstName`,`lastName`,`NIC`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, type);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, password);
			preparedStmt.setString(5, contactNo);
			preparedStmt.setString(6, firstName);
			preparedStmt.setString(7, lastName);
			preparedStmt.setString(8, NIC);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";

		} catch (Exception e) {
			output = "Error while inserting the Employee.";
			System.err.println(e.getMessage());

		}
		return output;
	}

//Read Employee list	
	public String readEmployee() {
		String output = "";
		try {
			Connection con = DBConnection.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Employee ID</th><th>User type</th><th>Employee Email</th><th>Password</th><th>Contact no"
					+ "</th><th>First name</th><th>Last name</th><th>NIC</th><th>Sex</th><th>Specialization</th><th>Update</th><th>Remove</th></tr>";

			String query = "select * from employee";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String ID = Integer.toString(rs.getInt("ID"));
				String type = rs.getString("type");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String contactNo = rs.getString("contactNo");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String NIC = rs.getString("NIC");

				// Add into the html table
				output += "<tr><td>" + ID + "</td>";
				output += "<td>" + type + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + password + "</td>";
				output += "<td>" + contactNo + "</td>";
				output += "<td>" + firstName + "</td>";
				output += "<td>" + lastName + "</td>";
				output += "<td>" + NIC + "</td>";

				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\""
						+ " value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"employee.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"" + " class=\"btn btn-danger\">"
						+ "<input name=\"ID\" type=\"hidden\" value=\"" + ID + "\">" + "</form></td></tr>";
			}
			con.close();

			// Complete the html table
			output += "</table>";

		} catch (Exception e) {
			output = "Error while reading the Employee.";
			System.err.println(e.getMessage());
		}

		return output;
	}

//Employee update
	public String updateEmployee(String ID, String contactNo) {
		String output = "";
		try {
			Connection con = DBConnection.connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE employee SET contactNo=? " + "WHERE ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			preparedStmt.setString(1, contactNo);
			preparedStmt.setInt(2, Integer.parseInt(ID));

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";

		} catch (Exception e) {
			output = "Error while updating the Employee.";
			System.err.println(e.getMessage());
		}

		return output;
	}

//Delete existing Employee
	public String deleteEmployee(String ID) {
		String output = "";
		try {
			Connection con = DBConnection.connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from employee " + "where id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(ID));

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";

		} catch (Exception e) {
			output = "Error while deleting the Employee.";
			System.err.println(e.getMessage());
		}

		return output;
	}

//Retrieve  single Employee
	public static Employee getEmployeeDetails(String id) {
		Employee employee = null;
		try {

			Connection con = DBConnection.connect();

			String getSql = "SELECT * FROM employee WHERE id = ? ";

			PreparedStatement ps_getEmployeeDetails = con.prepareStatement(getSql);
			ps_getEmployeeDetails.setInt(1, Integer.parseInt(id));

			ResultSet rs = ps_getEmployeeDetails.executeQuery();

			while (rs.next()) {

				employee = new Employee(Integer.parseInt(id), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return employee;
	}

}

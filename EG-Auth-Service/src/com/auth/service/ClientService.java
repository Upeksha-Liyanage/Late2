package com.auth.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.auth.database.DBConnection;

import com.auth.model.Client;

public class ClientService {

	
	
	/* User type - Client */
	
//	Insert Client
	public String insertClient(String type, String email, String password, String contactNo, String firstName, String lastName, String electroId, String NIC, String address) {
		String output = "";
		try {
			Connection con = DBConnection.connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			
			// create a prepared statement
			String query = " insert into client (`id`,`type`,`email`,`password`,`contactNo`,`firstName`,`lastName`,`electroId`,`nic`,`address`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, type);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, password);
			preparedStmt.setString(5, contactNo);
			preparedStmt.setString(6, firstName);
			preparedStmt.setString(7, lastName);
			preparedStmt.setString(8, electroId);
			preparedStmt.setString(9, NIC);
			preparedStmt.setString(10, address);

			
			

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the client.";
			System.err.println(e.getMessage());
		}
		return output;
	}
		

	//Read Client list	
		public String readClient() {
			String output = "";
			try {
				Connection con = DBConnection.connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}
				
				
				// Prepare the html table to be displayed
				output = "<table border=\"1\"><tr><th>Client ID</th><th>User type</th><th>Client Email</th><th>Password</th><th>Contact no"
						+ "</th><th>First name</th><th>Last name</th><th>DOB</th><th>Age</th><th>sex</th><th>NIC</th><th>Address</th><th>Update</th><th>Remove</th></tr>";
				
				String query = "select * from client";
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
					String electroId = rs.getString("electroId");
					String NIC = rs.getString("NIC");
					String address = rs.getString("address");
					
					// Add into the html table
					output += "<tr><td>" + ID + "</td>";
					output += "<td>" + type + "</td>";
					output += "<td>" + email + "</td>";
					output += "<td>" + password + "</td>";
					output += "<td>" + contactNo + "</td>";
					output += "<td>" + firstName + "</td>";
					output += "<td>" + lastName + "</td>";
					output += "<td>" + electroId + "</td>";
					output += "<td>" + NIC + "</td>";
					output += "<td>" + address + "</td>";
					
					
					// buttons
					output += "<td><input name=\"btnUpdate\" type=\"button\""
							+ " value=\"Update\" class=\"btn btn-secondary\"></td>"
							+ "<td><form method=\"post\" action=\"client.jsp\">"
							+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"" + " class=\"btn btn-danger\">"
							+ "<input name=\"ID\" type=\"hidden\" value=\"" + ID + "\">" + "</form></td></tr>";
				}
				con.close();
				
				
				// Complete the html table
				output += "</table>";
			} catch (Exception e) {
				output = "Error while reading the client.";
				System.err.println(e.getMessage());
			}
			return output;
			
		}		
			
			
//			Client update
			public String updateClient(String ID, String contactNo, String address) {
				String output = "";
				try {
					Connection con = DBConnection.connect();
					if (con == null) {
						return "Error while connecting to the database for updating.";
					}
					
					
					// create a prepared statement
					String query = "UPDATE client SET contactNo=?, address=? WHERE ID=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
//					preparedStmt.setString(1, password);
					preparedStmt.setString(1, contactNo);
					preparedStmt.setString(2, address);
					preparedStmt.setInt(3, Integer.parseInt(ID));
					
					
					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Updated successfully";
				} catch (Exception e) {
					output = "Error while updating the client.";
					System.err.println(e.getMessage());
				}
				return output;
			}
		
		
			
			//Delete existing Client
				public String deleteClient(String ID) {
					String output = "";
					try {
						Connection con = DBConnection.connect();
						if (con == null) {
							return "Error while connecting to the database for deleting.";
						}
						
						
						// create a prepared statement
						String query = "delete from client "
								+ "where id=?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						
						
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(ID));
						
						
						// execute the statement
						preparedStmt.execute();
						con.close();
						output = "Deleted successfully";
					} catch (Exception e) {
						output = "Error while deleting the client.";
						System.err.println(e.getMessage());
					}
					return output;
				}
								
				
				
//				Retrieve  single Client
				public static Client getClientDetails(String id) {
					Client client = null;
					try {

						Connection con = DBConnection.connect();

						String getSql = "SELECT * FROM client WHERE id = ? ";
						PreparedStatement ps_getClientDetails = con.prepareStatement(getSql);
						ps_getClientDetails.setInt(1, Integer.parseInt(id));

						ResultSet rs = ps_getClientDetails.executeQuery();

						while (rs.next()) {

							
							client = new Client(Integer.parseInt(id), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8) , rs.getString(9), rs.getString(10));

						
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

					return client;
				}

				
}

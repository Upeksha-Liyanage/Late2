package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Grids {

	// A common method to connect to the DB
		private Connection connect() {
			Connection con = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");

				// Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gridmanagement", "root", "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return con;
		}

		public String insertItem(String source, String 	area, String gridCode, String voltage, String station, String operator,
				String engineer) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for inserting.";
				}
				// create a prepared statement
				String query = " insert into powergrid(`id`,`source`,`area`,`gridCode`,`output`,`station`,`operator`,`engineer`)"
						+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, source);
				preparedStmt.setString(3, area);
				preparedStmt.setString(4, gridCode);
				preparedStmt.setString(5, voltage);
				preparedStmt.setString(6, station);
				preparedStmt.setString(7, operator);
				preparedStmt.setString(8, engineer);
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
			} catch (Exception e) {
				output = "Error while inserting the item.";
				System.err.println(e.getMessage());
			}
			return output;
		}

		public String readItems() {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}
				// Prepare the html table to be displayed
				output = "<table border=\"2\"><tr><th>Power Source</th><th>Area</th><th>Grid Code</th><th>Output</th><th>Station</th>"
						+ "<th>Oparator</th><th>Engineer</th><th>Update</th><th>Remove</th></tr>";
				String query = "select * from powergrid";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next()) {
					String id = Integer.toString(rs.getInt("id"));
					String source = rs.getString("source");
					String area = rs.getString("area");
					String gridcode = rs.getString("gridcode");
					String output1 = rs.getString("output");
					String station = rs.getString("station");
					String operator = rs.getString("operator");
					String engineer = rs.getString("engineer");
					// Add into the html table
					output += "<tr><td>" + source + "</td>";
					output += "<td>" + area + "</td>";
					output += "<td>" + gridcode + "</td>";
					output += "<td>" + output1 + "</td>";
					output += "<td>" + station + "</td>";
					output += "<td>" + operator + "</td>";
					output += "<td>" + engineer + "</td>";
					// buttons
					output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
							+ "<td><form method=\"post\" action=\"powerGrids.jsp\">"
							+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
							+ "<input name=\"id\" type=\"hidden\" value=\"" + id + "\">" + "</form></td></tr>";
				}
				con.close();
				// Complete the html table
				output += "</table>";
			} catch (Exception e) {
				output = "Error while reading the items.";
				System.err.println(e.getMessage());
			}
			return output;
		}

		public String updateItem(String id,String source, String area, String gridCode, String voltage, String station, String operator,
				String engineer) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for updating.";
				}
				// create a prepared statement
				String query = "UPDATE powergrid SET source=?,area=?,gridCode=?,output=?,station=?,operator=?,engineer=?WHERE id=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, source);
				preparedStmt.setString(2, area);
				preparedStmt.setString(3, gridCode);
				preparedStmt.setString(4, voltage);
				preparedStmt.setString(5, station);
				preparedStmt.setString(6, operator);
				preparedStmt.setString(7, engineer);
				preparedStmt.setInt(8, Integer.parseInt(id));
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Updated successfully";
			} catch (Exception e) {
				output = "Error while updating the item.";
				System.err.println(e.getMessage());
			}
			return output;
		}

		public String deleteItem(String itemID) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
				// create a prepared statement
				String query = "delete from powergrid where id=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(itemID));
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Deleted successfully";
			} catch (Exception e) {
				output = "Error while deleting the item.";
				System.err.println(e.getMessage());
			}
			return output;
		}
	}

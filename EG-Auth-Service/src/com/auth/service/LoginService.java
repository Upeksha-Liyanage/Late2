package com.auth.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.auth.database.DBConnection;
import com.auth.model.Employee;
import com.auth.model.Client;
import com.auth.model.User;

public class LoginService {

//Employee login
	public static Employee loginDoc(String email, String password) {
		Employee employee = null;
		try {

			Connection con = DBConnection.connect();

			String getSql = "SELECT * FROM employee WHERE email = ? AND password = ? ";

			PreparedStatement ps_getEmployeeDetails = con.prepareStatement(getSql);
			ps_getEmployeeDetails.setString(1, email);
			ps_getEmployeeDetails.setString(2, password);

			ResultSet rs = ps_getEmployeeDetails.executeQuery();

			while (rs.next()) {

				employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return employee;
	}

//Client login
	public static Client loginPat(String email, String password) {
		Client client = null;
		try {

			Connection con = DBConnection.connect();

			String getSql = "SELECT * FROM client WHERE email = ? AND password = ? ";

			PreparedStatement ps_getClientDetails = con.prepareStatement(getSql);
			ps_getClientDetails.setString(1, email);
			ps_getClientDetails.setString(2, password);

			ResultSet rs = ps_getClientDetails.executeQuery();

			while (rs.next()) {

				client = new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return client;
	}

}

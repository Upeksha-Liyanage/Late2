package com.auth.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.auth.model.Employee;
import com.auth.service.EmployeeService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/employees")
public class EmployeeResource {

	EmployeeService employeeService = new EmployeeService();

	/* User type - Employee */

//Insert Employee
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertEmployee(@FormParam("type") String type, 
							   @FormParam("email") String email,
							   @FormParam("password") String password,
							   @FormParam("contactNo") String contactNo,
							   @FormParam("firstName") String firstName,
							   @FormParam("lastName") String lastName,
							   @FormParam("NIC") String NIC) {
		String output = employeeService.insertEmployee(type, email, password, contactNo, firstName, lastName, NIC);
		return output;
	}

//Read Employee list	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readEmployees() {
		return employeeService.readEmployee();
	}

//Employee update
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateEmployee(String employeeData) {

		JsonObject employeeObject = new JsonParser().parse(employeeData).getAsJsonObject();

		String ID = employeeObject.get("ID").getAsString();
		String contactNo = employeeObject.get("contactNo").getAsString();

		String output = employeeService.updateEmployee(ID, contactNo);
		return output;
	}

//Delete existing Employee
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteEmployee(String employeeData) {

		Document doc = Jsoup.parse(employeeData, "", Parser.xmlParser());

		String id = doc.select("id").text();

		String output = employeeService.deleteEmployee(id);
		return output;
	}

//	Retrieve  single Employee
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Employee getEmployeeDetails(@PathParam("id") String id) {
		Employee employee = employeeService.getEmployeeDetails(id);
		return employee;
	}

}

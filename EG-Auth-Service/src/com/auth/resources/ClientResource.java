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

import com.auth.model.Client;
import com.auth.service.ClientService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/clients")
public class ClientResource {

	ClientService clientService = new ClientService();

	/* User type - Client */

//Insert Client
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertClient(@FormParam("type") String type, @FormParam("email") String email,
			@FormParam("password") String password, @FormParam("contactNo") String contactNo,
			@FormParam("firstName") String firstName, @FormParam("lastName") String lastName,
			@FormParam("electroId") String electroId,
			@FormParam("NIC") String NIC, @FormParam("address") String address) {
		String output = clientService.insertClient(type, email, password, contactNo, firstName, lastName,
				electroId, NIC, address);
		return output;
	}

//Read Client list	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readClients() {
		return clientService.readClient();
	}

//Client update
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateClient(String clientData) {

		JsonObject clientObject = new JsonParser().parse(clientData).getAsJsonObject();

		String ID = clientObject.get("ID").getAsString();
		String contactNo = clientObject.get("contactNo").getAsString();
		String address = clientObject.get("address").getAsString();

		String output = clientService.updateClient(ID, contactNo, address);
		return output;
	}

//Delete existing Client
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteClient(String clientData) {

		Document doc = Jsoup.parse(clientData, "", Parser.xmlParser());

		String id = doc.select("id").text();

		String output = clientService.deleteClient(id);
		return output;
	}

//Retrieve  single doctor
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Client getClientDetails(@PathParam("id") String id) {
		Client client = clientService.getClientDetails(id);
		return client;
	}

}

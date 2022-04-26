package com;

import model.Grids;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/PowerGrids")
public class PowerGridService {

	Grids itemObj = new Grids();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return itemObj.readItems();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(
			@FormParam("source") String source,
			@FormParam("area") String area,
			@FormParam("gridCode") String gridCode, 
			@FormParam("output") String voltage,
	        @FormParam("station") String station,
            @FormParam("operator") String operator,
            @FormParam("engineer") String engineer)
	{
		String output = itemObj.insertItem(source, area, gridCode, voltage, station, operator, engineer);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData) {
		// Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		// Read the values from the JSON object
		String id = itemObject.get("id").getAsString();
		String source = itemObject.get("source").getAsString();
		String area = itemObject.get("area").getAsString();
		String gridCode = itemObject.get("gridCode").getAsString();
		String voltage = itemObject.get("output").getAsString();
		String station = itemObject.get("station").getAsString();
		String operator = itemObject.get("operator").getAsString();
		String engineer = itemObject.get("engineer").getAsString();
		String output = itemObj.updateItem(id, source, area, gridCode, voltage, station, operator, engineer);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String itemID = doc.select("id").text();
		String output = itemObj.deleteItem(itemID);
		return output;
	}

}

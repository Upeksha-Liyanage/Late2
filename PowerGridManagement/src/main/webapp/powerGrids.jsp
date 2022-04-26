<%@page import="model.Grids"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
//Insert item---------------------------------
if (request.getParameter("source") != null) {
	Grids itemObj = new Grids();
	String stsMsg = itemObj.insertItem(request.getParameter("source"), request.getParameter("area"),
	request.getParameter("gridCode"), request.getParameter("output"), request.getParameter("station"),
	request.getParameter("operator"), request.getParameter("engineer"));
	session.setAttribute("statusMsg", stsMsg);
}
//Delete item----------------------------------
if (request.getParameter("id") != null) {
	Grids itemObj = new Grids();
	String stsMsg = itemObj.deleteItem(request.getParameter("id"));
	session.setAttribute("statusMsg", stsMsg);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Power Grids Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>
</head>
<body>
	<h1>Power Grids Management</h1>
	<form id="formItem" name="formItem" method="post"
		action="powerGrids.jsp">
		Power source: <input id="source" name="source" type="text"
			class="form-control form-control-sm"> <br> Power area: <input
			id="area" name="area" type="text"
			class="form-control form-control-sm"> <br> Grid Code: <input
			id="gridCode" name="gridCode" type="text"
			class="form-control form-control-sm"> <br> Voltage: <input
			id="output" name="output" type="text"
			class="form-control form-control-sm"> <br> Station: <input
			id="station" name="station" type="text"
			class="form-control form-control-sm"> <br> Operator: <input
			id="operator" name="operator" type="text"
			class="form-control form-control-sm"> <br> Engineer: <input
			id="engineer" name="engineer" type="text"
			class="form-control form-control-sm"> <br> <input
			id="btnSave" name="btnSave" type="button" value="Save"
			class="btn btn-primary"> <input type="hidden"
			id="hidItemIDSave" name="hidItemIDSave" value="">
	</form>
	<%
	out.print(session.getAttribute("statusMsg"));
	%>
	4
	<br>
	<%
	Grids itemObj = new Grids();
	out.print(itemObj.readItems());
	%>
</body>
</html>
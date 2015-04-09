<%--
  Created by IntelliJ IDEA.
  User: eugene
  Date: 11/24/2014
  Time: 12:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>OWL Expense Manager:Categories Page</title>
		<link rel="stylesheet" type="text/css" href="styles.css">
	</head>
	<body>
		<ul>
			<li><a href="/wem/redirect.jsp">Main Page</a></li>
			<li><a href="/wem/ReportServlet">Reports</a></li>
			<li><a href="/wem/CategoriesServlet">Categories</a></li>
		</ul>
		<div class="error">
			<%=(request.getAttribute("errorMessage")==null)? "":request.getAttribute("errorMessage")%>
		</div>
		<h4> Categories List: </h4>
		<table border="1px">
			<%=request.getAttribute("categoriesList")%>
		</table>
		<br>
		<table border = "0" valign="center" cellpadding="2px">
			<tr>
				<form
				action="/wem/CategoriesServlet"
				method="post">
					<td>
						<INPUT type="submit" name="Submit" value="Add">
					</td>
					<td>
						<input type="text" name="newCategory" size="18" maxlength="20">
				</form>
					</td>
			</tr>
			<tr>
				<form
				action="/wem/CategoriesServlet"
				method="post">
					<td>
						<INPUT type="submit" name="Submit" value="Remove">
					</td>
					<td>
						<%=request.getAttribute("categoriesSelect")%>
				</form>
					</td>
			</tr>
		</table>
	</body>
</html>

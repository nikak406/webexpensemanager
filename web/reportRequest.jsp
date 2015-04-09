<%--
  Created by IntelliJ IDEA.
  User: eugene
  Date: 11/24/2014
  Time: 12:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>WebExpenseManager: Report Request Page</title>
		<link rel="stylesheet" type="text/css" href="styles.css">
	</head>
	<body>
		<ul>
			<li><a href="/wem/redirect.jsp">Main Page</a></li>
			<li><a href="/wem/ReportServlet">Reports</a></li>
			<li><a href="/wem/CategoriesServlet">Categories</a></li>
		</ul>
		<div class="error">
            <br><%=(request.getAttribute("errorMessage")==null)? "":request.getAttribute("errorMessage")%>
		</div>
		<h4>Specify the report you need:</h4>
		<table border="0" cellpadding="8px">
			<tr>
				<td>
					<form name="report"
        			action="/wem/ReportServlet"
		        	method="post"  >
				        <input type="checkbox" name="Expenses" value="Expenses" checked>Expenses<br>
				        <input type="checkbox" name="Incomes" value="Incomes">Incomes<br>
		        </td>
	            <td>
	                <input type="radio" name="time" value="today">Today's expenses<br>
	                <input type="radio" name="time" value="week">This week expenses<br>
	                <input type="radio" name="time" value="month">This month expenses<br>
	                <input type="radio" name="time" value="specify">Start&End Date:<br>
                    <br>
		            <INPUT type="date" name="startDate" align="center" value = "<%=request.getAttribute("earlydate")%>"><br>
		            <INPUT type="date" name="endDate" align="center" value = "<%=request.getAttribute("today")%>">
	            </td>
                <td>
	                <%=request.getAttribute("categoryCheckboxes")%>
		        </td>
            </tr>
        </table>
        <br>
    	<input type="submit" value="Get Report">
                    </form>
    </body>
</html>

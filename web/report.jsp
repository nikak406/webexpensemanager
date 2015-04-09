<%--
  Created by IntelliJ IDEA.
  User: eugene
  Date: 11/24/2014
  Time: 9:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
	    <title>
            Web Expense Manager: Report Page
        </title>
	    <link rel="stylesheet" type="text/css" href="styles.css">
    </head>
    <body>
        <ul>
            <li><a href="/wem/redirect.jsp">Main Page</a></li>
            <li><a href="/wem/ReportServlet">Reports</a></li>
            <li><a href="/wem/CategoriesServlet">Categories</a></li>
        </ul>
        <div class="error">
            <br> <%=(request.getAttribute("errorMessage")==null)? "":request.getAttribute("errorMessage")%>
        </div>
        <h4>Report</h4>
        <table border = "1px" cellpadding="8px" valign="center">
            <%=request.getAttribute("filteredListTable")%>
            <tr>
                <td>
                    Total
                </td>
                <td>
                    <%=request.getAttribute("total")%>
                </td>
                <form name="removeExpense" action="/wem/ExpenseServlet">
                <td colspan="2" align="center">
                    <input type="submit" value="Remove №">
                </td>
                <td align="center">
                    <input type="text" name="number" size="2" maxlength="5">
                </td>
            </tr>
        </table>
    </body>
</html>

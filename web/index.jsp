<%--
  Created by IntelliJ IDEA.
  User: eugene
  Date: 11/19/2014
  Time: 11:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=windows-1251" language="java"  %>
<html>
  <head>
	<title>OWL Expense Manager: Main Page</title>
	<link rel="stylesheet" type="text/css" href="styles.css">
  </head>
  <body >
    <ul>
	    <li><a href="/wem/redirect.jsp">Main Page</a></li>
	    <li><a href="/wem/ReportServlet">Reports</a></li>
	    <li><a href="/wem/CategoriesServlet">Categories</a></li>
    </ul>
    <div class="error">
        <br><%=(request.getAttribute("errorMessage")==null)? "":request.getAttribute("errorMessage")%>
    </div>
	<div>
		<h4>Quick reference</h4>
		<table border = "1px" cellpadding="8px">
			<tr>
                <td>
                    Today's Expense
                </td>
				<td>
                    <%=request.getAttribute("todaysExpenses")%>
                </td>
			<tr>
                <td>
                    This Week Expense
                </td>
				<td>
                    <%=request.getAttribute("thisWeekExpenses")%>
                </td>
            </tr>
			<tr>
                <td>
                    This Month Expense
                </td>
				<td>
                    <%=request.getAttribute("thisMonthExpenses")%>
                </td>
            </tr>
			<tr>
                <td>
                    This Month Balance
                </td>
				<td>
                    <%=request.getAttribute("thisMonthBalance")%>
                </td>
            </tr>
			<tr>
                <td>
                    Total Balance
                </td>
				<td>
                    <%=request.getAttribute("totalBalance")%>
                </td>
            </tr>
		</table>
		<h4>Last 5 expenses</h4>
		<table border = "1px" cellpadding="8px">
			<%=request.getAttribute("last5")%>
			<tr>
                <td colspan="2">
                    <form name="removeExpense" action="/wem/ExpenseServlet">
                </td>
                <td colspan="2" align="center">
					<input type="submit" value="Remove ¹">
                </td>
                <td align="center">
				    <input type="text" name="number" size="3" maxlength="5">
			    </td>
            </tr>
		</table>
        <br>
		</form>
			<FORM name="addExpense"
				method="post"
				action="/wem/WebExpenseManager"  >
				<h4>Quick add expense</h4>
				<table border = "0" cellpadding="8px">
					<tr>
						<td>
                            Expense UAH:
                        </td>
						<td>
                            <INPUT type="text" name="addExpenseValue" size="15" maxlength="10">
                        </td>
                    </tr>
					<tr>
						<td>
                            Expense description:
                        </td>
						<td>
                            <INPUT type="text" name="addExpenseDescription" size="15" maxlength="100">
                        </td>
                    </tr>
					<tr>
						<td>
                            Date:
                        </td>
						<td>
                            <INPUT type="date" name="addDate" value="<%=request.getAttribute("today")%>">
                        </td>
                    </tr>
					<tr>
						<td>
                            Category:
                        </td>
						<td>
                            <%=request.getAttribute("selectCategory")%>
                        </td>
                    </tr>
					<tr>
						<td>
                            <INPUT type="checkbox" name="addIncome" value="checked">Income
                        </td>
						<td>
                            <INPUT type="submit" name="Submit" value="Add">
                        </td>
					</tr>
				</table>
		    </FORM>
		    <br>
	    </div>
    </body>
</html>

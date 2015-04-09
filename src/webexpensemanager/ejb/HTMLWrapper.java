package webexpensemanager.ejb;

import webexpensemanager.logic.Expense;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Locale;
import javax.ejb.Stateless;

@Stateless
public class HTMLWrapper {

	public String wrapSelect(Iterable<String> iterable, String name) {
		StringBuilder res = new StringBuilder("<select name=\"");
		res.append(name).append("\">\n");
		for (String s : iterable) {
			res.append("<option value=\"")
					.append(s)
					.append("\">")
					.append(s)
					.append("</option>\n");
		}
		res.append("</select>");
		return res.toString();
	}

	public String wrapSelect(String[] array, String name) {
		return wrapSelect(Arrays.asList(array), name);
	}

	public String wrapExpenseTable(Iterable<Expense> expenseCollection) {
		String EXPENSE_TABLE_HEAD = "<tr><th>№</th><th>Expense</th><th>Description</th>" +
				"<th>Date</th><th>Category</th></tr>";
		String TABLE_LAST_LINE = "</td>\n</tr>\n";
		String TABLE_FIRST_LINE = "<tr>\n<td>";
		String TABLE_NEW_LINE = "</td>\n<td>";
		StringBuilder res = new StringBuilder(EXPENSE_TABLE_HEAD);
		DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault());
		for (Expense expense : expenseCollection) {
			res.append(TABLE_FIRST_LINE)
					.append(expense.getNumber())
					.append(TABLE_NEW_LINE)
					.append(expense.getValue())
					.append(TABLE_NEW_LINE)
					.append(expense.getDescription())
					.append(TABLE_NEW_LINE)
					.append(df.format(expense.getDate()))
					.append(TABLE_NEW_LINE)
					.append(expense.getCategory())
					.append(TABLE_LAST_LINE);
		}
		return res.toString();
	}

	public String wrapTable(Iterable<String> iterable){
		StringBuilder res = new StringBuilder();
		for (String s : iterable) {
			res.append("<tr><td>")
					.append(s)
					.append("</td></tr>\n");
		}
		return res.toString();
	}

	public String wrapTable(String[] array){
		return wrapTable(Arrays.asList(array));
	}

	public String wrapCategoryCheckBoxes(String[] array){
		StringBuilder res = new StringBuilder();
		for (String s : array) {
			res.append("<input type=\"checkbox\" name=\"")
					.append(s)
					.append("\" checked=\"checked\">")
					.append(s)
					.append("<br>\n");
		}
		return res.toString();
	}

	public String wrapList(Iterable<String> iterable){
		StringBuilder s = new StringBuilder("<ol type=\"square\">");
		for(String str:iterable)
			s.append("<li>")
					.append(str)
					.append("</li>");
		s.append("</ol>");
		return s.toString();
	}

	public String wrapList(String[] arr){
		return wrapList(Arrays.asList(arr));
	}
}

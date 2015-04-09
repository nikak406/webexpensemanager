package webexpensemanager.logic;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Entity
@XmlRootElement
public class Expense {

	public static double total(Iterable<Expense> expenseCollection){
		double res = 0;
		if (expenseCollection == null)
			return res;
		for(Expense expense : expenseCollection){
			res += expense.getValue();
		}
		return res;
	}

	private double value;

	private String description;

	@Temporal(TemporalType.DATE)
	private Date date;

	@ManyToOne
	@JoinColumn(name = "NAME")
	private Category category;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int number;

	Expense (double value, String description, Date date, String category, int number){
		this.value = value;
		this.description = description;
		this.date = date;
		this.category = Categories.findCategory(category);
		this.number = number;
	}

	public Expense (double value, String description, Date date, String category){
		this.value = value;
		this.description = description;
		this.date = date;
		this.category = Categories.findCategory(category);
		this.number = 0;
	}

	public Expense(){
		value = 0.0;
		description = null;
		date = new Date();
		category = null;
	}

	public String getDescription(){
		return description;
	}

	public double getValue() {
		return value;
	}

	public Date getDate(){
		return date;
	}

	public String getCategory(){
		return category.getName();
	}

	public int getNumber() {
		return number;
	}

	public void setValue(double value){
		this.value = value;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public void setDate(Date date){
		this.date = date;
	}

	public void setNumber(int number){
		this.number = number;
	}

	public void setCategory(String category){
		this.category = Categories.findCategory(category);
	}

	public String toString(){
		return "Expense " + getNumber()
				+ " Value " + getValue()
				+ " Description " + getDescription()
				+ " Category " + getCategory()
				+ " Date " + getDate();
	}
}

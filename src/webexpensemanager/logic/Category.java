package webexpensemanager.logic;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Category{

	@Id
	private String name;

	Category(String name){
		this.name = name;
	}

	public Category(){
		name=null;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String toString(){
		return getName();
	}

}

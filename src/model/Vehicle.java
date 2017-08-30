package model;

import java.util.EnumSet;
import java.util.Set;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Vehicle {
	private EnumSet<Category> categorySet;

	/** In pounds */
	private double costPrice;

	private String vin;
	private StringProperty regNo;
	private String make;
	private StringProperty model;

	public Vehicle() {

	}
	
	/**
	 * 
	 * @param vin
	 * @param regNo
	 * @param cost
	 * @param categories
	 * @param model
	 * @param make
	 */
	
	public Vehicle(String vin, String regNo, int cost, Set<Category> categories, String model, String make) {
		this.vin = vin;
		this.regNo = new SimpleStringProperty(regNo);
		this.costPrice = cost;
		this.categorySet = EnumSet.noneOf(Category.class);
		this.categorySet.addAll(categories);
		this.model = new SimpleStringProperty(model);
		this.make = make;if( make.length() > 10 )
	{
	    throw new RuntimeException("User name too long");}
	}

	/**
	 * 
	 * Getters and setters
	 */
	public String getVin() {
		return this.vin;
	}

	public String getRegNo() {
		return this.regNo.get();

	}

	public StringProperty getRegNoProperty() {
		return regNo;
	}

	public double getCostPrice() {
		return this.costPrice;
	}

	public boolean isCategory(Category category) {
		return this.categorySet.contains(category);
	}

	public String getmake() {
		return this.make;
	}

	public String getmodel() {
		return this.model.get();
	}

	public StringProperty getmodelProperty() {
		return model;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public void setregNo(String regNo) {
		this.regNo.set(regNo);

	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public void setmake(String make) {
		this.make = make;
	}

	public void setmodel(String model) {
		this.model.set(model);
	}

	public void setCategory(Set<Category> categories) {
		this.categorySet.addAll(categories);
	}

	public String getCategoryString() {
		String result = "";
		boolean first = true;

		if (categorySet != null)
		{
			for (Category category : categorySet) {
				if (first) {
					first = false;
				} else {
					result += ", ";
				}
				result += category.name();
			}
		}
		
	return result;

}}

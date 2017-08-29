package model;

import java.util.UUID;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Staff {
	private UUID id;
	private StringProperty name;
	private StringProperty phoneNo;
	private String email;
	private String address;
	
	public Staff(){
		this.id = UUID.randomUUID();
	}
	
	public Staff(UUID id, String name, String phoneNo, String email, String address) {
		this.id = id;
		this.name = new SimpleStringProperty(name);
		this.phoneNo = new SimpleStringProperty(phoneNo);
		this.email = email;
		this.address = address;
	}
	
	public UUID getid() {
		return this.id;
	}
	
	public StringProperty getnameProperty(){
		return name;
	}
	
	public StringProperty getphoneNoProperty(){
		return phoneNo;
	}
	
	public String getname() {
		return this.name.get();
	}
	
	public String getphoneNo() {
		return this.phoneNo.get();
	}
	
	public String getemail() {
		return this.email;
	}
	
	public String getaddress() {
		return this.address;
	}
	
	public void setid(UUID id){
		 this.id = id;
		}
	
	public void setname(String name){
		this.name.set(name);
	
	}
	
	public void setphoneNo(String phoneNo){
		this.phoneNo.set(phoneNo);
	
	}
	
	public void setemail(String email){
		 this.email = email;
		}
	
	public void setaddress(String address){
		 this.address = address;
		}

	public enum Role {
		SALES_TEAM_MANAGER, SALES_MEMBER

	}

}

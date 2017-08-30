package model;

public class Transaction {
	
	private String purchase;
	private String sale;
	private double commission;
	private double staffCommission;
	private double cost;

	public Transaction(String purchase, String sale, double commission, double staffCommission, double cost) {
		this.setPurchase(purchase);
		this.setSale(sale);
		this.setCommission(commission);
		this.setStaffCommission(staffCommission);
		this.setCost(cost);
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public String getPurchase() {
		return purchase;
	}

	public void setPurchase(String purchase) {
		this.purchase = purchase;
	}

	public String getSale() {
		return sale;
	}

	public void setSale(String sale) {
		this.sale = sale;
	}

	public double getStaffCommission() {
		return staffCommission;
	}

	public void setStaffCommission(double staffCommission) {
		this.staffCommission = staffCommission;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
}

package databean;

public class CustomerData {
	
	private int customer_id;
	private String username;
	private String name;
	private String lastTransactionDay;
	private String cash;
	private String available;
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastTransactionDay() {
		return lastTransactionDay;
	}
	public void setLastTransactionDay(String lastTransactionDay) {
		this.lastTransactionDay = lastTransactionDay;
	}
	public String getCash() {
		return cash;
	}
	public void setCash(String cash) {
		this.cash = cash;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	
	

}

package databean;

import java.util.Date;

public class FundPriceHistory {

	private int fund_id;
	private Date price_date;
	private int price;
	public int getFund_id() {
		return fund_id;
	}
	public void setFund_id(int fund_id) {
		this.fund_id = fund_id;
	}
	public Date getPrice_date() {
		return price_date;
	}
	public void setPrice_date(Date price_date) {
		this.price_date = price_date;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}

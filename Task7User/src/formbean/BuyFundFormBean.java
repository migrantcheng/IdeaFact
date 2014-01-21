
package formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class BuyFundFormBean {

	private String ticker;
	private long amount;
	private String button;
	private String fundName;
	private String fundPrice;
	
	public BuyFundFormBean(HttpServletRequest request) {
		ticker = request.getParameter("ticker");
		button = request.getParameter("button");
		fundName = request.getParameter("fundName");
		fundPrice = request.getParameter("fundPrice");
		
		try {
			amount = (long)(Double.parseDouble(request.getParameter("amount")) * 100);
		} catch(Exception e) {
			amount = - 1;
		}
	}
	public long getAmount() {
		return amount;
	}
	public String getTicker() {
		return ticker;
	}
	public String getFundName() {
		return fundName;
	}
	public String getFundPrice() {
		return fundPrice;
	}
	public String getButton() {
		return button;
	}
	public void setButton(String button) {
		this.button = button;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (amount <= 0) {
			errors.add("Amount is not correct.");
		}
		
		if (ticker.length() == 0) {
			errors.add("Ticker cannot be empty.");
		}
		
		if (errors.size() > 0) {
			return errors;
		}

		return errors;
	}
	
	public boolean isPresent()   { return button != null; }
}

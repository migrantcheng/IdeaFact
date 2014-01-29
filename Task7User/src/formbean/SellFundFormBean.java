
package formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class SellFundFormBean {

	private String ticker;
	private long amount;
	private String button;
	private String fundName;
	private String fundPrice;
	private String[] temp;
	private String stringAmount;
	
	public SellFundFormBean(HttpServletRequest request) {
		ticker = request.getParameter("ticker");
		button = request.getParameter("button");
		fundName = request.getParameter("fundName");
		fundPrice = request.getParameter("fundPrice");
		stringAmount = request.getParameter("amount");
		if (request.getParameter("amount") != null) {
			temp = ((String)request.getParameter("amount")).split("\\.");
		}
		
		try {
			amount = (long)((Double.parseDouble(request.getParameter("amount")) * 1000));
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
		
		if (stringAmount.length() > 15) {
			errors.add("Limitation of maximum length for amount exceeded (15 digit)");
		}
		
		if (temp.length > 1 && temp[1].length() > 3) {
			errors.add("Only support 3 decimal places for amount");
		}
		
		if (amount <= 0) {
			errors.add("Amount is not correct");
		}
		
		if (ticker == null || ticker.length() == 0) {
			errors.add("Ticker cannot be empty");
		}

		return errors;
	}
	
	public boolean isPresent()   { return button != null; }
}

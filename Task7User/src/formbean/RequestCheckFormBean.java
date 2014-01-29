
package formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class RequestCheckFormBean {

	private long amount;
	private String button;
	private String[] temp;
	private String stringAmount;
	
	public RequestCheckFormBean(HttpServletRequest request) {
		button = request.getParameter("button");
		stringAmount = request.getParameter("amount");
		if (request.getParameter("amount") != null) {
			temp = ((String)request.getParameter("amount")).split("\\.");
		}
		
		try {
			amount = (long)((Double.parseDouble(request.getParameter("amount")) * 100));
		} catch(Exception e) {
			amount = - 1;
		}
	}
	public long getAmount() {
		return amount;
	}
	public String getButton() {
		return button;
	}
	public void setButton(String button) {
		this.button = button;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (stringAmount.length() > 10) {
			errors.add("Limitation of maximum length for amount exceeded (10 digit)");
		}
		
		if (temp.length > 1 && temp[1].length() > 2) {
			errors.add("Only support 2 decimal places for amount");
		}

		if (amount <= 0) {
			errors.add("Amount is not correct");
		}
		
		if (amount > 100000000) {
			errors.add("Maximum amount is 1000000.00");
		}

		return errors;
	}
	
	public boolean isPresent()   { return button != null; }
}


package formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class RequestCheckFormBean {

	private long amount;
	private String button;
	
	public RequestCheckFormBean(HttpServletRequest request) {
		button = request.getParameter("button");
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

		if (amount <= 0) {
			errors.add("Amount is not correct.");
		}

		return errors;
	}
	
	public boolean isPresent()   { return button != null; }
}

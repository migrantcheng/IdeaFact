package formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class DepositCheckForm {
	private String username;
	private long amount;
	private String button;
	
	public DepositCheckForm(HttpServletRequest request){
		username = request.getParameter("username");
		String amountStr = request.getParameter("amount");
		try{
			amount = (long)(Double.parseDouble(amountStr)*100);
		}catch(Exception e){
			amount = -1;
		}

		button = request.getParameter("button");
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getButton() {
		return button;
	}

	public void setButton(String button) {
		this.button = button;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (username == null || username.length() == 0) {
			errors.add("User Name is required");
		}
		
		if (amount<=0 || amount>100000000) {
			errors.add("Please input valid amount. Amount should be 0.01 - 1,000,000.00");
		}

		return errors;
	}
	
	public boolean isPresent()   { return button != null; }

}

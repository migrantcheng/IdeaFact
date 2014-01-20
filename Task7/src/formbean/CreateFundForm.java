package formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class CreateFundForm {
	private String fundName;
	private String ticker;
	private String button;
	
	public CreateFundForm(HttpServletRequest request){
		fundName = request.getParameter("fundName");
		ticker = request.getParameter("ticker");
		button = request.getParameter("button");
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public String getButton() {
		return button;
	}

	public void setButton(String button) {
		this.button = button;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (fundName == null || fundName.length() == 0) {
			errors.add("Fund Name is required");
		}
		
		if (ticker == null || ticker.length() == 0) {
			errors.add("Fund Symbol is required");
		}

		return errors;
	}
	
	public boolean isPresent()   { return button != null; }

}

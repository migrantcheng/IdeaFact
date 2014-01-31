package formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class CreateFundForm {
	private String fundName;
	private String ticker;
	private String button;

	private StringValidator validator;
	
	public CreateFundForm(HttpServletRequest request){
		fundName = request.getParameter("fundName");
		if(fundName!=null){
			fundName = fundName.trim();
		}
		ticker = request.getParameter("ticker");
		if(ticker!=null){
			ticker = ticker.trim();
			ticker = ticker.toUpperCase();
		}
		button = request.getParameter("button");
		
		validator = new StringValidator();
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
			errors.add("Fund Ticker is required");
		}
		
		if(!errors.isEmpty()){
			return errors;
		}
		
		if(fundName.length()>25){
			errors.add("The length of fund Name should be less than 25.");
		}
		
		if(ticker.length()!=4){
			errors.add("The length of ticker should be 4!");
		}
		
		if(!validator.validate(fundName)){
			errors.add("The Fund Name contains invalid chacter.");
		}
		
		if(!validator.validate(ticker)){
			errors.add("The Ticker contains invalid chacter.");
		}

		return errors;
	}
	
	public boolean isPresent()   { return button != null; }

}

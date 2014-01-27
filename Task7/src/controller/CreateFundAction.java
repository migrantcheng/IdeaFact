package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.interfaces.FundDAO;
import dao.Model;
import databean.Employee;
import databean.Fund;
import formbean.CreateEAccountForm;
import formbean.CreateFundForm;

public class CreateFundAction extends Action {
	
	private FundDAO fundDAO;
	
	public CreateFundAction(Model model){
		fundDAO = model.getFundDAO();
	}

	@Override
	public String getName() {
		return "createFund.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// Set up error list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
	        // Load the form parameters into a form bean
	        CreateFundForm form = new CreateFundForm(request);
	        
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "createFund.jsp";
	        }
	
	        // Check for any validation errors
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	        	return "createFund.jsp";
	        }
	
	        Fund fund = new Fund();
	        fund.setName(form.getFundName());
	        fund.setSymbol(form.getTicker());
	        
	        fundDAO.createFund(fund);
	
//			request.setAttribute("messages","Created Fund : "+fund.getName()+" Symbol: "+fund.getSymbol());
			List<String> messages = new ArrayList<String>();
			messages.add("Created Fund : "+fund.getName()+" Symbol: "+fund.getSymbol());
			request.setAttribute("messages",messages);
			return "createFund.jsp";
        }catch (Exception e) {
        	errors.add(e.getMessage());
        	return "createFund.jsp";
		}
	}

}

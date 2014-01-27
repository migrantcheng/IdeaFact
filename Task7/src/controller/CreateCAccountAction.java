package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.Model;
import dao.interfaces.CustomerDAO;
import databean.Customer;
import formbean.CreateCAccountForm;

public class CreateCAccountAction extends Action {
	
	private CustomerDAO customerDAO;
	
	public CreateCAccountAction(Model model){
		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {
		return "createCAccount.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// Set up error list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
	        // Load the form parameters into a form bean
	        CreateCAccountForm form = new CreateCAccountForm(request);
	        
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "createCustomerAccount.jsp";
	        }
	
	        // Check for any validation errors
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	        	return "createCAccount.do";
	        }
	
			Customer customer = new Customer();
			customer.setUsername(form.getUsername());
			customer.setPassword(form.getPassword());
			customer.setFirstname(form.getFirstName());
			customer.setLastname(form.getLastName());
			customer.setAddr_line1(form.getAddr_line1());
			if(form.getAddr_line2()==null || form.getAddr_line2().length()==0){
				customer.setAddr_line2("");
			}else{
				customer.setAddr_line2(form.getAddr_line2());
			}
			customer.setCity(form.getCity());
			customer.setState(form.getState());
			customer.setZip(form.getZip());
			customer.setCash(0);
			customer.setAvailable(0);
	
			customerDAO.create(customer);
	
			List<String> messages = new ArrayList<String>();
			messages.add("Created Account for "+customer.getUsername());
			request.setAttribute("messages",messages);
			return "createCustomerAccount.jsp";
        }catch (Exception e) {
        	errors.add(e.getMessage());
        	return "createCAccount.do";
		}
	}

}

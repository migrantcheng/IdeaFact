package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.Model;
import dao.interfaces.CustomerDAO;
import databean.Customer;
import formbean.ChangePwdFormBean;

public class ChangePwdAction extends Action {
	
	private CustomerDAO customerDAO;
	
	public ChangePwdAction(Model model){
		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {
		return "changePwd.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// Set up error list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
	        // Load the form parameters into a form bean
	        ChangePwdFormBean form = new ChangePwdFormBean(request);
	        
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "changeCustomerPassword.jsp";
	        }
	
	        // Check for any validation errors
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "changeCustomerPassword.jsp";
	        }
	
			Customer customer = (Customer) request.getSession().getAttribute("customer");
			synchronized (customerDAO) {
				customer = customerDAO.read(customer.getUsername());
			}
			if(!customer.getPassword().equals(form.getOldPwd())){
				errors.add("Old Password is not correct!");
				return "changeCustomerPassword.jsp";
			}
	
			// Change the password
			customer.setPassword(form.getNewPassword());
			synchronized (customerDAO) {
				customerDAO.update(customer);
			}
	
			request.getSession().setAttribute("messages","Password changed for "+customer.getUsername());
	        return "myaccount.do";
        }catch (Exception e) {
        	errors.add(e.getMessage());
			return "changeCustomerPassword.jsp";
		}
	}

}

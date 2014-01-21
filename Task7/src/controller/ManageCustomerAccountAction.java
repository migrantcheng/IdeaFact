package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.Model;
import dao.interfaces.CustomerDAO;
import databean.Customer;
import databean.Fund;
import formbean.CreateFundForm;

public class ManageCustomerAccountAction extends Action {
	private CustomerDAO customerDAO;
	
	public ManageCustomerAccountAction(Model model){
		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {
		return "manageCustomerAccount.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// Set up error list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
	        List<Customer> customers = new ArrayList<Customer>();
	        customers = customerDAO.getCustomerList();
	        
	        request.setAttribute("customers", customers);
	        
	        return "manageCustomerAccount.jsp";
        }catch (Exception e) {
        	errors.add(e.getMessage());
			return "error.jsp";
		}
	}

}

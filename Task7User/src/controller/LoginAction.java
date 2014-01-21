
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.interfaces.CustomerDAO;
import dao.Model;
import databean.Customer;
import formbean.LoginForm;

public class LoginAction extends Action {


//	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory.getInstance(LoginForm.class);
	
	private CustomerDAO customerDAO;
	
	public LoginAction(Model model){
		customerDAO = model.getCustomerDAO();
	}
	
	@Override
	public String getName() {
		return "login.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
        	LoginForm form = new LoginForm(request);
	        request.setAttribute("form",form);

	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "login.jsp";
	        }

	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "login.jsp";
	        }

	        // Look up the user
	        Customer user = customerDAO.read(form.getUsername());
	        
	        if (user == null) {
	            errors.add("Username not found");
	            return "login.jsp";
	        }

	        // Check the password
	        if (!(user.getPassword()).equals(form.getPassword())) {
	            errors.add("Incorrect password");
	            return "login.jsp";
	        }
	
	        // Attach (this copy of) the user bean to the session
	        HttpSession session = request.getSession();
	        session.setAttribute("customer",user);

	        return "changePwd.do";
        } catch (Exception e) {
        	errors.add(e.getMessage());
			return "login.jsp";
		}
	}

}

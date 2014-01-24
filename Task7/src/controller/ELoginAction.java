
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.interfaces.CustomerDAO;
import dao.interfaces.EmployeeDAO;
import dao.Model;
import databean.Customer;
import databean.Employee;
import formbean.LoginForm;

public class ELoginAction extends Action {
	
	private EmployeeDAO employeeDAO;
	
	public ELoginAction(Model model){
		employeeDAO = model.getEmployeeDAO();
	}
	
	@Override
	public String getName() {
		return "employeeLogin.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
//        	request.setAttribute("userList",userDAO.getAllUsers());
        	
        	LoginForm form = new LoginForm(request);
	        request.setAttribute("form",form);

	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "employeeLogin.jsp";
	        }

	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	        	return "employeeLogin.jsp";
	        }

	        // Look up the user
	        Employee employee = employeeDAO.read(form.getUsername());
	        
	        if (employee == null) {
	            errors.add("Username not found");
	            return "employeeLogin.jsp";
	        }

	        // Check the password
	        if (!(employee.getPassword()).equals(form.getPassword())) {
	            errors.add("Incorrect password");
	            return "employeeLogin.jsp";
	        }
	
	        // Attach (this copy of) the user bean to the session
	        HttpSession session = request.getSession();
	        session.setAttribute("employee",employee);

	        return "manageCustomerAccount.do";
        } catch (Exception e) {
        	errors.add(e.getMessage());
        	return "employeeLogin.jsp";
		}
	}

}

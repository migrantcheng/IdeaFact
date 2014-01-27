package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.interfaces.EmployeeDAO;
import dao.Model;
import databean.Employee;
import formbean.ChangePwdFormBean;
import formbean.CreateEAccountForm;

public class CreateEAccountAction extends Action {

	private EmployeeDAO employeeDAO;
	
	public CreateEAccountAction(Model model){
		employeeDAO = model.getEmployeeDAO();
	}
	
	@Override
	public String getName() {
		return "createEAccount.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// Set up error list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
	        // Load the form parameters into a form bean
	        CreateEAccountForm form = new CreateEAccountForm(request);
	        
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "createEmployeeAccount.jsp";
	        }
	
	        // Check for any validation errors
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	        	return "createEmployeeAccount.jsp";
	        }
	
			Employee employee = new Employee();
			employee.setUsername(form.getUsername());
			employee.setPassword(form.getPassword());
			employee.setFirstname(form.getFirstName());
			employee.setLastname(form.getLastName());
	
			// Change the password
        	employeeDAO.createAccount(employee);
	
//			request.setAttribute("messages","Created Account for "+employee.getUsername());
			
			List<String> messages = new ArrayList<String>();
			messages.add("Created Account for "+employee.getUsername());
			request.setAttribute("messages",messages);
			return "createEAccount.do";
        }catch (Exception e) {
        	errors.add(e.getMessage());
        	return "createEAccount.do";
		}
	}

}

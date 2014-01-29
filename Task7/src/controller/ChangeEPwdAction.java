package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.Model;
import dao.interfaces.EmployeeDAO;
import databean.Employee;
import formbean.ChangePwdFormBean;

public class ChangeEPwdAction extends Action {
	
	private EmployeeDAO employeeDAO;
	
	public ChangeEPwdAction(Model model){
		employeeDAO = model.getEmployeeDAO();
	}

	@Override
	public String getName() {
		return "changeEPwd.do";
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
	            return "changeEmployeePassword.jsp";
	        }
	
	        // Check for any validation errors
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "changeEmployeePassword.jsp";
	        }
	
			Employee employee = (Employee) request.getSession().getAttribute("employee");
			employee = employeeDAO.read(employee.getUsername());
			if(!employee.getPassword().equals(form.getOldPwd())){
				errors.add("Old Password not correct!");
				return "changeEmployeePassword.jsp";
			}
	
			// Change the password
        	employeeDAO.updatePassword(employee.getUsername(), form.getNewPassword());
	
//			request.setAttribute("message","Password changed for "+employee.getUsername());
			List<String> messages = new ArrayList<String>();
			messages.add("Password changed for "+employee.getUsername());
			request.setAttribute("messages",messages);
			return "changeEmployeePassword.jsp";
        }catch (Exception e) {
        	errors.add(e.getMessage());
			return "changeEmployeePassword.jsp";
		}
	}

}

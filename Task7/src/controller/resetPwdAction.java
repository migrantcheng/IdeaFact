package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.Model;
import dao.interfaces.EmployeeDAO;
import databean.Employee;
import formbean.ChangePwdFormBean;

public class resetPwdAction {
	private EmployeeDAO employeeDAO;
	
	public resetPwdAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}
	
	public String getName() {
		return "resetPwd.do";
	}
	
	public String perform(HttpServletRequest request) {
		// set up error list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {
			ChangePwdFormBean form = new ChangePwdFormBean(request);
			
			if (!form.isPresent()) {
				return "resetCustomerPassword.jsp";
			}
			
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "resetCustomerPassword.jsp";
			}
			
			Employee employee = (Employee) request.getSession().getAttribute("employee");
			employeeDAO.updatePassword(employee.getUsername(), form.getNewPassword());
			
			request.setAttribute("message", "Password reset for " + employee.getUsername());
			return "resetCustomerPassword.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "resetCustomerPassword.jsp";
		}
	}

}

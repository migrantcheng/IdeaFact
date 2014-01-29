package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.Model;
import dao.interfaces.CustomerDAO;
import dao.interfaces.EmployeeDAO;
import databean.Customer;
import databean.Employee;
import formbean.ChangePwdFormBean;

public class resetPwdAction extends Action{
	private EmployeeDAO employeeDAO;
	private CustomerDAO customerDAO;
	
	public resetPwdAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
		customerDAO = model.getCustomerDAO();
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
				request.setAttribute("customer_username",request.getParameter("customer_username"));
				return "resetCustomerPassword.jsp";
			}
			
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "resetCustomerPassword.jsp";
			}
			
			String username = request.getParameter("username");
			Customer customer = customerDAO.read(username);
			customer.setPassword(form.getNewPassword());
			customerDAO.update(customer);
			
//			Employee employee = (Employee) request.getSession().getAttribute("employee");
//			employeeDAO.updatePassword(employee.getUsername(), form.getNewPassword());
			
			request.setAttribute("message", "Password reset for " + customer.getUsername());
			return "manageCustomerAccount.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "resetCustomerPassword.jsp";
		}
	}

}

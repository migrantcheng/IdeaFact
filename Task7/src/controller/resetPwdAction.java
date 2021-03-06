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
import formbean.ResetPwdFormBean;

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
			ResetPwdFormBean form = new ResetPwdFormBean(request);
			request.setAttribute("customer_username",request.getParameter("customer_username"));
			
			if (!form.isPresent()) {
				return "resetCustomerPassword.jsp";
			}
			
			form.setOldPwd("");
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "resetCustomerPassword.jsp";
			}
			
			String username = request.getParameter("username");
			Customer customer;
			synchronized(customerDAO){
			customer = customerDAO.read(username);
			if(customer==null){
				throw new Exception("Username not found!");
			}
			customer.setPassword(form.getNewPassword());
			customerDAO.update(customer);
			}
			
//			Employee employee = (Employee) request.getSession().getAttribute("employee");
//			employeeDAO.updatePassword(employee.getUsername(), form.getNewPassword());
			
			
			List<String> messages = new ArrayList<String>();
			messages.add("Password reset for " + customer.getUsername());
			request.setAttribute("messages",messages);
			return "resetCustomerPassword.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "resetCustomerPassword.jsp";
		}
	}

}

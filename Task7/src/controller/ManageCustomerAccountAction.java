package controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.Model;
import dao.interfaces.CustomerDAO;
import dao.interfaces.TransactionDAO;
import databean.Customer;
import databean.CustomerData;
import databean.Fund;
import databean.Transaction;
import formbean.CreateFundForm;

public class ManageCustomerAccountAction extends Action {
	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;
	private DecimalFormat dfNumberCash = new DecimalFormat("#,##0.00");
	private DecimalFormat dfNumberFund = new DecimalFormat("#,##0.000");
	private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
	public ManageCustomerAccountAction(Model model){
		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
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
	        synchronized(customerDAO){
	        	customers = customerDAO.getCustomerList();
	        }
	        Iterator<Customer> customerIter = customers.iterator();
	        List<CustomerData> customerData = new ArrayList<CustomerData>();
	        while(customerIter.hasNext()){
	        	Customer customer = customerIter.next();
	        	CustomerData data = new CustomerData();
	        	data.setCustomer_id(customer.getCustomer_id());
	        	data.setUsername(customer.getUsername());
	        	data.setName(customer.getFirstname()+" "+customer.getLastname());
	        	data.setCash(dfNumberCash.format((double)customer.getCash()/100));
	        	data.setAvailable(dfNumberCash.format((double)customer.getAvailable()/100));
	        	
	        	
	        	Transaction transaction;
	        	synchronized(transactionDAO){
	        		transaction = transactionDAO.getLastTransaction(customer.getCustomer_id());
	        	}
	        	if(transaction!=null){
	        	data.setLastTransactionDay(sdf.format(transaction.getExecute_date()));
	        	}else{
	        		data.setLastTransactionDay("-");
	        	}
	        	
//	        	data.setLastTransactionDay("-");
	        	customerData.add(data);
	        }
	        
	        request.setAttribute("customers", customerData);
	        
	        return "manageCustomerAccount.jsp";
        }catch (Exception e) {
        	errors.add(e.getMessage());
        	return "manageCustomerAccount.do";
		}
	}

}

package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.Model;
import dao.interfaces.FundDAO;
import databean.Customer;
import databean.Fund;

public class TransitionDayAction extends Action {
	
	private FundDAO fundDAO;
	
	public TransitionDayAction(Model model){
		fundDAO = model.getFundDAO();
	}

	@Override
	public String getName() {
		return "transitionDay.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// Set up error list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
        	String button = request.getParameter("button");
        	if(button==null){
        		//refresh page
        		List<Fund> funds = fundDAO.getFundList();
        		request.setAttribute("funds", funds);
        		
        		return "transitionDay.jsp";
        		
        	}else{
        		//process transition day process
        		
        		
        		return "success.jsp";
        	}
        }catch (Exception e) {
        	errors.add(e.getMessage());
			return "error.jsp";
		}
	}

}

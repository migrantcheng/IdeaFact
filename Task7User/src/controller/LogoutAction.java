package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.Model;


public class LogoutAction extends Action {

	public LogoutAction(Model model){
		
	}
	
	@Override
	public String getName() {
		return "logout.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
        session.setAttribute("customer",null);
        
		request.setAttribute("messages","You have successfully logged out");
        return "login.jsp";
	}

}

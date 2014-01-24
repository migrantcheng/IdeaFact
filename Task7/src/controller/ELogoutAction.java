package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.Model;


public class ELogoutAction extends Action {

	public ELogoutAction(Model model){
		
	}
	
	@Override
	public String getName() {
		return "employeeLogout.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
        session.setAttribute("employee",null);
        
        return "employeeLogin.do";
	}

}

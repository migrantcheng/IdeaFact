package controller;

import javax.servlet.http.HttpServletRequest;

public class LoginAction extends Action {

	@Override
	public String getName() {
		return "login.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		return "login.jsp";
	}

}
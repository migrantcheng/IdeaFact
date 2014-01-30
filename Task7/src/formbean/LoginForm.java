/*  
 * 08-600
 * JAVA J2EE Programming
 * Homework #9
 * Haoran Cheng
 * haoranc@andrew.cmu.edu
 * Dec 3, 2013
 *  
 */

package formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.HibernateUtil;


public class LoginForm {
	private String username;
	private String password;
	private String button;
	
	public LoginForm(HttpServletRequest request) {
		username = request.getParameter("username");
    	if(username!=null){
    		username.replace("[<>\";]", "");
    	}
    	password  = request.getParameter("password");
    	button 	  = request.getParameter("button");
    }

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getButton() {
		return button;
	}

	public void setButton(String button) {
		this.button = button;
	}

	public boolean isPresent()   { return button != null; }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (button != null && !button.equals("signin")) {
        	errors.add("Session expired, please login again.");
        	return errors;
        }
        if (username == null || username.length() == 0) errors.add("Username is required");
        if (password == null || password.length() == 0) errors.add("Password is required");
//        if (button == null) errors.add("Button is required");
        
        password = HibernateUtil.getMD5(password);

        if (errors.size() > 0) return errors;

//        if (!button.equals("Login") && !button.equals("Register")) errors.add("Invalid button");
		
        return errors;
    }

}

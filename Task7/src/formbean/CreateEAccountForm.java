package formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.HibernateUtil;

public class CreateEAccountForm {
	private String username;
	private String password;
	private String confirmPassword;
	private String firstName;
	private String lastName;
	private String button;
	
	public CreateEAccountForm(HttpServletRequest request){
		username = request.getParameter("username");
		password = request.getParameter("password");
		confirmPassword = request.getParameter("confirmPassword");
		firstName = request.getParameter("firstName");
		lastName = request.getParameter("lastName");
		button = request.getParameter("button");
		
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getButton() {
		return button;
	}

	public void setButton(String button) {
		this.button = button;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (password == null || password.length() == 0) {
			errors.add("New Password is required");
		}
		
		if (confirmPassword == null || confirmPassword.length() == 0) {
			errors.add("Confirm Password is required");
		}
		
		if (username == null || username.length() == 0) {
			errors.add("Username is required");
		}
		
		if (firstName == null || firstName.length() == 0) {
			errors.add("FirstName is required");
		}
		
		if (lastName == null || lastName.length() == 0) {
			errors.add("LastName is required");
		}
		
		if(!(password.length()>=6 && password.length()<=16)){
			errors.add("The length of password should be 6 ~ 16.");
		}
		
		if(username.length()>16){
			errors.add("The length of username should be less than 16.");
		}
		
		if(firstName.length()>30){
			errors.add("The length of firstname should be less than 30.");
		}
		
		if(lastName.length()>30){
			errors.add("The length of lastname should be less than 30.");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		if (!password.equals(confirmPassword)) {
			errors.add("Passwords do not match");
		}

		password = HibernateUtil.getMD5(password);
		return errors;
	}
	
	public boolean isPresent()   { return button != null; }

}

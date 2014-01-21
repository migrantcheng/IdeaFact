package formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
			errors.add("Confirm Pwd is required");
		}
		
		if (username == null || username.length() == 0) {
			errors.add("username is required");
		}
		
		if (firstName == null || firstName.length() == 0) {
			errors.add("firstName is required");
		}
		
		if (lastName == null || lastName.length() == 0) {
			errors.add("lastName is required");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		if (!password.equals(confirmPassword)) {
			errors.add("Passwords do not match");
		}

		return errors;
	}
	
	public boolean isPresent()   { return button != null; }

}

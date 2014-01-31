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
	
	private StringValidator validator;
	
	public CreateEAccountForm(HttpServletRequest request){
		username = request.getParameter("username");
		if(username!=null){
			username = username.trim();
		}
		password = request.getParameter("password");
		if(password!=null){
			password = password.trim();
		}
		confirmPassword = request.getParameter("confirmPassword");
		if(confirmPassword!=null){
			confirmPassword = confirmPassword.trim();
		}
		firstName = request.getParameter("firstName");
		if(firstName!=null){
			firstName = firstName.trim();
		}
		lastName = request.getParameter("lastName");
		if(lastName!=null){
			lastName = lastName.trim();
		}
		button = request.getParameter("button");
		
		validator = new StringValidator();
		
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
			errors.add("First name is required");
		}
		
		if (lastName == null || lastName.length() == 0) {
			errors.add("Last name is required");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		
		if(username.length()>16){
			errors.add("The length of username should be less than 16.");
		}

		if(!(password.length()>=6 && password.length()<=16)){
			errors.add("The length of password should be 6 ~ 16.");
		}
		
		if(firstName.length()>30){
			errors.add("The length of firstname should be less than 30.");
		}
		
		if(lastName.length()>30){
			errors.add("The length of lastname should be less than 30.");
		}
		
		if(!validator.validate(username)){
			errors.add("The username contains invalid chacter.");
		}
		
		if(!validator.validate(firstName)){
			errors.add("The First Name contains invalid chacter.");
		}
		
		if(!validator.validate(lastName)){
			errors.add("The Last Name contains invalid chacter.");
		}
		
		if (!password.equals(confirmPassword)) {
			errors.add("Passwords do not match");
		}

		password = HibernateUtil.getMD5(password);
		return errors;
	}
	
	public boolean isPresent()   { return button != null; }

}

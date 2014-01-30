package formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.HibernateUtil;

public class CreateCAccountForm {
	private String username;
	private String password;
	private String confirmPassword;
	private String firstName;
	private String lastName;
	private String addr_line1;
	private String addr_line2;
	private String city;
	private String state;
	private String zip;
	private String button;
	
	public CreateCAccountForm(HttpServletRequest request){
		username = request.getParameter("username");
		password = request.getParameter("password");
		confirmPassword = request.getParameter("confirmPassword");
		firstName = request.getParameter("firstname");
		lastName = request.getParameter("lastname");
		addr_line1 = request.getParameter("addrline1");
		addr_line2 = request.getParameter("addrline2");
		city = request.getParameter("city");
		state = request.getParameter("state");
		zip = request.getParameter("zip");
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


	public String getAddr_line1() {
		return addr_line1;
	}


	public void setAddr_line1(String addr_line1) {
		this.addr_line1 = addr_line1;
	}


	public String getAddr_line2() {
		return addr_line2;
	}


	public void setAddr_line2(String addr_line2) {
		this.addr_line2 = addr_line2;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getZip() {
		return zip;
	}


	public void setZip(String zip) {
		this.zip = zip;
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
			errors.add("Username is required");
		}
		
		if (firstName == null || firstName.length() == 0) {
			errors.add("First name is required");
		}
		
		if (lastName == null || lastName.length() == 0) {
			errors.add("Last name is required");
		}
		
		if (addr_line1 == null || addr_line1.length() == 0) {
			errors.add("Address line 1 is required");
		}
		
		if (city == null || city.length() == 0) {
			errors.add("City is required");
		}
		
		if (state == null || state.length() == 0) {
			errors.add("State is required");
		}
		
		if (zip==null || zip.length()==0) {
			errors.add("Zip is required");
		}
		
		if (zip.length()!=5) {
			errors.add("The length of zip should be 5.");
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

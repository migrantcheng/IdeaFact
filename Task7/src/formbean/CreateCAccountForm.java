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
	
	private StringValidator validator;
	
	public CreateCAccountForm(HttpServletRequest request){
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
		firstName = request.getParameter("firstname");
		if(firstName!=null){
			firstName = firstName.trim();
		}
		lastName = request.getParameter("lastname");
		if(lastName!=null){
			lastName = lastName.trim();
		}
		addr_line1 = request.getParameter("addrline1");
		if(addr_line1!=null){
			addr_line1 = addr_line1.trim();
		}
		addr_line2 = request.getParameter("addrline2");
		if(addr_line2!=null){
			addr_line2 = addr_line2.trim();
		}
		city = request.getParameter("city");
		if(city!=null){
			city = city.trim();
		}
		state = request.getParameter("state");
		if(state!=null){
			state = state.trim();
		}
		zip = request.getParameter("zip");
		if(zip!=null){
			zip = zip.trim();
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
		
		
		
		if (errors.size() > 0) {
			return errors;
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
		
		if (!password.equals(confirmPassword)) {
			errors.add("Passwords do not match");
		}
		
		if(addr_line1.length()>50){
			errors.add("The length of address line 1 should be less than 50.");
		}
		if (addr_line2 != null) {
			if (addr_line2.length() > 50) {
				errors.add("The length of address line 2 should be less than 50.");
			}
			if(!validator.validate(addr_line2)){
				errors.add("The address line 2 contains invalid chacter.");
			}
		}
		
		if(city.length()>50){
			errors.add("The length of city should be less than 50.");
		}
		
		if (zip.length()!=5) {
			errors.add("The length of zip should be 5.");
		}
		

		if(!validator.validate(username)){
			errors.add("The Username contains invalid chacter.");
		}

		if(!validator.validate(firstName)){
			errors.add("The First Name contains invalid chacter.");
		}

		if(!validator.validate(lastName)){
			errors.add("The Last Name contains invalid chacter.");
		}
		

		if(!validator.validate(addr_line1)){
			errors.add("The address line 1 contains invalid chacter.");
		}
		if(!validator.validate(city)){
			errors.add("City contains invalid chacter.");
		}
		if(!validator.validate(zip)){
			errors.add("Zip contains invalid chacter.");
		}
		
		password = HibernateUtil.getMD5(password);

		return errors;
	}
	
	public boolean isPresent()   { return button != null; }

}

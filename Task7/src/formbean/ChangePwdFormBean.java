
package formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.HibernateUtil;

public class ChangePwdFormBean {

	private String oldPwd;
	private String confirmPassword;
	private String newPassword;
	private String button;
	
	public ChangePwdFormBean(HttpServletRequest request) {
		oldPwd = request.getParameter("oldPwd");
		if(oldPwd!=null){
			oldPwd = oldPwd.trim();
		}
		newPassword = request.getParameter("newPwd");
		if(newPassword!=null){
			newPassword = newPassword.trim();
		}
		
		confirmPassword = request.getParameter("confirmPwd");
		if(confirmPassword!=null){
			confirmPassword = confirmPassword.trim();
		}
		button = request.getParameter("button");
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getButton() {
		return button;
	}
	public void setButton(String button) {
		this.button = button;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (oldPwd == null || oldPwd.length() == 0) {
			errors.add("Old Password is required");
		}
		
		if (newPassword == null || newPassword.length() == 0) {
			errors.add("New Password is required");
		}
		
		if (confirmPassword == null || confirmPassword.length() == 0) {
			errors.add("Confirm Password is required");
		}
		
		if(!(newPassword.length()>=6 && newPassword.length()<=16)){
			errors.add("The length of password should be 6 ~ 16.");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		if (!newPassword.equals(confirmPassword)) {
			errors.add("Passwords do not match");
		}
		
		oldPwd = HibernateUtil.getMD5(oldPwd);
		newPassword = HibernateUtil.getMD5(newPassword);
		confirmPassword = HibernateUtil.getMD5(confirmPassword);

		return errors;
	}
	
	public boolean isPresent()   { return button != null; }
}

package dao.interfaces;

import databean.Employee;

public interface EmployeeDAO {

	Employee read(String username);

	void updatePassword(String username, String newPassword);

	void createAccount(Employee employee);

}

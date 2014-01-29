package dao.interfaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import databean.Customer;
import databean.Position;
import databean.Transaction;

public interface TransactionDAO {

	void create(Transaction transaction);

	Transaction getLastTransaction(int customer_id);

	List<Transaction> getAll(int customer_id);

	List<Transaction> getPending(int customer_id);

	ArrayList<String> buyFund(Customer attribute, Transaction transaction, long amount);

	ArrayList<String> sellFund(Position position, Transaction transaction, long amount);

}

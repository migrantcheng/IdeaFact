package dao.interfaces;

import java.util.List;

import databean.Transaction;

public interface TransactionDAO {

	void create(Transaction transaction);

	Transaction getLastTransaction(int customer_id);

	List<Transaction> getAll(int customer_id);

}

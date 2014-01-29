package dao.interfaces;

import java.util.Date;
import java.util.List;

import databean.Transaction;

public interface TransactionDAO {

	void create(Transaction transaction);

	List<Transaction> getDepositList();
	
	List<Transaction> getWithdrawList();
	
	List<Transaction> getBuyList();
	
	List<Transaction> getSellList();
	
	List<Transaction> getPendingList();

	void update(Transaction pendingItem);
	
	Date getLastTransitionDay();

	List<Transaction> getPending(int customer_id);

	Transaction getLastTransaction(int customer_id);

	List<Transaction> getAll(int customer_id);

}

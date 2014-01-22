package dao.interfaces;

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

}

package dao.interfaces;

import java.util.List;

import databean.Fund;

public interface FundDAO {

	Fund read(String ticker);
	
	Fund read(int fund_id);
	
	List<Fund> getAllFunds();
}

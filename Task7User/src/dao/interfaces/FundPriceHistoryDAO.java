package dao.interfaces;

import java.util.List;

import databean.FundPriceHistory;

public interface FundPriceHistoryDAO {

	long getLatestPrice(int id);
	
	List<FundPriceHistory> read(int fundId);
	
}
package dao.interfaces;

import java.util.Date;

import databean.Fund;
import databean.FundPriceHistory;

public interface FundPriceHistoryDAO {

	void createFundPriceHistory(FundPriceHistory temp);

	FundPriceHistory readByIdDate(int fund_id, Date date);

	long getLatestPrice(int fund_id);

}

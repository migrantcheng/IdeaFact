package dao.interfaces;

import java.util.Date;
import java.util.List;

import databean.Fund;

public interface FundDAO {

	void createFund(Fund fund);

	List<Fund> getFundList();

	Fund read(int fund_id);

	Fund readByTicker(String ticker);

	Fund readByName(String fundName);

}

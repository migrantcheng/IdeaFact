package dao.interfaces;

import databean.Fund;

public interface FundDAO {

	Fund read(String ticker);
	
	Fund read(int fund_id);

}

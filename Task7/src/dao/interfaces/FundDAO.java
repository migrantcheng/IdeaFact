package dao.interfaces;

import java.util.Date;
import java.util.List;

import databean.Fund;

public interface FundDAO {

	void createFund(Fund fund);

	List<Fund> getFundList();

}

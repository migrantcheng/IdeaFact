package dao.interfaces;

import java.util.ArrayList;
import java.util.List;

import databean.Fund;
import databean.Position;

public interface PositionDAO {

	public List<Position> getAllPositions(int customer_id);
	
	Position read(int fund_id, int customer_id);
	
	ArrayList<String> spend(Position position, long amount);
}

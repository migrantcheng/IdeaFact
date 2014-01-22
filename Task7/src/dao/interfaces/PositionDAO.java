package dao.interfaces;

import databean.Position;


public interface PositionDAO {

	Position read(int fund_id, int customer_id);

	void delete(Position position);

	void update(Position position);

	void create(Position position);


}

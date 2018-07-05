package infrastructure;

public class MonService {

	DatabaseDriver databaseDriver;
	
	public void findAll() {
		databaseDriver = new PostgreSDriver();
		databaseDriver.getConnection();
		
		//reqûete sql;
	}
}

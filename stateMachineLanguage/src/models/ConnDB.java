package models;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;


public class ConnDB implements AutoCloseable{

	private static final String URI = "bolt://localhost:7687";
	private static final String USER = "neo4j";
	private static final String PWD = "1234";
	
	private static final Driver driver = GraphDatabase.driver( URI, AuthTokens.basic( USER, PWD ) );
	
	public static Session getSession() {
		return driver.session();
	}
	
	public static Driver getdDriver(){
		return driver;
	}
	
	private ConnDB() {
		
	}
	
	
	@Override
	public void close() throws Exception {
		driver.close();
		
	}

	
}

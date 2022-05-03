package domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;


public class DBConnFactory {
	
	private final static String URI_JDBC = "jdbc:neo4j:bolt://localhost:7687";
	private final static String URI_BOLT = "bolt://localhost:7687";
	private final static String USER= "neo4j";
	private final static String PWD = "1234";
	
	private static Connection conn = null;
	
	static Configuration configuration = new Configuration.Builder()
		    .uri(URI_BOLT)
		    .credentials(USER, PWD)
		    .verifyConnection(true)
		    .build();

	private static SessionFactory sessionFactory = new SessionFactory(configuration, "domain");
	
	private static DBConnFactory factory = new DBConnFactory();
	
	
	private DBConnFactory() {
	}
	
	public static void createSessionFactory(String dbName) {
		configuration = new Configuration.Builder()
			    .uri(URI_BOLT)
			    .credentials(USER, PWD)
			    .database(dbName)
			    .verifyConnection(true)
			    .build();
		
		sessionFactory = new SessionFactory(configuration, "domain");
	}
	
	public static DBConnFactory getInstance() {
        return factory;
    }
	
	public Session getSession() throws SQLException{
		return sessionFactory.openSession();
	}

	
	
	public static Connection getConn() throws SQLException {
		if(conn == null) {
			conn = DriverManager.getConnection(
					URI_JDBC+"/?user="+USER+",password="+ PWD+",scheme=basic");
		}
		return conn;
	}

}

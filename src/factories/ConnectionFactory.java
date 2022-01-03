package factories;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class ConnectionFactory {

	private static final String CONNECTION_CONFIGURATION_FILE = "./resources/database-config.properties";
	private static final String CONNECTION_DRIVER 			  = "connection.driver";
	private static final String CONNECTION_HOSTNAME 	 	  = "connection.hostname";
	private static final String CONNECTION_PORT 	  		  = "connection.port";
	private static final String CONNECTION_DATABASE 		  = "connection.database";
	private static final String CONNECTION_USER 	  		  = "connection.user";
	private static final String CONNECTION_PASSWORD 		  = "connection.password";
	
	private static ThreadLocal<Connection> sessionThread;
	
	private static Connection conn;
	
	static {
		sessionThread = new ThreadLocal<Connection>();
	}
	
	public static Connection getConnection() {
		if(sessionThread.get() == null) {
			sessionThread.set(openConnection());
		}
		return sessionThread.get();
	}

	private static Connection openConnection() {
		conn = null;
		try {
			Class.forName(getConnectionDriver());
			 conn = DriverManager.getConnection(getConnectionUrl(), getConnectionUser(), getConnectionPassowrd());
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}		
		return conn;
	}
	
	private static String getConnectionDriver() {
		String result = null;
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(CONNECTION_CONFIGURATION_FILE));
			result =  properties.getProperty(CONNECTION_DRIVER);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static String getConnectionUser() {
		String result = null;
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(CONNECTION_CONFIGURATION_FILE));
			result =  properties.getProperty(CONNECTION_USER);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static String getConnectionHostname() {
		String result = null;
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(CONNECTION_CONFIGURATION_FILE));
			result =  properties.getProperty(CONNECTION_HOSTNAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private static String getConnectionPassowrd() {
		String result = null;
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(CONNECTION_CONFIGURATION_FILE));
			result =  properties.getProperty(CONNECTION_PASSWORD);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private static String getConnectionDatabase() {
		String result = null;
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(CONNECTION_CONFIGURATION_FILE));
			result =  properties.getProperty(CONNECTION_DATABASE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private static String getConnectionPort() {
		String result = null;
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(CONNECTION_CONFIGURATION_FILE));
			result =  properties.getProperty(CONNECTION_PORT);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private static String getConnectionUrl() {
		String result = "";
		result += "jdbc:mysql://"+ getConnectionHostname() +":"+ getConnectionPort() +"/"+ getConnectionDatabase();
		return result;
	}

	
}
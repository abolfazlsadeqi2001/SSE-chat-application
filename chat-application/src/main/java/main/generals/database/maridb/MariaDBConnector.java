package main.generals.database.maridb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import main.generals.config.reader.ConfigReader;

// TODO write document
public class MariaDBConnector {
	private static final String CONNECTION_ADDRESS = "jdbc:mariadb://localhost:3306/chat";
	private static final String USER = ConfigReader.getDatabaseUserName();
	private static final String PASS = ConfigReader.getDatabasePassword();
	
	public static void set(String query) throws Exception {
		Connection con = DriverManager.getConnection(CONNECTION_ADDRESS,USER,PASS);
		Statement st = con.createStatement();
		st.executeUpdate(query);
	}
	
	public static ResultSet get(String query) throws Exception {
		Connection con = DriverManager.getConnection(CONNECTION_ADDRESS,USER,PASS);
		Statement st = con.createStatement();
		ResultSet set = st.executeQuery(query);
		return set;
	}
}

package main.generals.config.reader;

import java.io.File;
import java.io.FileInputStream;

/**
 * this class just read some datas from a file that contains some important data
 * like user name and password of a database<br>
 * <b>if the config file path is changed the
 * {@link main.generals.config.reader.ConfigReader#PATH_TO_CONFIG_FILE} must be changed
 * to that
 * 
 * @author abolfazlsadeqi2001
 *
 */
public class ConfigReader {
	// general variables
	private static final String PATH_TO_CONFIG_FILE = "/home/abolfazlsadeqi2001/.configs";
	private static final String PARAMETERS_SPLITER = "\\|";
	private static String body = "";
	// database indexes definer
	private static final int INDEX_OF_DATABASE_USER_PARAMETER = 0;
	private static final int INDEX_OF_DATABASE_PASSWORD_USER_PARAMETER = 1;
	/**
	 * read the config file and placed its content into body variable
	 */
	private static void readBody() {
		try {
			File configFile = new File(PATH_TO_CONFIG_FILE);
			FileInputStream fis = new FileInputStream(configFile);
			byte[] fisBytes = fis.readAllBytes();
			for (byte b : fisBytes) {
				body += (char) b;
			}
			fis.close();
		} catch (Exception e) {
			body = "";
		}
	}
	
	/**
	 * this method return an specific index of parameter defined into config file
	 * @param index
	 * @return
	 */
	private static String getIndex(int index) {
		if(body.equals(""))
			readBody();
		return body.split(PARAMETERS_SPLITER)[index];
	}
	
	/**
	 * return user name of database to access
	 * @return
	 */
	public static String getDatabaseUserName() {
		return getIndex(INDEX_OF_DATABASE_USER_PARAMETER);
	}
	
	/**
	 * return password of database to access
	 * @return
	 */
	public static String getDatabasePassword() {
		return getIndex(INDEX_OF_DATABASE_PASSWORD_USER_PARAMETER);
	}
}

package genericLibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This class contains reusable methods to read data from properties file
 * @author Srinivas Yadav
 */

public class PropertiesUtility {
private Properties property;
	
	/**
	 * This method initializes properties file
	 * @param filePath
	 */
	public void propertiesInit(String filePath) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		property = new Properties();
		try {
			property.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method fetches data from properties file based on the key passed
	 * @param key
	 * @return
	 */
	public String readFromProperties(String key) {
		return property.getProperty(key);
	}
}

package environmentConfig;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import commons.GlobalConstants;

public class PropertiesConfig {
	private Properties properties;
	private final String propertyFilePath = GlobalConstants.ENVIRONMENT_CONFIG_PATH;

	public static PropertiesConfig getProperties(String serverName) {
		return new PropertiesConfig(serverName);
	}

	public PropertiesConfig(String serverName) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(String.format(propertyFilePath, serverName)));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration properties not found at " + propertyFilePath);
		}
	}

	public String getUserUrl() {
		String userUrl = properties.getProperty("userUrl");
		if (userUrl != null)
			return userUrl;
		else
			throw new RuntimeException("User URL not specified in the properties file.");
	}

	public String getAdminUrl() {
		String adminUrl = properties.getProperty("adminUrl");
		if (adminUrl != null)
			return adminUrl;
		else
			throw new RuntimeException("User URL not specified in the properties file.");
	}
}

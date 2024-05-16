package commons;

import java.io.File;

public class GlobalConstants {
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String JAVA_VERSION = System.getProperty("java.version");
	public static final String FILE_SEPARATOR = File.separator;

	public static final long SHORT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 30;

	public static final String UPLOAD_PATH = PROJECT_PATH + FILE_SEPARATOR + "uploadFiles" + FILE_SEPARATOR;
	public static final String ENVIRONMENT_CONFIG_PATH = PROJECT_PATH + FILE_SEPARATOR + "environmentConfig" + FILE_SEPARATOR + "%s.properties";
}

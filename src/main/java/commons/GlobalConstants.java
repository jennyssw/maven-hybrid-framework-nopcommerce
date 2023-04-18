package commons;

import java.io.File;

public class GlobalConstants {
	// System Infor
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String JAVA_VERSION = System.getProperty("java.version");
	public static final String FILE_SEPARATOR = File.separator;

	// Wait Infor
	public static final long SHORT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 30;

	// Download / Upload file
	public static final String UPLOAD_PATH = getFolderSeparator("uploadFiles");
	public static final String DOWNLOAD_PATH = getFolderSeparator("downloadFiles");

	// Data test
	public static final String DATA_PATH = getFolderSeparator("dataTest");

	// Retry Case Failed
	public static final int RETRY_NUMBER = 3;

	// Browser Logs
	public static final String BROWSER_LOG_PATH = getFolderSeparator("browserLogs");
	public static final String BROWSER_EXTENSION_PATH = getFolderSeparator("browserExtensions");

	// HTML Report Folder
	public static final String EXTENT_PATH = getFolderSeparator("htmlExtent");

	public static String getFolderSeparator(String folderName) {
		return PROJECT_PATH + FILE_SEPARATOR + folderName + FILE_SEPARATOR;
	}
}

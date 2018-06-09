package es.tappex.utils;

public class Constants {
	//END BATCH PROCESSING
	public static final int END_OK = 0;
	public static final int END_KO = -1;
	
	//HELP COMMANDS
	public static final String HELP_1 = "-h";
	public static final String HELP_2 = "-help";
	
	//FILE TYPE (ASCII FILES ONLY ACCEPTED)
	public static final String text = "text";
	
	//FILE INPUT PARAMETERS
	public static final String resources_file = "launcher.properties";
	public static final String resources_key = "key";
	public static final String resources_url = "url";
	public static final String resources_response = "response";
	public static final String resources_outputfile = "file";
	
	//RESPONSES TYPES
	public static final String APP_HTML = "application/html";
	public static final String APP_XML = "application/xml";
	public static final String APP_JSON = "application/json";
	
	//RESPONSE TAGS
	public static final String TAG_HTML_EVAL = "<input type=\"hidden\" id=\"inneractive-error\" value=\"";
	
	public static final String TAG_XML_EVAL = "Error=\"";
	
	//RESPONSES
	public static final String OK_CONTENT = "OK";
	public static final String OK_CONTENT_1 = "House Ad";
	
	public static final int OK_CONTENT_INT = 200;
	
	public static final String OK_CONTENT_TEST = "OK_CONTENTTEST";
	
	public static final String OK_NOCONTENT = "OK_NOCONTENT";
	public static final int OK_NOCONTENT_INT = 204;
	
	public static final String KO_BADREQUEST = "KO_BADREQUEST";
	public static final int KO_BADREQUEST_INT = 404;
	
	public static final String OTHER = "OTHER";
	
	public static final String KO_SERVERFAULT = "KO_SERVERFAULT";
	public static final int KO_SERVERFAULT_INT = 500;
	
	public static final String KO_CODE = "KO";
	
}

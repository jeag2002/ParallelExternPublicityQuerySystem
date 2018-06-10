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
	public static final String resources_parser = "parser";
	public static final String resources_validation = "validation";
	
	//PARSER TYPES
	public static final String PARSER_TYPE_1 = "parser_1";
	
	//RESPONSES TYPES
	public static final String APP_HTML = "application/html";
	public static final String APP_XML = "application/xml";
	public static final String APP_JSON = "application/json";
	
	//VALIDATION TYPES
	public static final String HTML_VAL = "html";
	public static final String XML_1_VAL = "xml_1";
	public static final String XML_2_VAL = "xml_2";
	public static final String JSON_VAL = "json";
	
	//EVALUATION TAGS
	public static final String TAG_HTML_EVAL = "<input type=\"hidden\" id=\"inneractive-error\" value=\"";
	public static final String TAG_XML_EVAL = "Error=\"";
	
	//RESPONSES
	public static final String OK_CONTENT = "OK";
	public static final String OK_CONTENT_1 = "House Ad";
	
	public static final int OK_CONTENT_INT = 200;
	
	public static final String OK_CONTENT_TEST = "TEST";
	
	public static final String OK_NOCONTENT = "BAD CONTENT";
	public static final int OK_NOCONTENT_INT = 204;
	
	public static final String KO_BADREQUEST = "BAD REQUEST";
	public static final int KO_BADREQUEST_INT = 404;
	
	public static final String OTHER = "OTHER";
	
	public static final String KO_SERVERFAULT = "SERVER FAULT";
	public static final int KO_SERVERFAULT_INT = 500;
	
	public static final String KO_CODE = "KO";
	
}

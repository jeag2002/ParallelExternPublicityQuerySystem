package es.tappex.json;

import es.tappex.utils.Constants;

/**
 * JSON Parser Factory
 * @author Usuario
 *
 */

public class JSONFactory {
	
	public static ParserSchema getParserSchema(String parserType){
		
		//Parser JSON to GET parameters for HTML/XML banners
		if (parserType.trim().equalsIgnoreCase(Constants.PARSER_TYPE_1)) {
			return new JSONMAPToParameters();
		}else {
			return new JSONMAPToParameters();
		}
		
	}

}

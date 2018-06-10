package es.tappex.validations;

import es.tappex.utils.Constants;

/**
 * Factory of validations of income messages
 * @author Usuario
 */
public class ValidationFactory {
	
	public static ValidationSchema getValidationSchema(String criteria) {
		
		//validates HTML output banner schema
		if (criteria.equalsIgnoreCase( Constants.HTML_VAL)) {
			return new ValidateHTML();
		//validates XML output banner schema
		}else if (criteria.equalsIgnoreCase( Constants.XML_1_VAL)) {
			return new ValidateXML();
		//validates JSON output video schema
		}else if (criteria.equalsIgnoreCase( Constants.JSON_VAL)) {
			return new ValidateJSON();
		}else {
			return null;
		}
		
	}

}

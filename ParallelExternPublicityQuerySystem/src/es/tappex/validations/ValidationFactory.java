package es.tappex.validations;

import es.tappex.utils.Constants;

/**
 * Factory of validations of outcome schemas
 * @author Usuario
 *
 */

public class ValidationFactory {
	
	public static ValidationSchema getValidationSchema(String criteria) {
		
		//validates HTML schemas
		if (criteria.equalsIgnoreCase( Constants.APP_HTML)) {
			return new ValidateHTML();
		//validates XML schemas
		}else if (criteria.equalsIgnoreCase( Constants.APP_XML)) {
			return new ValidateXML();
		//validates JSON schemas
		}else if (criteria.equalsIgnoreCase( Constants.APP_JSON)) {
			return new ValidateJSON();
		}else {
			return null;
		}
		
	}

}

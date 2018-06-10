package es.tappex.validations;

import es.tappex.bean.Response;

/**
 * Response JSON Video info validation
 * @author Usuario
 */
public class ValidateJSON implements ValidationSchema {

	@Override
	public Response validateSchema(Response resp) {
		return resp;
	}

}

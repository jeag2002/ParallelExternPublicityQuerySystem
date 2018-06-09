package es.tappex.validations;

import es.tappex.bean.Response;

public class ValidateJSON implements ValidationSchema {

	@Override
	public Response validateSchema(Response resp) {
		return resp;
	}

}

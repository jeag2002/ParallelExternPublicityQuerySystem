package es.tappex.validations;

import es.tappex.bean.Response;

public interface ValidationSchema {
	public Response validateSchema(Response resp);
}

package es.tappex.validations;

import es.tappex.bean.Response;
import es.tappex.utils.Constants;

public class ValidateXML implements ValidationSchema {

	@Override
	public Response validateSchema(Response resp) {
		
		String body = resp.getResponseData();
		
		if (body.indexOf(Constants.TAG_XML_EVAL)!=-1) {
			
			String evalError = body.substring(body.indexOf(Constants.TAG_XML_EVAL)+Constants.TAG_XML_EVAL.length(), body.indexOf("\"",body.indexOf(Constants.TAG_XML_EVAL)+Constants.TAG_XML_EVAL.length()));
			
			if (!evalError.equalsIgnoreCase(Constants.OK_CONTENT) && (!evalError.equalsIgnoreCase(Constants.OK_CONTENT_1))) {
				System.out.println("[ValidateXML - "+Thread.currentThread().getName()+"] expect [" + Constants.OK_CONTENT + "/" + Constants.OK_CONTENT_1 + "] but is [" + evalError + "]");
				resp.setResponseId(Constants.OK_NOCONTENT);
				resp.setResponseData("");
			}
			
			
		}else {
			System.out.println("[ValidateXML - "+Thread.currentThread().getName()+"] cannot find the tag (" + Constants.TAG_XML_EVAL + ")");
			resp.setResponseId(Constants.OK_NOCONTENT);
			resp.setResponseData("");
		}
		
		
		return resp;
	}

}

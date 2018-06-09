package es.tappex.validations;

import es.tappex.bean.Response;
import es.tappex.utils.Constants;

/**
 * Validation of HTML schemas.
 * @author Usuario
 */

public class ValidateHTML implements ValidationSchema {

	@Override
	public Response validateSchema(Response resp) {
		
		if (resp.getResponseId().equalsIgnoreCase(Constants.OK_CONTENT)) {
		
			if (resp.getResponseData().trim().equalsIgnoreCase("")) {
				//Evaluate if body is empty
				System.out.println("[ValidateHTML -"+Thread.currentThread().getName()+"] Empty content! ");
				resp.setResponseId(Constants.OK_NOCONTENT);
				
			}else {
				//Evaluate if messageError is OK or not.
				String responseHTML = resp.getResponseData();
				if (responseHTML.indexOf(Constants.TAG_HTML_EVAL)!=-1) {
					String evalError = responseHTML.substring(responseHTML.indexOf(Constants.TAG_HTML_EVAL)+Constants.TAG_HTML_EVAL.length(), responseHTML.indexOf("\"",responseHTML.indexOf(Constants.TAG_HTML_EVAL)+Constants.TAG_HTML_EVAL.length()));
					//if inneractive error equal to OK or House Ad ==> it's OK. Else No Content.
					if (!evalError.equalsIgnoreCase(Constants.OK_CONTENT) && (!evalError.equalsIgnoreCase(Constants.OK_CONTENT_1))) {
						System.out.println("[ValidateHTML - "+Thread.currentThread().getName()+"] expect [" + Constants.OK_CONTENT + "/" + Constants.OK_CONTENT_1 + "] but is [" + evalError + "]");
						resp.setResponseId(Constants.OK_NOCONTENT);
						resp.setResponseData("");
					}
				}else {
					System.out.println("[ValidateHTML - "+Thread.currentThread().getName()+"] cannot find the tag (" + Constants.TAG_HTML_EVAL + ")");
					resp.setResponseId(Constants.OK_NOCONTENT);
					resp.setResponseData("");
				}
			}
		
		}
		
		
		return resp;
	}

}

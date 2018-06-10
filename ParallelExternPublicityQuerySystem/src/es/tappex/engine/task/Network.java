package es.tappex.engine.task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.xml.bind.JAXBException;

import es.tappex.bean.Response;
import es.tappex.utils.Constants;
import es.tappex.validations.ValidationFactory;
import es.tappex.validations.ValidationSchema;

public class Network {
	
	protected String key;
	protected String url;
	
	
	public Network(String _url, String _key) {
		url = _url;
		key = _key;
	}
	
	/**
	 * Do the remote url call
	 * @param _json				input json query
	 * @param _timeOut			ReadTimeout/ConnectTimeout
	 * @param _type_response	response type 
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws JAXBException
	 */
	
	public Response connection(String _json, long _timeOut, String _type_validation, String _type_response) throws MalformedURLException, IOException, JAXBException
	{
		
		if (url.equalsIgnoreCase("") || key.equalsIgnoreCase("")) {
			System.out.println("[Network -"+Thread.currentThread().getName()+"] error getting parameters url (" + url + ")  - key (" + key+ ")");
			return new Response(Thread.currentThread().getName(), Constants.KO_BADREQUEST,"No url or key");
		}else {
			
			//DO THE HTTP Connection.
			String urlconnection = url+"?"+_json;
			
			URL urlrequest  = new URL(urlconnection);
			HttpURLConnection conn = (HttpURLConnection)urlrequest.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", _type_response);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			conn.setReadTimeout((int)_timeOut);
			conn.setConnectTimeout((int)_timeOut);
			
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.connect();
			
			int HttpResultCode = conn.getResponseCode(); 
			
			Response resp = new Response();
			
			resp.setRequestURL(urlconnection);
			
			resp.setThreadId(Thread.currentThread().getName());
			
			if (HttpResultCode == Constants.OK_CONTENT_INT) {
				resp.setResponseId(Constants.OK_CONTENT);
				
				//Get the body of the response.
				/////////////////////////////////////////////////////////////////////////////////////////////
				StringBuilder sb = new StringBuilder(); 
				
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			    String line = null;  
			    while ((line = br.readLine()) != null) {  
			        sb.append(line + "\n");  
			    }
			    br.close();
				resp.setResponseData(sb.toString());
				/////////////////////////////////////////////////////////////////////////////////////////////
				
				//Validate Schema by type_response
				/////////////////////////////////////////////////////////////////////////////////////////////
				if (!_type_response.trim().equals("")) {
					//Get appropriate validation schema from factory
					ValidationSchema vS = ValidationFactory.getValidationSchema(_type_validation);
					//validate schema
					resp = vS.validateSchema(resp);
				}else {
					System.out.println("[Network -"+Thread.currentThread().getName()+"] warn cannot identify type_response ("+_type_response+")");
					resp.setResponseId(Constants.OK_NOCONTENT);
					resp.setResponseData("");
				}
				/////////////////////////////////////////////////////////////////////////////////////////////
			
			}else if (HttpResultCode == Constants.OK_NOCONTENT_INT) {
				
				resp.setResponseId(Constants.OK_NOCONTENT);
				resp.setResponseData("OK Response without Data");
				
			}else if (HttpResultCode == Constants.KO_BADREQUEST_INT) {
				
				resp.setResponseId(Constants.KO_BADREQUEST);
				resp.setResponseData("Bad Request Data");
				
			}else if (HttpResultCode >= Constants.KO_SERVERFAULT_INT) {
				throw new IOException("HTTP 500 Internal Server Error");
			
			}else {
				resp.setResponseId(Constants.OTHER);
				resp.setResponseData("HTTP Response warning (" + HttpResultCode + ")");
			}
			
			
			
			return resp;
				
		}
	}
	
	
	
}

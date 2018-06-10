package es.tappex.engine.task;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;

import javax.xml.bind.JAXBException;

import es.tappex.bean.Response;
import es.tappex.utils.Constants;

/**
 * Callable task element of the thread pool.. responsible of the network connection and query
 * @author Usuario
 *
 */


public class InnerActive extends Network implements Callable<Response>{
	
	
	private String json;
	private long timeOut;
	private String type_validation;
	private String type_response;

	
	
	/**
	 * Create a callable response query to remote host
	 * @param _url
	 * @param _key
	 * @param _json
	 * @param _timeOut
	 * @param _type_response
	 */
	
	public InnerActive(String _url, String _key, String _json, long _timeOut, String _type_validation, String _type_response) {
		super(_url, _key);
		json = _json;
		timeOut = _timeOut;
		type_validation = _type_validation;
		type_response = _type_response;
	}
	
	/**
	 * Process the network call
	 */
	@Override
	public Response call(){
		
		
		String errMsg = "";
		boolean isFault = false;
		
		long startTime = System.currentTimeMillis();
		
		Response callback = new Response();
		try {
			callback = super.connection(json, timeOut, type_validation, type_response);
		}catch (MalformedURLException e1) {
			errMsg = e1.getMessage();
		}catch(IOException e2) {
			errMsg = e2.getMessage();
		}catch(JAXBException e3) {
			errMsg = e3.getMessage();
		}catch(IllegalArgumentException e4){
			
			long endTime = System.currentTimeMillis();
			callback = new Response();
			callback.setResponseId(Constants.KO_BADREQUEST);
			callback.setThreadId(Thread.currentThread().getName());
			callback.setResponseData(e4.getMessage());
			callback.setTimeoutData(endTime-startTime);
			return callback;
			
		}catch(NullPointerException e5) {
			
			long endTime = System.currentTimeMillis();
			callback = new Response();
			callback.setResponseId(Constants.KO_BADREQUEST);
			callback.setThreadId(Thread.currentThread().getName());
			callback.setResponseData("NullPointerException!");
			callback.setTimeoutData(endTime-startTime);
			return callback;
			
			
		}catch (Exception e) {
			errMsg = e.getMessage();
		}finally {
			
			long endTime = System.currentTimeMillis();
			
			if (!errMsg.trim().equalsIgnoreCase("")) {
				callback = new Response();
				callback.setResponseId(Constants.KO_SERVERFAULT);
				callback.setThreadId(Thread.currentThread().getName());
				callback.setResponseData(errMsg);
			}
			
			callback.setTimeoutData(endTime-startTime);
			
			return callback;
		}
		
	}
		

}

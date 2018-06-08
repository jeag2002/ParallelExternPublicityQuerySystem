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


public class InnerActive extends Network implements Callable<Response>{
	
	
	private String json;
	private long timeOut;
	
	
	/**
	 * Create a callable response query to remote host
	 * @param _url
	 * @param _key
	 * @param _json
	 * @param _timeOut
	 */
	
	public InnerActive(String _url, String _key, String _json, long _timeOut) {
		super(_url, _key);
		json = _json;
		timeOut = _timeOut;
	}
	
	/**
	 * Process the network call
	 */
	@Override
	public Response call(){
		
		
		String errMsg = "";
		boolean isFault = false;
		
		Response callback = new Response();
		try {
			callback = super.connection(json, timeOut);
		}catch (MalformedURLException e1) {
			errMsg = e1.getMessage();
		}catch(IOException e2) {
			errMsg = e2.getMessage();
		}catch(JAXBException e3) {
			errMsg = e3.getMessage();
		}catch (Exception e) {
			errMsg = e.getMessage();
		}finally {
			
			if (!errMsg.trim().equalsIgnoreCase("")) {
				callback = new Response();
				callback.setResponseId(Constants.KO_SERVERFAULT);
				callback.setThreadId(Thread.currentThread().getName());
				callback.setResponseData(errMsg);
			}
			
			return callback;
		}
		
	}
		

}

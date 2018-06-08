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

public class Network {
	
	protected String key;
	protected String url;
	
	
	public Network(String _url, String _key) {
		url = _url;
		key = _key;
	}
	
	/**
	 * Do the remote call
	 * @param _json			input json query
	 * @param _timeOut		ReadTimeout/ConnectTimeout
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws JAXBException
	 */
	
	public Response connection(String _json, long _timeOut) throws MalformedURLException, IOException, JAXBException
	{
		
		if (url.equalsIgnoreCase("") || key.equalsIgnoreCase("")) {
			System.out.println("[Network -"+Thread.currentThread().getName()+"] error getting parameters url (" + url + ")  - key (" + key+ ")");
			return null;
		}else {
			
			String urlconnection = url+"?KEY="+key;
			
			URL urlrequest  = new URL(urlconnection);
			HttpURLConnection conn = (HttpURLConnection)urlrequest.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/html");
			conn.setRequestProperty("Content-Type", "application/json");
			
			conn.setReadTimeout((int)_timeOut);
			conn.setConnectTimeout((int)_timeOut);
			
			conn.setDoInput(true);
			conn.setDoOutput(true);
			
			OutputStream os = conn.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			writer.write(_json);
			
			writer.flush();
			writer.close();
			os.close();

			conn.connect();
			
			int HttpResultCode = conn.getResponseCode(); 
			System.out.println("[Network -"+Thread.currentThread().getName()+"] url::(" + urlconnection + ") RESPONSE (" + HttpResultCode + ")");
			
			Response resp = new Response();
			
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

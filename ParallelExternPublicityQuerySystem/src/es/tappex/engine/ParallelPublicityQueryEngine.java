package es.tappex.engine;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.script.ScriptException;

import es.tappex.bean.Request;
import es.tappex.bean.Response;
import es.tappex.engine.task.InnerActive;
import es.tappex.json.JSONMAPToParameters;
import es.tappex.json.JSONParser;
import es.tappex.utils.Constants;

public class ParallelPublicityQueryEngine {
	
	/**
	 * Process RequestData (extract info from file)
	 * @param req Request Bean
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws ScriptException
	 */
	
	public static void process(Request req) throws IOException, InterruptedException, ExecutionException, ScriptException, NullPointerException, Exception {
		 
		 String json = jsonMapToStringParameters(req);
		
		 Response response = processString(req, json);
		 
		//If a thread finalizes correctly. will save the content to file.
		if (response.getResponseId().equalsIgnoreCase(Constants.OK_CONTENT)) {
			stringToFile(req.getFil().getParent(),response.getResponseData(), req.getResponse_file());
		}
	}
	
	
	
	/**
	 * Process RequestData (process Request bean, create Threadpool, launch Threadpool, gather results)
	 
	 * @param req Request Bean input data 
	 * @return resp Response Bean 
	 * 
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws IOException
	 */
	
	public static Response processString(Request req, String json) throws InterruptedException, ExecutionException, IOException{
		
		Response resp = new Response();
		
		//Create threadpool
		ExecutorService executorService = Executors.newFixedThreadPool(req.getNum_threads());
		
		//Create ArrayList of callables
		List<Future<Response>> arrayCallableTasks = new ArrayList<Future<Response>>();
		
		//Fill callable Arraylist with runnables instances
		for(int i=0; i<req.getNum_threads(); i++) {
			InnerActive iA = new InnerActive(req.getUrl(), req.getKey(), json, req.getTimeOut(), req.getResponse_type());
			Future<Response> data = executorService.submit(iA);
			arrayCallableTasks.add(data);
		}
		
		//Process Futures of runnables instances.
		for (Future<Response> data : arrayCallableTasks) {
			try {
				Response response = data.get();
				if (response != null) {
					System.out.println("[ParallelPublicityQueryEngine - process] -- " + response.toString());
					
					//capture message from one of the threads
					resp.copy(response);
						
					//Copy valid result and end the thread pool
					if (response.getResponseId().equalsIgnoreCase(Constants.OK_CONTENT) || 
						response.getResponseId().equalsIgnoreCase(Constants.OK_CONTENT_TEST)||
						response.getResponseId().equalsIgnoreCase(Constants.OK_NOCONTENT) ||
						response.getResponseId().equalsIgnoreCase(Constants.KO_BADREQUEST) ||
						response.getResponseId().equalsIgnoreCase(Constants.OTHER)) {
						executorService.shutdown();
						break;
					}
					
				}else {
					System.out.println("[ParallelPublicityQueryEngine - process] -- null!");
				}
			}catch(InterruptedException | ExecutionException e) {
				System.out.println("[ParallelPublicityQueryEngine - process] error (" + e.getMessage() + ")");
			}
		}
		
		return resp;
	}
	
	
	
	/**
	 * Save Response Data to file.
	 * @param path
	 * @param content
	 * @param filename
	 * @throws IOException
	 */
	public static void stringToFile(String path, String content, String filename) throws IOException{
		
		String outputFile = path + File.separatorChar + filename;
		try (PrintWriter out = new PrintWriter(outputFile)) {
		    out.println(content);
		}
	}
	
	/**
	 * Transform data from files in inputparameters for GET QUERY
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public static String jsonMapToStringParameters(Request req) throws ScriptException, IOException, NullPointerException, Exception{
		String parameters = "";
		JSONParser jParser = new JSONParser();
		String json = new String(Files.readAllBytes(req.getFil().toPath()));
        Map data = jParser.parserStringToMAPJSON(json);
        parameters = JSONMAPToParameters.jsonMapToStringConversion(data, req.getKey());
        return parameters;		
	}
	
	
	

}

package es.tappex.engine;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import es.tappex.bean.Response;
import es.tappex.engine.task.InnerActive;
import es.tappex.utils.Constants;

public class ParallelPublicityQueryEngine {
	
	public static void process(File fil, String url, String key, Integer num_threads, Long keep_alive) throws IOException, InterruptedException, ExecutionException {
		 String json = new String(Files.readAllBytes(fil.toPath()));
		 process(json, fil.getParent() ,url, key, num_threads, keep_alive);
	}
	
	public static void process(String fileToJson, String folder, String url, String key, Integer num_threads, Long keep_alive) throws InterruptedException, ExecutionException, IOException{
		
		ExecutorService executorService = Executors.newFixedThreadPool(num_threads);
		List<Future<Response>> arrayCallableTasks = new ArrayList<Future<Response>>();
		
		
		for(int i=0; i<num_threads; i++) {
			InnerActive iA = new InnerActive(url, key, fileToJson,keep_alive);
			Future<Response> data = executorService.submit(iA);
			arrayCallableTasks.add(data);
		}
		
		
		for (Future<Response> data : arrayCallableTasks) {
			try {
				Response response = data.get();
				if (response != null) {
					System.out.println("[ParallelPublicityQueryEngine - process] -- " + response.toString());
					
					
					if (response.getResponseId().equalsIgnoreCase(Constants.OK_CONTENT)) {
						stringToFile(folder,response.getResponseData());
					}
					
					if (response.getResponseId().equalsIgnoreCase(Constants.OK_CONTENT) || 
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
	}
	
	
	
	
	public static void stringToFile(String path, String content) throws IOException{
		
		String outputFile = path + File.separatorChar + Constants.OUTPUT_FILE;
		try (PrintWriter out = new PrintWriter(outputFile)) {
		    out.println(content);
		}
	}
	
	

}

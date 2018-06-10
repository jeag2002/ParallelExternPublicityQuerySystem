package es.tappex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.AccessDeniedException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import javax.script.ScriptException;

import es.tappex.bean.Request;
import es.tappex.engine.ParallelPublicityQueryEngine;
import es.tappex.exceptions.LessThanOneException;
import es.tappex.utils.Constants;
import es.tappex.utils.VariableUtils;
 
public class Launcher {
	
	/**
	 * Get parameters from resource files
	 * @return
	 * @throws IOException
	 */
	private Properties  processLauncher() throws IOException{
		InputStream dataInputStream = Launcher.class.getClassLoader().getResourceAsStream(Constants.resources_file);
		Properties prop = new Properties();
		prop.load(dataInputStream);
		return prop;
	}
	
	/**
	 * Run function. input parameters evaluation
	 * @param args
	 * @return
	 */
	public int run(String[] args) {
		int res = Constants.END_OK;
		
		try {
			
			if ((args.length == 1) && ((args[0].trim().equalsIgnoreCase(Constants.HELP_1)) || (args[0].trim().equalsIgnoreCase(Constants.HELP_2)))){
					helpParameters();
				
			}else if (args.length == 3) {	
				
				Properties prop = processLauncher();
				
				
				String url = prop.getProperty(Constants.resources_url, "");
				String key = prop.getProperty(Constants.resources_key, "");
				File fil = VariableUtils.StringToFile(args[0]);
				
				String type_parser = prop.getProperty(Constants.resources_parser,"");
				String type_validation = prop.getProperty(Constants.resources_validation, "");
				String type_response = prop.getProperty(Constants.resources_response,"");
				String file_response = prop.getProperty(Constants.resources_outputfile,"");
			    
				
				Integer num_threads = VariableUtils.StringToInteger(args[1]);
				Long keep_alive = VariableUtils.StringToLong(args[2]);
				
				
				Request req = new Request();
				
				req.setUrl(url);
				req.setKey(key);
				req.setFil(fil);
				
				req.setParser_type(type_parser);
				req.setValidation_type(type_validation);
				
				req.setResponse_type(type_response);
				req.setResponse_file(file_response);
				
				req.setNum_threads(num_threads);
				req.setTimeOut(keep_alive);
				
				ParallelPublicityQueryEngine.process(req);
				
			}else {
				System.out.println("[Launcher] Error - wrong parameters");	
				helpParameters();
				helpMsg();
			}
		
		}catch(FileNotFoundException e0) {	
			System.out.println("[Launcher] General FileNotFoundException Error -  (" + e0.getMessage() + ")");
			res = Constants.END_KO;
		}catch(AccessDeniedException e1) {
			System.out.println("[Launcher] General AccessDeniedException Error - (" + e1.getMessage() + ")");
			res = Constants.END_KO;	
		}catch(IOException e2) {
			System.out.println("[Launcher] General IOException Error -  (" + e2.getMessage() + ")");
			res = Constants.END_KO;
		}catch (NullPointerException e3) {
			System.out.println("[Launcher] General NullPointerException Error -  (" + e3.getMessage() + ")");
			res = Constants.END_KO;
		}catch(ScriptException e4) {
			System.out.println("[Launcher] General JSON Parser Script Exception Error -  (" + e4.getMessage() + ")");
			res = Constants.END_KO;
		}catch(NumberFormatException e5) {
			res = Constants.END_KO;
		}catch(LessThanOneException e6) {
			res = Constants.END_KO;
		}catch(InterruptedException e7) {
			System.out.println("[Launcher] General InterruptedException Error -  (" + e7.getMessage() + ")");
			res = Constants.END_KO;
		}catch(ExecutionException e8) {
			System.out.println("[Launcher] General ExecutionException Error -  (" + e8.getMessage() + ")");
			res = Constants.END_KO;
		}catch(IllegalArgumentException e9) {
			System.out.println("[Launcher] General IllegalArgumentException Error - (" + e9.getMessage() + ")");
			res = Constants.END_KO;
		}catch(Exception e10) {
			res = Constants.END_KO;
		}finally {
			return res;
		}
	}
	
	
	/**
	 * MAIN 
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		int halt = Constants.END_OK;
		Launcher main = new Launcher();
		System.out.println("**************** PARALLELEXTERNPUBLICITYQUERYSYSTEM [INI] *********** ");
		halt = main.run(args);
		System.out.println("**************** PARALLELEXTERNPUBLICITYQUERYSYSTEM [END] *********** ");
		System.exit(halt);
	}
	
	/**
	 * Help info
	 */
	private void helpParameters() {
		System.out.println("Launcher <path_to_archive> <num_threads> <timeout_threads (ms)>");
		System.out.println("Ex: Launcher ./input/request.txt 10 10000");
	}
	
	private void helpMsg() {
		System.out.println("Launcher <-h/-help> print this help");
	}

}

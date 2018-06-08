package es.tappex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
				
				String key = prop.getProperty(Constants.resources_key, "");
			    String url = prop.getProperty(Constants.resources_url, "");
				
				File fil = VariableUtils.StringToFile(args[0]);
				Integer num_threads = VariableUtils.StringToInteger(args[1]);
				Long keep_alive = VariableUtils.StringToLong(args[2]);
				
				System.out.println("[Launcher] url ("+url+") key ("+key+") fil (" + fil.getName()+ ") num_threads (" + String.valueOf(num_threads) + ") keep_alive (" + String.valueOf(keep_alive) + ")");
				
				ParallelPublicityQueryEngine.process(fil, url, key, num_threads, keep_alive);
				
			}else {
				System.out.println("[Launcher] Error - wrong parameters");	
				helpParameters();
				helpMsg();
			}
		
		}catch(FileNotFoundException e0) {	
			System.out.println("[Launcher] General FileNotFoundException Error -  (" + e0.getMessage() + ")");
			res = Constants.END_KO;
		}catch(IOException e1) {
			System.out.println("[Launcher] General IOException Error -  (" + e1.getMessage() + ")");
			res = Constants.END_KO;
		}catch (NullPointerException e2) {
			System.out.println("[Launcher] General NullPointerException Error -  (" + e2.getMessage() + ")");
			res = Constants.END_KO;
		}catch(NumberFormatException e3) {
			res = Constants.END_KO;
		}catch(LessThanOneException e4) {
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

package es.tappex.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import es.tappex.exceptions.LessThanOneException;


public class VariableUtils {
	/**
	 * Transform String to Long
	 * @param value
	 * @return
	 * @throws NumberFormatException
	 */
	public static Long StringToLong(String value) throws NumberFormatException, LessThanOneException{
		Long amount = -1L;
		try{
			
			amount = Long.valueOf(value);
			if (amount < 1L) {throw new LessThanOneException();}
			return amount;
			
		}catch(NumberFormatException e1) {
			throw new NumberFormatException("value (" + value + ") cannot tranform to Long");
		}catch(LessThanOneException e2) {
			throw new NumberFormatException("value (" + value + ") cannot be less than 1");
		}
	}
	
	/**
	 * Transform String to Integer
	 * @param value
	 * @return
	 * @throws NumberFormatException
	 */
	public static Integer StringToInteger(String value) throws NumberFormatException, LessThanOneException{
		Integer amount = -1;
		try{
			amount = Integer.parseInt(value);
			
			if (amount < 1) {
				throw new LessThanOneException();
			}
			
			return amount;
		}catch(NumberFormatException e1) {
			throw new NumberFormatException("value (" + value + ") cannot tranform to integer");
		}catch(LessThanOneException e2) {
			throw new NumberFormatException("value (" + value + ") cannot be less than 1");
		}
		
	}
	
	/**
	 * Transform String path to File 
	 * @param path
	 * @return
	 * @throws IOException
	 * @throws NullPointerException
	 * @throws FileNotFoundException
	 */
	
	public static File StringToFile(String path) throws IOException,NullPointerException,FileNotFoundException{
		
		File fil = null;
		
		if (path == null){
			throw new NullPointerException("path is null");
		}
		
		if (path.trim().equalsIgnoreCase("")){
			throw new NullPointerException("path is empty");
		}
		
			
		fil = new File(path);
				
		if (!fil.exists()){
			throw new FileNotFoundException("" + path + " doesn't exist");
		}
		
		if(!fil.isFile()){
			throw new IOException("" + path + " is not a File");
		}
		
		 String type = Files.probeContentType(fil.toPath());
		 
		 if (type == null){
			 throw new IOException("" + path + " is a binary file");
		 }
		 
		 if (!type.startsWith(Constants.text)){
			 throw new IOException("" + path + " is not a csv file. is a (" + type + ") file");
		 }
		 
		 	
		return fil;
	}
	
	
	
}

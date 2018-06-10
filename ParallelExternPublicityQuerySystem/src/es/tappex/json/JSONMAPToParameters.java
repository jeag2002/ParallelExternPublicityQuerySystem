package es.tappex.json;

import java.util.Calendar;
import java.util.Map;

/**
 * Extract possible parameters from JSON Mapping
   
   Example:
   http://m2m1.inner-active.mobi/simpleM2M/clientRequestEnhancedHtmlAd?
   f=20&
   g=m&
   nt=3G&
   v=Sm2m-2.1.0&
   lg=45.45679625%2C-122.84698568& (%2C = ",")
   a=18&
   aaid=a0760ae1-919a-42b7-808b-d0e53069b308&
   aid=Company_Name_OS&
   ua=Mozilla%2F5.0%20(Linux%3B%20U%3B%20Android%204.1.2%3B%20nl-nl%3B%20GT-I9300%20Build%2FJZO54K)%20AppleWebKit%2F534.30%20(KHTML%2C%20like%20Gecko)%20Version%2F4.0%20Mobile%20Safari%2F534.30&
   cip=107.20.248.13
   
*/

public class JSONMAPToParameters implements ParserSchema{
	
	/**
	 * Transform a Map with the structure of a JSON schema to input parameters for a GET HTTP Query
	 * @param JSONMapping JSON Structure
	 * @param key		  Company_Name_OS key	
	 * @return
	 * @throws Exception
	 */
	
	public String jsonMapToStringConversion(Map JSONMapping, String key) throws NullPointerException{
		
		String parameters = "";
		
		Calendar cal = Calendar.getInstance();
		Integer year_now = cal.get(Calendar.YEAR);
		
		
		//f ==>  max number of advices
		parameters+="f=20"; 
		
		//g ==> gender of the customer //root/user{gender}
		parameters+="&g=" + ((String)((Map)JSONMapping.get("user")).get("gender")).toLowerCase();	   	
		
		//nt ==> connection
		parameters+="&nt=3G";
		
		//v ==> inneractive server version
		parameters+="&v=Sm2m-2.1.0"; 
		
		//lg ==> GP location (latitude - longitude) //root/device/geo/{lat}-{long}
		Double lat = ((Double)((Map)(((Map)JSONMapping.get("device")).get("geo"))).get("lat")); 
		Double longitud = ((Double)((Map)(((Map)JSONMapping.get("device")).get("geo"))).get("lon")); 
		
		parameters+="&lg=" + lat.toString() +"%2C"+ longitud.toString();									
		
		//a ==> age (//root/user{year})
		Integer age = ((Integer)((Map)JSONMapping.get("user")).get("yob"));
		year_now = year_now-age;
		parameters+="&a=" + year_now.toString() ;									
		
		//aid
		parameters+="&aid="+key;
		
		//aaid ==> Google Advertising Id  //root/device{ifa}
		parameters+="&aaid=" + ((String)((Map)JSONMapping.get("device")).get("ifa")); 
		
		//ua ==> the user agent of the client device //root/device{ua}
		parameters+="&ua=" + ((String)((Map)JSONMapping.get("device")).get("ua")).replaceAll("\\s", "%20").replaceAll("/","%2F").replaceAll(";","%3B").replaceAll(",", "%2C");
		
		//cip => ip address of the device //root/device{ip}
		parameters+="&cip="+((String)((Map)JSONMapping.get("device")).get("ip"));								
	
		
		return parameters;
		
	}
	

}

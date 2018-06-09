package es.tappex.json;

import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Request File to Map of key-values
 * @author Usuario
 */

public class JSONParser {
	
	private ScriptEngine engine;
	
	public JSONParser() {
		ScriptEngineManager sem = new ScriptEngineManager();
        this.engine = sem.getEngineByName("javascript");
	}
	
	/**
	 * Transform String of data to map key-values using nashorn engine.
	 * @param data
	 * @return
	 * @throws ScriptException
	 */
	
	public Map parserStringToMAPJSON(String data) throws ScriptException {
		
		String script = "Java.asJSONCompatible(" + data + ")";
        Object result = this.engine.eval(script);
        Map contents = (Map) result;
        return contents;
	}
	
	

}

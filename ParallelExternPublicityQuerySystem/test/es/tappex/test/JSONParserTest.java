package es.tappex.test;

import java.io.IOException;
import java.util.Map;
import javax.script.ScriptException;
import org.junit.Before;
import org.junit.Test;
import es.tappex.json.JSONParser;
import static org.junit.Assert.assertEquals;

public class JSONParserTest {
	
	JSONParser jsonEngine;
	
	@Before
	public void  createJSONEngine() {
		jsonEngine = new JSONParser();
	}
	
	@Test(expected = ScriptException.class)
	public void parserJSON_KO () throws Exception{
		String malformedJSON = "xxxxxxxxxx";
		jsonEngine.parserStringToMAPJSON(malformedJSON);
	}
	
	@Test
	public void parserJSON_Empty () throws Exception{
		String malformedJSON = "{}";
		jsonEngine.parserStringToMAPJSON(malformedJSON);
	}
	
	@Test
	public void parserJSON_OK () throws Exception{
		String wellformedJSON = "{\"prueba_1\":\"valor_1\",\"prueba_2\":\"valor_2\"}";
		Map data = jsonEngine.parserStringToMAPJSON(wellformedJSON);
		assertEquals("prueba_1_valor","valor_1",(String)data.get("prueba_1"));
		assertEquals("prueba_2_valor","valor_2",(String)data.get("prueba_2"));
	
	}

	
	

}

package es.tappex.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import javax.script.ScriptException;
import org.junit.Before;
import org.junit.Test;

import es.tappex.json.JSONMAPToParameters;
import es.tappex.json.JSONParser;
import static org.junit.Assert.assertEquals;


public class JSONMAPToParametersTest {
	
	JSONMAPToParameters mapToParameters;
	JSONParser jsonEngine;
	Map data_ok;
	Map data_ko;
	
	@Before
	public void process() throws Exception{
		jsonEngine = new JSONParser();
		
		File fil = new File("./input/request.txt");
		String json = new String(Files.readAllBytes(fil.toPath()));
        data_ok = jsonEngine.parserStringToMAPJSON(json);
        
        String wellformedJSON = "{\"prueba_1\":\"valor_1\",\"prueba_2\":\"valor_2\"}";
        
        data_ko = jsonEngine.parserStringToMAPJSON(wellformedJSON);
        mapToParameters = new JSONMAPToParameters();
	}
	
	@Test
	public void wellformedParameters() throws Exception{
		String data = mapToParameters.jsonMapToStringConversion(data_ok, "Tappx_3134_TriviaCrack_BM_Android");
		String eval = "f=20&g=m&nt=3G&v=Sm2m-2.1.0&lg=52.370998382568%2C4.9040999412537&a=38&aid=Tappx_3134_TriviaCrack_BM_Android&aaid=38400000-8cf0-11bd-b23e-10b96e40000d&ua=Mozilla%2F5.0%20(Linux%3B%20Android%204.4.3%3B%20HTC%20One%20Build%2FKTU84L)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F38.0.2125.102%20Mobile%20Safari%2F537.36&cip=213.127.223.58";
		assertEquals("data_processed",data,eval);
		
	}
	
	@Test(expected = NullPointerException.class)
	public void malformedParameters() throws Exception{
		String data = mapToParameters.jsonMapToStringConversion(data_ko, "Tappx_3134_TriviaCrack_BM_Android");
		String eval = "f=20&g=m&nt=3G&v=Sm2m-2.1.0&lg=52.370998382568%2C4.9040999412537&a=38&aid=Tappx_3134_TriviaCrack_BM_Android&aaid=38400000-8cf0-11bd-b23e-10b96e40000d&ua=Mozilla%2F5.0%20(Linux%3B%20Android%204.4.3%3B%20HTC%20One%20Build%2FKTU84L)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F38.0.2125.102%20Mobile%20Safari%2F537.36&cip=213.127.223.58";
		assertEquals("data_processed",data,eval);	
	}
	
	
	
	
	

}

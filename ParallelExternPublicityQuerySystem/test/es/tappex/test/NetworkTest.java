package es.tappex.test;

import es.tappex.bean.Response;
import es.tappex.engine.task.Network;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;
import javax.script.ScriptException;
import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;
import es.tappex.json.JSONParser;
import es.tappex.utils.Constants;

import static org.junit.Assert.assertEquals;


public class NetworkTest {
	
	private Network net;
	private Network net_1;
	
	@Before
	public void initNet() {
		net = new Network("http://m2m1.inner-active.mobi/simpleM2M/clientRequestEnhancedHtmlAd","Tappx_3134_TriviaCrack_BM_Android");
		net_1 = new Network("","");
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	//bad timeout
	public void testNet_badTimeout() throws MalformedURLException, IOException, JAXBException{
		net.connection("", -1L, "", "");
	}
	
	@Test
	//unexpected response
	public void testNet_emptyConn() throws MalformedURLException, IOException, JAXBException{
		Response resp = net.connection("", 0L, "","");
		assertEquals("network resp",resp.getResponseId(), Constants.OTHER);
	}
	
	
	@Test
	//empty json
	public void testNet_emptyJSON() throws MalformedURLException, IOException, JAXBException{
		Response resp = net.connection("", 1000L, Constants.HTML_VAL, Constants.APP_HTML);
		assertEquals("network resp",resp.getResponseId(), Constants.OTHER);
	}
	
	@Test
	//empty outputformat
	public void testNet_emptyOutputFormat() throws MalformedURLException, IOException, JAXBException{
		String json = "f=20&g=m&nt=3G&v=Sm2m-2.1.0&lg=52.370998382568%2C4.9040999412537&a=38&aid=Tappx_3134_TriviaCrack_BM_Android&aaid=38400000-8cf0-11bd-b23e-10b96e40000d&ua=Mozilla%2F5.0%20(Linux%3B%20Android%204.4.3%3B%20HTC%20One%20Build%2FKTU84L)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F38.0.2125.102%20Mobile%20Safari%2F537.36&cip=213.127.223.58";
		Response resp = net.connection(json, 1000L, "","");
		assertEquals("network resp",resp.getResponseId(), Constants.OK_NOCONTENT);
	}
	
	@Test
	//bad outputformat
	public void testNet_badOutputFormat() throws MalformedURLException, IOException, JAXBException{
		String json = "f=20&g=m&nt=3G&v=Sm2m-2.1.0&lg=52.370998382568%2C4.9040999412537&a=38&aid=Tappx_3134_TriviaCrack_BM_Android&aaid=38400000-8cf0-11bd-b23e-10b96e40000d&ua=Mozilla%2F5.0%20(Linux%3B%20Android%204.4.3%3B%20HTC%20One%20Build%2FKTU84L)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F38.0.2125.102%20Mobile%20Safari%2F537.36&cip=213.127.223.58";
		Response resp = net.connection(json, 1000L, Constants.XML_1_VAL, Constants.APP_XML);
		assertEquals("network resp",resp.getResponseId(), Constants.OK_NOCONTENT);
	}
	
	@Test
	//bad json
	public void testNet_badJSON() throws MalformedURLException, IOException, JAXBException{
		String json = "aaid=38400000-8cf0-11bd-b23e-10b96e40000d&ua=Mozilla%2F5.0%20(Linux%3B%20Android%204.4.3%3B%20HTC%20One%20Build%2FKTU84L)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F38.0.2125.102%20Mobile%20Safari%2F537.36&cip=213.127.223.58";
		Response resp = net.connection(json, 1000L, Constants.HTML_VAL, Constants.APP_HTML);
		assertEquals("network resp",resp.getResponseId(), Constants.OK_NOCONTENT);
	}
	
	@Test
	//malformed json
	public void testNet_malformedJSON() throws MalformedURLException, IOException, JAXBException{
		String json = "xxxxx";
		Response resp = net.connection(json, 1000L, Constants.HTML_VAL, Constants.APP_HTML);
		assertEquals("network resp",resp.getResponseId(), Constants.OTHER);
	}
	
	@Test
	//bad validation
	public void testNet_badValidationResponse() throws MalformedURLException, IOException, JAXBException{
		String json = "f=20&g=m&nt=3G&v=Sm2m-2.1.0&lg=52.370998382568%2C4.9040999412537&a=38&aid=Tappx_3134_TriviaCrack_BM_Android&aaid=38400000-8cf0-11bd-b23e-10b96e40000d&ua=Mozilla%2F5.0%20(Linux%3B%20Android%204.4.3%3B%20HTC%20One%20Build%2FKTU84L)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F38.0.2125.102%20Mobile%20Safari%2F537.36&cip=213.127.223.58";
		Response resp = net.connection(json, 1000L, Constants.XML_1_VAL, Constants.APP_HTML);
		assertEquals("network resp",resp.getResponseId(), Constants.OK_NOCONTENT);
	}
	
	@Test
	//OK
	public void testNet_OK() throws MalformedURLException, IOException, JAXBException{
		String json = "f=20&g=m&nt=3G&v=Sm2m-2.1.0&lg=52.370998382568%2C4.9040999412537&a=38&aid=Tappx_3134_TriviaCrack_BM_Android&aaid=38400000-8cf0-11bd-b23e-10b96e40000d&ua=Mozilla%2F5.0%20(Linux%3B%20Android%204.4.3%3B%20HTC%20One%20Build%2FKTU84L)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F38.0.2125.102%20Mobile%20Safari%2F537.36&cip=213.127.223.58";
		Response resp = net.connection(json, 1000L, Constants.HTML_VAL, Constants.APP_HTML);
		assertEquals("network resp",resp.getResponseId(), Constants.OK_CONTENT_TEST);
	}
	
	@Test
	public void test_NotEnoughParameters() throws MalformedURLException, IOException, JAXBException{
		Response resp = net_1.connection("", 0L, Constants.HTML_VAL, Constants.APP_HTML);
		assertEquals("network resp",resp.getResponseId(), Constants.KO_BADREQUEST);
	}
	
	

}

package es.tappex.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.tappex.bean.Request;
import es.tappex.bean.Response;
import es.tappex.engine.ParallelPublicityQueryEngine;
import es.tappex.utils.Constants;

public class ParallelPublicityQueryEngineOKSingleTest {
	
	@Before
	public void initTest() {
	}
	
	@Test
	public void testPublicityQueryEngineTest_OK_3_HTML() throws Exception{
		
		Request req = new Request();
		req.setNum_threads(3);
		req.setUrl("http://m2m1.inner-active.mobi/simpleM2M/clientRequestEnhancedHtmlAd");
		req.setKey("Tappx_3134_TriviaCrack_BM_Android");
		req.setResponse_type(Constants.APP_HTML);
		req.setValidation_type(Constants.HTML_VAL);
		
		req.setTimeOut(1000L);
		
		Response response = ParallelPublicityQueryEngine.processString(req,"f=20&g=m&nt=3G&v=Sm2m-2.1.0&lg=52.370998382568%2C4.9040999412537&a=38&aid=Tappx_3134_TriviaCrack_BM_Android&aaid=38400000-8cf0-11bd-b23e-10b96e40000d&ua=Mozilla%2F5.0%20(Linux%3B%20Android%204.4.3%3B%20HTC%20One%20Build%2FKTU84L)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F38.0.2125.102%20Mobile%20Safari%2F537.36&cip=213.127.223.58");
		assertEquals("responseID",response.getResponseId(), Constants.OK_CONTENT_TEST);
	}
	
	@Test
	public void testPublicityQueryEngineTest_OK_3_XML() throws Exception{
		
		Request req = new Request();
		req.setNum_threads(3);
		req.setUrl("http://m2m1.inner-active.mobi/simpleM2M/clientRequestEnhancedXmlAd");
		req.setKey("Tappx_3134_TriviaCrack_BM_Android");
		req.setResponse_type(Constants.APP_XML);
		req.setValidation_type(Constants.XML_1_VAL);
		req.setTimeOut(1000L);
		
		Response response = ParallelPublicityQueryEngine.processString(req,"f=20&g=m&nt=3G&v=Sm2m-2.1.0&lg=52.370998382568%2C4.9040999412537&a=38&aid=Tappx_3134_TriviaCrack_BM_Android&aaid=38400000-8cf0-11bd-b23e-10b96e40000d&ua=Mozilla%2F5.0%20(Linux%3B%20Android%204.4.3%3B%20HTC%20One%20Build%2FKTU84L)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F38.0.2125.102%20Mobile%20Safari%2F537.36&cip=213.127.223.58");
		assertEquals("responseID",response.getResponseId(), Constants.OK_CONTENT_TEST);
	}
	
	@Test
	public void testPublicityQueryEngineTest_OK_3_BADPARAMETER_HTML() throws Exception{
		
		Request req = new Request();
		req.setNum_threads(3);
		req.setUrl("http://m2m1.inner-active.mobi/simpleM2M/clientRequestEnhancedHtmlAd");
		req.setKey("Tappx_3134_TriviaCrack_BM_Android");
		req.setResponse_type(Constants.APP_HTML);
		req.setValidation_type(Constants.HTML_VAL);
		req.setTimeOut(1000L);
		
		Response response = ParallelPublicityQueryEngine.processString(req,"f=20&g=m&nt=3G&v=Sm2m-2.1.0&lg=52.370998382568%2C4.9040999412537&a=38&aid=xxxxxxxxxxxxxxxx&aaid=38400000-8cf0-11bd-b23e-10b96e40000d&ua=Mozilla%2F5.0%20(Linux%3B%20Android%204.4.3%3B%20HTC%20One%20Build%2FKTU84L)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F38.0.2125.102%20Mobile%20Safari%2F537.36&cip=213.127.223.58");
		assertEquals("responseID",response.getResponseId(), Constants.OK_NOCONTENT);
	}
	
	@Test
	public void testPublicityQueryEngineTest_OK_3_BADPARAMETER_XML() throws Exception{
		
		Request req = new Request();
		req.setNum_threads(3);
		req.setUrl("http://m2m1.inner-active.mobi/simpleM2M/clientRequestEnhancedXmlAd");
		req.setKey("Tappx_3134_TriviaCrack_BM_Android");
		req.setResponse_type(Constants.APP_XML);
		req.setValidation_type(Constants.XML_1_VAL);
		req.setTimeOut(1000L);
		
		Response response = ParallelPublicityQueryEngine.processString(req,"f=20&g=m&nt=3G&v=Sm2m-2.1.0&lg=52.370998382568%2C4.9040999412537&a=38&aid=xxxxxxxxxxxxxxxx&aaid=38400000-8cf0-11bd-b23e-10b96e40000d&ua=Mozilla%2F5.0%20(Linux%3B%20Android%204.4.3%3B%20HTC%20One%20Build%2FKTU84L)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F38.0.2125.102%20Mobile%20Safari%2F537.36&cip=213.127.223.58");
		assertEquals("responseID",response.getResponseId(), Constants.OK_NOCONTENT);
	}

	@Test
	public void testPublicityQueryEngineTest_OK_3_NOPARAMETERS_HTML() throws Exception{
		
		Request req = new Request();
		req.setNum_threads(3);
		req.setUrl("http://m2m1.inner-active.mobi/simpleM2M/clientRequestEnhancedHtmlAd");
		req.setKey("Tappx_3134_TriviaCrack_BM_Android");
		req.setResponse_type(Constants.APP_HTML);
		req.setValidation_type(Constants.HTML_VAL);
		req.setTimeOut(1000L);
		
		Response response = ParallelPublicityQueryEngine.processString(req,"aaid=38400000-8cf0-11bd-b23e-10b96e40000d&ua=Mozilla%2F5.0%20(Linux%3B%20Android%204.4.3%3B%20HTC%20One%20Build%2FKTU84L)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F38.0.2125.102%20Mobile%20Safari%2F537.36&cip=213.127.223.58");
		assertEquals("responseID",response.getResponseId(), Constants.OK_NOCONTENT);
	}
	
	@Test
	public void testPublicityQueryEngineTest_OK_3_NOPARAMETERS_XML() throws Exception{
		
		Request req = new Request();
		req.setNum_threads(3);
		req.setUrl("http://m2m1.inner-active.mobi/simpleM2M/clientRequestEnhancedXmlAd");
		req.setKey("Tappx_3134_TriviaCrack_BM_Android");
		req.setResponse_type(Constants.APP_XML);
		req.setValidation_type(Constants.XML_1_VAL);
		req.setTimeOut(1000L);
		
		Response response = ParallelPublicityQueryEngine.processString(req,"aaid=38400000-8cf0-11bd-b23e-10b96e40000d&ua=Mozilla%2F5.0%20(Linux%3B%20Android%204.4.3%3B%20HTC%20One%20Build%2FKTU84L)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F38.0.2125.102%20Mobile%20Safari%2F537.36&cip=213.127.223.58");
		assertEquals("responseID",response.getResponseId(), Constants.OK_NOCONTENT);
	}
	
	

}

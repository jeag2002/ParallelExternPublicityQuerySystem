package es.tappex.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

import javax.script.ScriptException;

import org.junit.Before;
import org.junit.Test;

import es.tappex.bean.Request;
import es.tappex.bean.Response;
import es.tappex.engine.ParallelPublicityQueryEngine;
import es.tappex.utils.Constants;

import static org.junit.Assert.assertEquals;

/**
 * 
 * @author Usuario
 *
 */

public class ParallelPublicityQueryEngineFailsTest {
	
	@Before
	public void initTest() {
	}
	
	@Test(expected = IOException.class)
	//test raise exception with empty file
	public void testPublicityQueryEngineTest_KO_LoadNoFile () throws Exception{
		Request req = new Request();
		ParallelPublicityQueryEngine.process(req);
	}
	
	@Test(expected = NullPointerException.class)
	public void testPublicityQueryEngineTest_KO_LoadEmptyFile () throws Exception{
		String path = "./input/request_2.txt";
		File fil = new File(path);
		Request req = new Request();
		req.setFil(fil);
		ParallelPublicityQueryEngine.process(req);
	}
	
	@Test(expected = NullPointerException.class)
	public void testPublicityQueryEngineTest_KO_LoadIncompleteFile () throws Exception{
		String path = "./input/request_1.txt";
		File fil = new File(path);
		Request req = new Request();
		req.setFil(fil);
		ParallelPublicityQueryEngine.process(req);
	}	
	
	@Test(expected = ScriptException.class)
	public void testPublicityQueryEngineTest_KO_MalformedFile () throws Exception{
		String path = "./input/request_3.txt";
		File fil = new File(path);
		Request req = new Request();
		req.setFil(fil);
		ParallelPublicityQueryEngine.process(req);
	}
	
	@Test(expected = NullPointerException.class)
	public void testPublicityQueryEngineTest_KO_UnexpectedNullParameter_1() throws Exception{
		Request req = new Request();
		req.setFil(null);
		ParallelPublicityQueryEngine.process(req);
	}
	
	@Test(expected = AccessDeniedException.class)
	public void testPublicityQueryEngineTest_KO_UnexpectedNullParameter_2() throws Exception{
		Request req = new Request();
		req.setValidation_type(null);
		ParallelPublicityQueryEngine.process(req);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPublicityQueryEngineTest_KO_UnexpectedNullParameter_3() throws Exception{
		Request req = new Request();
		String path = "./input/request.txt";
		File fil = new File(path);
		req.setFil(fil);
		req.setValidation_type(null);
		ParallelPublicityQueryEngine.process(req);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	//test raise exception with negative thread pool
	public void testPublicityQueryEngineTest_KO_NegativeThreadPool() throws Exception{
		Request req = new Request();
		req.setNum_threads(-1);
		ParallelPublicityQueryEngine.processString(req, "");
	}
	
	@Test
	//test get null data if there aren't enough information (no url or key) -- (1 thread)
	public void testPublicityQueryEngineTest_KO_NoUrlNoKey() throws Exception{
		Request req = new Request();
		req.setNum_threads(1);
		Response response = ParallelPublicityQueryEngine.processString(req, "");
		assertEquals("responseID",response.getResponseId(), Constants.KO_BADREQUEST);
	}
	
	
	@Test
	//test not good key -- (1 thread)
	public void testPublicityQueryEngineTest_KO_BadKey() throws Exception{
		Request req = new Request();
		req.setNum_threads(1);
		req.setUrl("http://m2m1.inner-active.mobi/simpleM2M/clientRequestEnhancedHtmlAd");
		req.setKey("xxxxxxxxxxx");
		req.setTimeOut(2000L);
		req.setValidation_type(Constants.HTML_VAL);
		req.setResponse_type(Constants.APP_HTML);
		Response response = ParallelPublicityQueryEngine.processString(req,"aid="+req.getKey());
		assertEquals("responseID",response.getResponseId(), Constants.OK_NOCONTENT);
	}
	
	@Test
	//test not good key -- (1 thread)
	public void testPublicityQueryEngineTest_KO_BadKey_1() throws Exception{
		Request req = new Request();
		req.setNum_threads(1);
		req.setUrl("http://m2m1.inner-active.mobi/simpleM2M/clientRequestEnhancedHtmlAd");
		req.setKey("Company_Name_OS");
		req.setTimeOut(2000L);
		req.setValidation_type(Constants.HTML_VAL);
		req.setResponse_type(Constants.APP_HTML);
		Response response = ParallelPublicityQueryEngine.processString(req,"f=20&g=m&nt=3G&v=Sm2m-2.1.0&lg=52.370998382568%2C4.9040999412537&a=38&aid=Company_Name_OS&aaid=38400000-8cf0-11bd-b23e-10b96e40000d&ua=Mozilla%2F5.0%20(Linux%3B%20Android%204.4.3%3B%20HTC%20One%20Build%2FKTU84L)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F38.0.2125.102%20Mobile%20Safari%2F537.36&cip=213.127.223.58");
		assertEquals("responseID",response.getResponseId(), Constants.OK_NOCONTENT);
	}	
	
	@Test
	//test unexpected url good key -- (1 thread)
	public void testPublicityQueryEngineTest_KO_BadUrlGoodKey() throws Exception{
		Request req = new Request();
		req.setNum_threads(1);
		req.setUrl("http://www.google.com");
		req.setKey("Tappx_3134_TriviaCrack_BM_Android");
		req.setTimeOut(2000L);
		req.setValidation_type(Constants.HTML_VAL);
		req.setResponse_type(Constants.APP_HTML);
		Response response = ParallelPublicityQueryEngine.processString(req,"aid="+req.getKey());
		assertEquals("responseID",response.getResponseId(), Constants.OK_NOCONTENT);
	}
	
	
	@Test
	//test unexpected url bad key -- (1 thread)
	public void testPublicityQueryEngineTest_KO_BadKeyBadJsonBadUrl() throws Exception{
		Request req = new Request();
		req.setNum_threads(1);
		req.setUrl("http://www.google.com");
		req.setKey("xxxxxxxxxxx");
		req.setTimeOut(2000L);
		req.setValidation_type(Constants.HTML_VAL);
		req.setResponse_type(Constants.APP_HTML);
		Response response = ParallelPublicityQueryEngine.processString(req,"aid="+req.getKey());
		assertEquals("responseID",response.getResponseId(), Constants.OK_NOCONTENT);
	}
	
	
	@Test
	//test malformed url -- (1 thread)
	public void testPublicityQueryEngineTest_KO_MalformedUrl() throws Exception{
		Request req = new Request();
		req.setNum_threads(1);
		req.setUrl("jsklsdkdsjklfdjlkdjflksdfjl");
		req.setKey("xxxxxxxxxxx");
		req.setTimeOut(2000L);
		req.setValidation_type(Constants.HTML_VAL);
		req.setResponse_type(Constants.APP_HTML);
		Response response = ParallelPublicityQueryEngine.processString(req,"aid="+req.getKey());
		assertEquals("responseID",response.getResponseId(), Constants.KO_SERVERFAULT);
	}
	
	
	@Test
	//test timeout parameter is incorrect  -- (1 thread)
	public void testPublicityQueryEngineTest_KO_BadTimeOut() throws Exception{
		Request req = new Request();
		req.setNum_threads(1);
		req.setUrl("http://m2m1.inner-active.mobi/simpleM2M/clientRequestEnhancedHtmlAd");
		req.setKey("Tappx_3134_TriviaCrack_BM_Android");
		req.setTimeOut(-1L);
		Response response = ParallelPublicityQueryEngine.processString(req,"aid="+req.getKey());
		assertEquals("responseID",response.getResponseId(), Constants.KO_BADREQUEST);
	}
		
	@Test
	//test input data with not enough data  -- (1 thread)
	public void testPublicityQueryEngineTest_KO_NotEnoughDataJSON() throws Exception{
		Request req = new Request();
		req.setNum_threads(1);
		req.setUrl("http://m2m1.inner-active.mobi/simpleM2M/clientRequestEnhancedHtmlAd");
		req.setKey("Tappx_3134_TriviaCrack_BM_Android");
		req.setTimeOut(2000L);
		Response response = ParallelPublicityQueryEngine.processString(req,"aid="+req.getKey());
		assertEquals("responseId",response.getResponseId(), Constants.OK_NOCONTENT);
	}
	
	
	@Test
	//test input bad validation id
	public void testPublicityQueryEngineTest_KO_BadResponseType() throws Exception{
		Request req = new Request();
		req.setNum_threads(1);
		req.setUrl("http://m2m1.inner-active.mobi/simpleM2M/clientRequestEnhancedHtmlAd");
		req.setKey("Tappx_3134_TriviaCrack_BM_Android");
		req.setTimeOut(2000L);
		req.setResponse_type(Constants.APP_XML);
		req.setValidation_type(Constants.HTML_VAL);
		Response response = ParallelPublicityQueryEngine.processString(req,"f=20&g=m&nt=3G&v=Sm2m-2.1.0&lg=52.370998382568%2C4.9040999412537&a=38&aid=Tappx_3134_TriviaCrack_BM_Android&aaid=38400000-8cf0-11bd-b23e-10b96e40000d&ua=Mozilla%2F5.0%20(Linux%3B%20Android%204.4.3%3B%20HTC%20One%20Build%2FKTU84L)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F38.0.2125.102%20Mobile%20Safari%2F537.36&cip=213.127.223.58");
		assertEquals("responseId",response.getResponseId(), Constants.OK_CONTENT_TEST);
	}
		
	@Test
	//test input null validation id
	public void testPublicityQueryEngineTest_KO_NullResponseType() throws Exception{
		Request req = new Request();
		req.setNum_threads(1);
		req.setUrl("http://m2m1.inner-active.mobi/simpleM2M/clientRequestEnhancedHtmlAd");
		req.setKey("Tappx_3134_TriviaCrack_BM_Android");
		req.setTimeOut(2000L);
		req.setResponse_type(null);
		req.setValidation_type(Constants.HTML_VAL);
		Response response = ParallelPublicityQueryEngine.processString(req,"f=20&g=m&nt=3G&v=Sm2m-2.1.0&lg=52.370998382568%2C4.9040999412537&a=38&aid=Tappx_3134_TriviaCrack_BM_Android&aaid=38400000-8cf0-11bd-b23e-10b96e40000d&ua=Mozilla%2F5.0%20(Linux%3B%20Android%204.4.3%3B%20HTC%20One%20Build%2FKTU84L)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F38.0.2125.102%20Mobile%20Safari%2F537.36&cip=213.127.223.58");
		assertEquals("responseId",response.getResponseId(), Constants.OTHER);
	}
	
	
	@Test
	//test input not defined validation
	public void testPublicityQueryEngineTest_KO_MissformedResponseType() throws Exception{
		Request req = new Request();
		req.setNum_threads(1);
		req.setUrl("http://m2m1.inner-active.mobi/simpleM2M/clientRequestEnhancedHtmlAd");
		req.setKey("Tappx_3134_TriviaCrack_BM_Android");
		req.setTimeOut(2000L);
		req.setResponse_type(Constants.HELP_1);
		req.setValidation_type(Constants.HTML_VAL);
		Response response = ParallelPublicityQueryEngine.processString(req,"f=20&g=m&nt=3G&v=Sm2m-2.1.0&lg=52.370998382568%2C4.9040999412537&a=38&aid=Tappx_3134_TriviaCrack_BM_Android&aaid=38400000-8cf0-11bd-b23e-10b96e40000d&ua=Mozilla%2F5.0%20(Linux%3B%20Android%204.4.3%3B%20HTC%20One%20Build%2FKTU84L)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F38.0.2125.102%20Mobile%20Safari%2F537.36&cip=213.127.223.58");
		assertEquals("responseId",response.getResponseId(), Constants.OK_CONTENT_TEST);
	}

	
	
	
	
	
	
	@Test
	//test input bad validation id
	public void testPublicityQueryEngineTest_KO_BadValidation() throws Exception{
		Request req = new Request();
		req.setNum_threads(1);
		req.setUrl("http://m2m1.inner-active.mobi/simpleM2M/clientRequestEnhancedHtmlAd");
		req.setKey("Tappx_3134_TriviaCrack_BM_Android");
		req.setTimeOut(2000L);
		req.setResponse_type(Constants.APP_HTML);
		req.setValidation_type(Constants.XML_1_VAL);
		Response response = ParallelPublicityQueryEngine.processString(req,"f=20&g=m&nt=3G&v=Sm2m-2.1.0&lg=52.370998382568%2C4.9040999412537&a=38&aid=Tappx_3134_TriviaCrack_BM_Android&aaid=38400000-8cf0-11bd-b23e-10b96e40000d&ua=Mozilla%2F5.0%20(Linux%3B%20Android%204.4.3%3B%20HTC%20One%20Build%2FKTU84L)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F38.0.2125.102%20Mobile%20Safari%2F537.36&cip=213.127.223.58");
		assertEquals("responseId",response.getResponseId(), Constants.OK_NOCONTENT);
	}
		
	@Test
	//test input null validation id
	public void testPublicityQueryEngineTest_KO_NullValidation() throws Exception{
		Request req = new Request();
		req.setNum_threads(1);
		req.setUrl("http://m2m1.inner-active.mobi/simpleM2M/clientRequestEnhancedHtmlAd");
		req.setKey("Tappx_3134_TriviaCrack_BM_Android");
		req.setTimeOut(2000L);
		req.setResponse_type(Constants.APP_HTML);
		req.setValidation_type(null);
		Response response = ParallelPublicityQueryEngine.processString(req,"f=20&g=m&nt=3G&v=Sm2m-2.1.0&lg=52.370998382568%2C4.9040999412537&a=38&aid=Tappx_3134_TriviaCrack_BM_Android&aaid=38400000-8cf0-11bd-b23e-10b96e40000d&ua=Mozilla%2F5.0%20(Linux%3B%20Android%204.4.3%3B%20HTC%20One%20Build%2FKTU84L)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F38.0.2125.102%20Mobile%20Safari%2F537.36&cip=213.127.223.58");
		assertEquals("responseId",response.getResponseId(), Constants.KO_BADREQUEST);
	}
	
	
	@Test
	//test input not defined validation
	public void testPublicityQueryEngineTest_KO_MissformedValidation() throws Exception{
		Request req = new Request();
		req.setNum_threads(1);
		req.setUrl("http://m2m1.inner-active.mobi/simpleM2M/clientRequestEnhancedHtmlAd");
		req.setKey("Tappx_3134_TriviaCrack_BM_Android");
		req.setTimeOut(2000L);
		req.setResponse_type(Constants.APP_HTML);
		req.setValidation_type(Constants.HELP_1);
		Response response = ParallelPublicityQueryEngine.processString(req,"f=20&g=m&nt=3G&v=Sm2m-2.1.0&lg=52.370998382568%2C4.9040999412537&a=38&aid=Tappx_3134_TriviaCrack_BM_Android&aaid=38400000-8cf0-11bd-b23e-10b96e40000d&ua=Mozilla%2F5.0%20(Linux%3B%20Android%204.4.3%3B%20HTC%20One%20Build%2FKTU84L)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F38.0.2125.102%20Mobile%20Safari%2F537.36&cip=213.127.223.58");
		assertEquals("responseId",response.getResponseId(), Constants.KO_BADREQUEST);
	}
	
	
	
	
	@Test
	//test input data is not recognizable -- (1 thread)
	public void testPublicityQueryEngineTest_KO_MalformedJSON() throws Exception{
		Request req = new Request();
		req.setNum_threads(1);
		req.setUrl("http://m2m1.inner-active.mobi/simpleM2M/clientRequestEnhancedHtmlAd");
		req.setKey("Tappx_3134_TriviaCrack_BM_Android");
		req.setTimeOut(20000L);
		req.setValidation_type(Constants.HTML_VAL);
		req.setResponse_type(Constants.APP_HTML);
		Response response = ParallelPublicityQueryEngine.processString(req,"xxxxxxxx");
		assertEquals("responseId",response.getResponseId(), Constants.OTHER);
	}

	@Test
	//test input data timeout -- (1 thread)
	public void testPublicityQueryEngineTest_KO_TimeOut_1Thread() throws Exception{
		Request req = new Request();
		req.setNum_threads(1);
		req.setUrl("http://m2m1.inner-active.mobi/simpleM2M/clientRequestEnhancedHtmlAd");
		req.setKey("Tappx_3134_TriviaCrack_BM_Android");
		req.setTimeOut(10L);
		req.setValidation_type(Constants.HTML_VAL);
		req.setResponse_type(Constants.APP_HTML);
		Response response = ParallelPublicityQueryEngine.processString(req,"xxxxxxxxxx");
		assertEquals("responseId",response.getResponseId(), Constants.KO_SERVERFAULT);
	}
	
	
	@Test
	//test input data timeout -- (3 threads)
	public void testPublicityQueryEngineTest_KO_TimeOut_3Thread() throws Exception{
		Request req = new Request();
		req.setNum_threads(3);
		req.setUrl("http://m2m1.inner-active.mobi/simpleM2M/clientRequestEnhancedHtmlAd");
		req.setKey("Tappx_3134_TriviaCrack_BM_Android");
		req.setTimeOut(10L);
		req.setValidation_type(Constants.HTML_VAL);
		req.setResponse_type(Constants.APP_HTML);
		Response response = ParallelPublicityQueryEngine.processString(req,"xxxxxxxxxx");
		assertEquals("responseId",response.getResponseId(), Constants.KO_SERVERFAULT);
	}
	
	
	
	

}

package es.tappex.test;

import java.io.File;
import java.io.IOException;

import javax.script.ScriptException;

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

public class ParallelPublicityQueryEngineTest_KO_1Thread {
	
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
		req.setTimeOut(1000L);
		req.setResponse_type(Constants.APP_HTML);
		Response response = ParallelPublicityQueryEngine.processString(req,"aid="+req.getKey());
		assertEquals("responseID",response.getResponseId(), Constants.OK_NOCONTENT);
	}
	
	@Test
	//test unexpected url good key -- (1 thread)
	public void testPublicityQueryEngineTest_KO_BadUrlGoodKey() throws Exception{
		Request req = new Request();
		req.setNum_threads(1);
		req.setUrl("http://www.google.com");
		req.setKey("Tappx_3134_TriviaCrack_BM_Android");
		req.setTimeOut(1000L);
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
		req.setTimeOut(1000L);
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
		req.setTimeOut(1000L);
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
		req.setTimeOut(1000L);
		Response response = ParallelPublicityQueryEngine.processString(req,"aid="+req.getKey());
		assertEquals("responseId",response.getResponseId(), Constants.OK_NOCONTENT);
	}
	
	@Test
	//test input data is not recognizable -- (1 thread)
	public void testPublicityQueryEngineTest_KO_MalformedJSON() throws Exception{
		Request req = new Request();
		req.setNum_threads(1);
		req.setUrl("http://m2m1.inner-active.mobi/simpleM2M/clientRequestEnhancedHtmlAd");
		req.setKey("Tappx_3134_TriviaCrack_BM_Android");
		req.setTimeOut(10000L);
		req.setResponse_type(Constants.APP_HTML);
		Response response = ParallelPublicityQueryEngine.processString(req,"xxxxxxxxxx");
		assertEquals("responseId",response.getResponseId(), Constants.OTHER);
	}

	@Test
	//test input data timeout -- (1 thread)
	public void testPublicityQueryEngineTest_KO_TimeOut() throws Exception{
		Request req = new Request();
		req.setNum_threads(1);
		req.setUrl("http://m2m1.inner-active.mobi/simpleM2M/clientRequestEnhancedHtmlAd");
		req.setKey("Tappx_3134_TriviaCrack_BM_Android");
		req.setTimeOut(10L);
		req.setResponse_type(Constants.APP_HTML);
		Response response = ParallelPublicityQueryEngine.processString(req,"xxxxxxxxxx");
		assertEquals("responseId",response.getResponseId(), Constants.KO_SERVERFAULT);
	}
	
	
	
	
	

}

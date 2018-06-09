package es.tappex.bean;

/**
 * Response Data
 * @author Usuario
 *
 */

public class Response {
	
	private String threadId;
	private String responseId;
	private String responseData;
	private Long timeoutData;
	
	
	public Response() {
		threadId = "";
		responseId = "";
		responseData = "";
		timeoutData = 0L;
	}
	
	public Response(String _threadId, String _responseId, String _responseData) {
		threadId = _threadId;
		responseId = _responseId;
		responseData = _responseData;
	}
	
	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	public String getResponseId() {
		return responseId;
	}

	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}

	public String getResponseData() {
		return responseData;
	}

	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}
	
	public Long getTimeoutData() {
		return timeoutData;
	}

	public void setTimeoutData(Long timeoutData) {
		this.timeoutData = timeoutData;
	}

	
	
	//clone Response data.
	public void copy(Response copy) {
		
		this.threadId = copy.getThreadId();
		this.responseId = copy.getResponseId();
		this.responseData = copy.getResponseData();
		this.timeoutData = copy.getTimeoutData();
	}
	
	
	@Override
	public String toString() {
		return "threadId (" + this.threadId + ") -- responseId (" + this.responseId + ") -- timeOut ("+this.timeoutData+") ms -- data (\r\n" + this.responseData + "\r\n)";
	}
	
	

}

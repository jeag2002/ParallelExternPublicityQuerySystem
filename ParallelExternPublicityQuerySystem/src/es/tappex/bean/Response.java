package es.tappex.bean;

public class Response {
	
	

	private String threadId;
	private String responseId;
	private String responseData;
	
	public Response() {
		threadId = "";
		responseId = "";
		responseData = "";
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
	
	@Override
	public String toString() {
		return "threadId (" + this.threadId + ") -- responseId (" + this.responseId + ") -- data (\r\n" + this.responseData + "\r\n)";
	}
	
	

}

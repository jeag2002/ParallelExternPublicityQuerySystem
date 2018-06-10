package es.tappex.bean;

import java.io.File;

/**
 * Request Data (data necessary to create the threadpool and establish the URL connection)
 * @author Usuario
 *
 */
public class Request {
	
	private String url;
	private String key;
	private File fil;
	

	private String parser_type;
	private String validation_type;
	private String response_type;
	private String response_file;
	private Integer num_threads;
	private Long timeOut;
	
	public Request() {
		clear();
	}
	
	public void clear() {
		url = "";
		key = "";
		fil = new File("");
		
		parser_type = "";
		validation_type = "";
		
		response_type = "";
		response_file = "";
		
		num_threads = 0;
		timeOut = 0L;
		
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public File getFil() {
		return fil;
	}

	public void setFil(File fil) {
		this.fil = fil;
	}

	public String getResponse_type() {
		return response_type;
	}

	public void setResponse_type(String response_type) {
		this.response_type = response_type;
	}

	public String getResponse_file() {
		return response_file;
	}

	public void setResponse_file(String response_file) {
		this.response_file = response_file;
	}

	public Integer getNum_threads() {
		return num_threads;
	}

	public void setNum_threads(Integer num_threads) {
		this.num_threads = num_threads;
	}

	public Long getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Long timeOut) {
		this.timeOut = timeOut;
	}
	
	public String getParser_type() {
		return parser_type;
	}

	public void setParser_type(String parser_type) {
		this.parser_type = parser_type;
	}
	
	public String getValidation_type() {
		return validation_type;
	}

	public void setValidation_type(String validation_type) {
		this.validation_type = validation_type;
	}
	
	@Override
	public String toString() {
		return "url ::= (" + this.url + ") key ::= ("+ this.key + ") request_file ::= (" + fil.getName() + ") parser_type ::= (" + this.parser_type + ") validation_type ::= (" + this.validation_type + ") response_type ::= (" + this.response_type + ") response_file ::= (" + this.response_file + ") num_threads ::= (" + num_threads+ ") timeOut ::= (" + timeOut + ")";
	}
	

}

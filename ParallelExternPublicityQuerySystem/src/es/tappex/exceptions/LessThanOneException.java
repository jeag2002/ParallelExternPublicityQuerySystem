package es.tappex.exceptions;

/**
 * Exception. raised when there is a number less of one.
 * @author Usuario
 *
 */
public class LessThanOneException extends Exception{
	public LessThanOneException() { super(); }
	public LessThanOneException(String message) { super(message); }
	public LessThanOneException(String message, Throwable cause) { super(message, cause); }
	public LessThanOneException(Throwable cause) { super(cause); }
}


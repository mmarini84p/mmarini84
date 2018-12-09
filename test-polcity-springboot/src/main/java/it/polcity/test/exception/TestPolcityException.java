package it.polcity.test.exception;

/**
 * @author marco.marini
 *	Exception utilizzata per ritornare alcuni errori "custom" del modulo
 */
@SuppressWarnings("serial")
public class TestPolcityException extends Exception {

	public TestPolcityException() {
		super();
	}

	public TestPolcityException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TestPolcityException(String message, Throwable cause) {
		super(message, cause);
	}

	public TestPolcityException(String message) {
		super(message);
	}

	public TestPolcityException(Throwable cause) {
		super(cause);
	}
	
	

}

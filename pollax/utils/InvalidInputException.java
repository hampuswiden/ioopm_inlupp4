package pollax.utils;

import java.io.IOException;

public class InvalidInputException extends RuntimeException {
		/**
		 * The constructor for InvalidInputException
		 */
	    public InvalidInputException() {
	        super();
	    }
	    /**
		 * The constructor for InvalidInputException
		 * @param msg The string to be printed with the exception
		 */
	    public InvalidInputException(String msg) {
	        super(msg);
	    }
	}

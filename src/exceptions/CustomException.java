package exceptions;

public class CustomException extends Exception{
	/**
	 * A Custom Exception Class that can be used to throw an exception with a String message.
	 * This Class has a generated serial version id, see the serialVersionUID below.
	 */
	private static final long serialVersionUID = 8787280705781725384L;

	public CustomException () {}

    public CustomException (String message) {
        super (message);
    }

    public CustomException (Throwable cause) {
        super (cause);
    }

    public CustomException (String message, Throwable cause) {
        super (message, cause);
    }
}
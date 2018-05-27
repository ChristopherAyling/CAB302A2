package Stock;

/**
 * 
 * StockException class.
 * 
 * @author Christopher Ayling
 *
 */
@SuppressWarnings("serial")
public class StockException extends Exception {

	/**
	 * StockException.
	 */
	public StockException() {
	}

	/**
	 * StockException with message.
	 * @param message
	 */
	public StockException(String message) {
		super(message);
	}
}

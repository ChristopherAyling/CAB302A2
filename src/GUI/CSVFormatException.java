/**
 * 
 */
package GUI;

/**
 * To be used when a CSV is in the wrong format.
 * 
 * The expected format of CSVs is covered in the program requirements.
 * 
 * @author Christopher Ayling
 *
 */
@SuppressWarnings("serial")
public class CSVFormatException extends Exception {

	/**
	 * CSVFormatException
	 */
	public CSVFormatException() {
		super();
	}
	
	/**
	 * CSVFormatException with message.
	 * @param message
	 */
	public CSVFormatException(String message) {
		super(message);
	}
}
/**
 * 
 */
package GUI;

/**
 * To be used when a CSV is in the wrong format.
 * 
 * The expected format of CSVs is covered in the program requirements.
 * 
 * @author Chris
 *
 */
@SuppressWarnings("serial")
public class CSVFormatException extends Exception {

	public CSVFormatException() {
		super();
	}

	public CSVFormatException(String message) {
		super(message);
	}
}
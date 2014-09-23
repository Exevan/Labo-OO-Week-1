package exc;

public class NotLeantException extends Exception {
	
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "The specified Item is not leant.";
	}
	
}

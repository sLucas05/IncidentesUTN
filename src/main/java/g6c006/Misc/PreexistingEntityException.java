package g6c006.Misc;

@SuppressWarnings("all")
public class PreexistingEntityException extends Exception {
	public PreexistingEntityException(String message, Throwable cause) {
		super(message, cause);
	}

	public PreexistingEntityException(String message) {
		super(message);
	}
}

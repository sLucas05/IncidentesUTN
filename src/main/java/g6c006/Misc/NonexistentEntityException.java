package g6c006.Misc;

@SuppressWarnings("all")
public class NonexistentEntityException extends Exception {
	public NonexistentEntityException(String message, Throwable cause) {
		super(message, cause);
	}

	public NonexistentEntityException(String message) {
		super(message);
	}
}

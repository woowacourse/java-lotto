package error;

public class AppException extends IllegalArgumentException {
    public AppException(final ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}

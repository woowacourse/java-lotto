package coordinate.exception;

public class InvalidPointException extends RuntimeException {
    public InvalidPointException(String message){
        super(message);
    }

    public InvalidPointException(String message, Throwable cause){
        super(message, cause);
    }
}

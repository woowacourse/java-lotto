package lotto.domain.exception;

public class InvalidInputSizeException extends IllegalArgumentException{
    public InvalidInputSizeException(String message) {
        super(message);
    }
}
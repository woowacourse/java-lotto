package lotto.domain.exception;

public class EmptyOrNullException extends IllegalArgumentException{
    public EmptyOrNullException(String message) {
        super(message);
    }
}
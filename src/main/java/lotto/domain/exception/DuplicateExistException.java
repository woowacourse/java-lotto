package lotto.domain.exception;

public class DuplicateExistException extends IllegalArgumentException{
    public DuplicateExistException(String message) {
        super(message);
    }
}
package lotto.domain.exception;

public class DuplicateExistException extends IllegalArgumentException {
    private static final String DUPLICATE_EXIST_EXCEPTION_MESSAGE = "Duplicate exist.";

    public DuplicateExistException() {
        super(DUPLICATE_EXIST_EXCEPTION_MESSAGE);
    }
}
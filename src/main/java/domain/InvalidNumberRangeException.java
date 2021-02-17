package domain;

public class InvalidNumberRangeException extends RuntimeException {
    public InvalidNumberRangeException(String input) {
        super(input);
    }
}

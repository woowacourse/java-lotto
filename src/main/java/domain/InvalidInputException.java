package domain;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException() {
        super("숫자만 입력해주세요.");
    }
}

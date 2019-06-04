package lotto.exception;

public class NotRegisteredNumbersException extends RuntimeException {
    public NotRegisteredNumbersException() {
        super("생성정보가 등록되지 않았습니다.");
    }
}

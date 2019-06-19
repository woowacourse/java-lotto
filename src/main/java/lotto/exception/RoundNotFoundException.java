package lotto.exception;

public class RoundNotFoundException extends RuntimeException {
    public RoundNotFoundException() {
        super("해당 회차의 정보를 찾을 수 없습니다.");
    }
}

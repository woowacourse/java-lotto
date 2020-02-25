package lotto.exception;

public class WinningNumbersException extends IllegalArgumentException {
    public WinningNumbersException(String message) {
        super(message);
    }

    public WinningNumbersException() {
        this("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }
}

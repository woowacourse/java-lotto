package lotto.exception;

public class InvalidBonusNumberException extends RuntimeException {
    public InvalidBonusNumberException() {
        super("보너스 번호는 당첨번호와 중복될 수 없습니다.");
    }
}

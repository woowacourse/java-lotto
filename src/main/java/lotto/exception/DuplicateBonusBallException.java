package lotto.exception;

public class DuplicateBonusBallException extends RuntimeException{
    public DuplicateBonusBallException() {
        super("보너스 볼과 당첨번호는 겹치면 안됩니다.");
    }
}

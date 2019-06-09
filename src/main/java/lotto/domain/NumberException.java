package lotto.domain;

public class NumberException extends NumberFormatException {
    public NumberException() {
        super("숫자를 입력해주세요");
    }
}

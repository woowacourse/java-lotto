package lotto.model.exceptions;

public class IllegalMoneyException extends RuntimeException {
    private static String MESSAGE = "잘못된 돈 입니다!";

    public IllegalMoneyException() {
        super(MESSAGE);
    }
}

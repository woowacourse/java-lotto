package lotto.model.exceptions;

public class IllegalNumberCombinationException extends RuntimeException {
    private static String MESSAGE = "잘못도니 숫자 조합입니다";

    public IllegalNumberCombinationException() {
        super(MESSAGE);
    }
}

package lotto.domain;

public class NoMoneyException extends Exception {
    public NoMoneyException() {
    }

    public NoMoneyException(String message) {
        super(message);
    }
}
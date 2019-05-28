package lotto.domain;

public class InvalidMoney extends RuntimeException {
    public InvalidMoney(String message) {
        super(message);
    }
}

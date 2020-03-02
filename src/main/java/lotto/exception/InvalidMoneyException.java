package lotto.exception;

public class InvalidMoneyException extends RuntimeException {
    public InvalidMoneyException(int money) {
        super(money + " : 1000원 밑으로는 로또를 살 수 없습니다.");
    }
}

package lotto.util;

public class InvalidMoneyException extends RuntimeException {
    public InvalidMoneyException() {
        super("1000원 밑으로는 로또를 살 수 없습니다.");
    }
}

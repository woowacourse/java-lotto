package lotto.utils;

public class InvalidMoneyException extends RuntimeException {
    public InvalidMoneyException() {
        System.out.println("금액을 잘못 입력하셨습니다.");
    }
}

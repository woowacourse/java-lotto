package lotto.exception;

public class OutOfLottoPurchaseAmountBoundException extends RuntimeException{
    public OutOfLottoPurchaseAmountBoundException() {
        super("로또 구입 가능 금액의 범위를 벗어났습니다.");
    }
}

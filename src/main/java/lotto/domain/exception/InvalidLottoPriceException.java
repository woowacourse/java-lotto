package lotto.domain.exception;

public class InvalidLottoPriceException extends RuntimeException {
    public InvalidLottoPriceException(String money) {
        super(money + " -> 제대로 된 금액이 아닙니다.");
    }

    public InvalidLottoPriceException(int restMoney) {
        super(restMoney + " -> 1000원 단위로 입력해주세요.");
    }
}

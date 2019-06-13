package lotto.exception;

public class InvalidPurchaseInformationException extends RuntimeException {
    public static final String LOTTO_COUNT_BIGGER_THAN_ZERO_MSG = "수동 로또의 개수는 0이상이여야 합니다.";
    public static final String NOT_ENOUGH_MONEY_MSG = "구입 금액이 부족합니다.";

    public InvalidPurchaseInformationException(String message) {
        super(message);
    }
}
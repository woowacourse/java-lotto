package lotto.domain.exception;

public class InvalidLottoPurchaseMoneyException extends RuntimeException {
	private static final String INVALID_LOTTO_PURCHASE_MONEY_MESSAGE = "로또 구입 금액이 로또 한 장의 가격인 1000원 보다 작습니다.";

	public InvalidLottoPurchaseMoneyException() {
		super(INVALID_LOTTO_PURCHASE_MONEY_MESSAGE);
	}

	public InvalidLottoPurchaseMoneyException(String message) {
		super(message);
	}
}

package lotto.exceptions;

public class LottoMoneyIllegalArgumentException extends IllegalArgumentException {
	public static final String MESSAGE
			= "는 유효한 금액이 아닙니다. 금액은 0보다 큰 1000의 배수여야 합니다.";

	private final int invalidPurchaseMoney;

	public LottoMoneyIllegalArgumentException(final int invalidPurchaseMoney) {
		super(invalidPurchaseMoney + MESSAGE);
		this.invalidPurchaseMoney = invalidPurchaseMoney;
	}
}

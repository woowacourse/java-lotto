package domain;

import static constant.LottoConstant.PRICE_OF_LOTTO;

public class Money {

	private static final String DIVIDE_BY_THOUSAND = "1000원으로 나누어 떨어지는 금액을 입력해주세요.";
	private static final String CANT_PURCHASE = "구입금액이 부족합니다.";

	private final int value;

	public Money(int value) {
		validateMoney(value);
		this.value = value;
	}

	public void canPurchase(int count) {
		if (value < count * PRICE_OF_LOTTO) {
			throw new IllegalArgumentException(CANT_PURCHASE);
		}
	}

	public int calculateTotalCount() {
		return value / PRICE_OF_LOTTO;
	}

	public int getValue() {
		return value;
	}

	private void validateMoney(int money) {
		if (money % PRICE_OF_LOTTO != 0 || money < PRICE_OF_LOTTO) {
			throw new IllegalArgumentException(DIVIDE_BY_THOUSAND);
		}
	}
}

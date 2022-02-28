package domain;

import static constant.ErrorConstant.START_ERROR;
import static constant.LottoConstant.PRICE_OF_LOTTO;

public class Money {

	private static final String DIVIDE_BY_THOUSAND = START_ERROR + "1000원으로 나누어 떨어지는 금액을 입력해주세요.";
	private static final String CANT_PURCHASE = START_ERROR + "구입금액이 부족합니다.";

	private final int inputMoney;

	public Money(int inputMoney) {
		validateMoney(inputMoney);
		this.inputMoney = inputMoney;
	}

	public void canPurchase(int count) {
		if (inputMoney < count * PRICE_OF_LOTTO) {
			throw new IllegalArgumentException(CANT_PURCHASE);
		}
	}

	public int calculateCount() {
		return this.inputMoney / PRICE_OF_LOTTO;
	}

	private void validateMoney(int money) {
		if (money % PRICE_OF_LOTTO != 0 || money < PRICE_OF_LOTTO) {
			throw new IllegalArgumentException(DIVIDE_BY_THOUSAND);
		}
	}
}

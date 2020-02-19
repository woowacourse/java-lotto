package lotto.controller;

import lotto.domain.Money;

public class LottoController {
	public void start(String inputMoney) {
		Money money = new Money(inputMoney);
		int numberOfLotto = money.purchaseLotto();
	}
}

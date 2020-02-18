package lotto.domain;

public class LottoManager {
	private static final int LOTTO_PRICE = 1000;

	public static int calculateLottoAmount(Money money) {
		return money.getMoney() / LOTTO_PRICE;
	}
}

package lotto.domain.money;

public class LottoPrice {
	static final int UNIT = 1_000;

	public static int calculateCountOfLotto(Money money) {
		return money.calculateCountOfLotto(UNIT);
	}
}

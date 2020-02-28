package lotto.domain.lottomoney;

public class LottoPrice {
	static final int UNIT = 1_000;

	public static int calculateCountOfLotto(LottoMoney money) {
		return money.calculateCountOfLotto(UNIT);
	}
}

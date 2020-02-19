package lotto.domain;

public class LottoCount {
	private static final int MONEY_UNIT = 1000;
	private static final int MINIMUM_MONEY = 1000;

	private final int lottoCount;

	public LottoCount(int money) {
		validate(money);
		this.lottoCount = money / MONEY_UNIT;
	}

	private void validate(int money) {
		validateLackOf(money);
		validateMoneyUnit(money);
	}

	private void validateLackOf(int money) {
		if (money < MINIMUM_MONEY) {
			throw new IllegalArgumentException("돈이 부족합니다.");
		}
	}

	private void validateMoneyUnit(int money) {
		if (money % MONEY_UNIT != 0) {
			throw new IllegalArgumentException("천 원 단위로 입력하셔야 합니다.");
		}
	}
}

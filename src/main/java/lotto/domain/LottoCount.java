package lotto.domain;

public class LottoCount {
	private final int lottoCount;

	public LottoCount(int money) {
		validate(money);
		this.lottoCount = money / 1000;
	}

	private void validate(int money) {
		validateMoneyUnit(money);
	}

	private void validateMoneyUnit(int money) {
		if (money % 1000 != 0) {
			throw new IllegalArgumentException("천 원 단위로 입력하셔야 합니다.");
		}
	}
}

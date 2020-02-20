package lotto.domain;

public class Money {
	private final int lottoMoneyValue;

	public Money(final String lottoMoneyValue) {
		checkValidationOf(lottoMoneyValue);
		this.lottoMoneyValue = Integer.parseInt(lottoMoneyValue);
	}

	public int getLottoMoneyValue() {
		return this.lottoMoneyValue;
	}

	private void checkValidationOf(final String lottoMoneyValue) {
		if (isNotNumber(lottoMoneyValue)) {
			throw new IllegalArgumentException("금액은 숫자이어야 합니다.");
		}
		if (isEmpty(lottoMoneyValue)) {
			throw new IllegalArgumentException("금액은 공백이 될 수 없습니다.");
		}
	}

	private boolean isNotNumber(final String lottoMoneyValue) {
		return lottoMoneyValue.chars()
			.anyMatch(c -> !Character.isDigit(c));
	}

	private boolean isEmpty(final String lottoMoneyValue) {
		return lottoMoneyValue.equals("");
	}
}

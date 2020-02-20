package lotto.domain;

public class LottoPurchaseMoney {
	private static final int LOTTO_PRICE = 1000;

	private final int lottoPurchaseMoney;

	public LottoPurchaseMoney(String lottoPurchaseMoney) {
		validate(lottoPurchaseMoney);
		this.lottoPurchaseMoney = Integer.parseInt(lottoPurchaseMoney);
	}

	private void validate(String lottoPurchaseMoney) {
		if (isNotNumber(lottoPurchaseMoney)) {
			throw new IllegalArgumentException("숫자가 아닌 값을 입력하면 안됩니다.");
		}
		if (canNotBought(lottoPurchaseMoney)) {
			throw new IllegalArgumentException("로또를 구입하기에 충분하지 않은 값입니다.");
		}
	}

	private boolean isNotNumber(String lottoPurchaseMoney) {
		try {
			Integer.parseInt(lottoPurchaseMoney);
			return false;
		} catch (NumberFormatException e) {
			return true;
		}
	}

	private boolean canNotBought(String lottoPurchaseMoney) {
		return Integer.parseInt(lottoPurchaseMoney) < LOTTO_PRICE;
	}

	public int getBuyCount() {
		return lottoPurchaseMoney / LOTTO_PRICE;
	}

	public int getValue() {
		return lottoPurchaseMoney;
	}
}

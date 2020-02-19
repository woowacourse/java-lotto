package lotto.domain;

import java.util.Objects;

import lotto.exception.IllegalMoneyUnitException;

public class PurchaseMoney {
	public static final int MONEY_UNIT = 1000;
	public static final int ZERO = 0;
	public static final int MIN = 1000;
	public static final int LOWER_BOUND = MIN;
	public static final int UPPER_BOUND = 100000;
	public static final int LOTTO_PICEC_PRICE = 1000;

	private int purchaseMoney;

	public PurchaseMoney(String purchaseMoney) {
		validate(purchaseMoney);
		this.purchaseMoney = Integer.parseInt(purchaseMoney);
	}

	private int numberFormatValidate(String money) {
		try {
			return Integer.parseInt(money);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("문자열은 입력되지 않습니다.");
		}
	}

	private void validate(String purchaseMoney) {
		checkNull(purchaseMoney);
		checkBlank(purchaseMoney);
		int money = numberFormatValidate(purchaseMoney);
		checkMoneyUnit(money);
		checkMoneyRange(money);
	}

	private void checkMoneyRange(int money) {
		if (money > UPPER_BOUND || money < LOWER_BOUND) {
			throw new IllegalArgumentException("로또는 1000원에서 100,000원 까지만 구입 할 수 있습니다.");
		}
	}

	private void checkNull(String purchaseMoney) {
		if (Objects.isNull(purchaseMoney)) {
			throw new NullPointerException("널값은 입력되지 않습니다.");
		}
	}

	private void checkMoneyUnit(int purchaseMoney) {
		if (purchaseMoney % MONEY_UNIT != ZERO) {
			throw new IllegalMoneyUnitException("구입금액은 1000원단위로만 가능합니다.");
		}
	}

	private void checkBlank(String purchaseMoney) {
		if (purchaseMoney.trim().isEmpty()) {
			throw new IllegalArgumentException("공백은 입력되지 않습니다.");
		}
	}

	public int parseToPiece() {
		return purchaseMoney / LOTTO_PICEC_PRICE;
	}
}

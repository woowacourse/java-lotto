package lotto.domain;

public class Money {
	public static final int TO_PERCENT_VALUE = 100;
	public static final int PRICE_ONE_LOTTO = 1000;
	public static final String ERROR_MESSAGE_MIN_MONEY = "천원 이상의 금액만 가능합니다.";
	public static final String ERROR_MESSAGE_NOT_INTEGER = "숫자가 아닌 문자를 입력하였습니다.";

	private final int inputMoney;

	public Money(String inputMoney) {
		validateInteger(inputMoney);
		this.inputMoney = Integer.parseInt(inputMoney);
		validateOverThousand();
	}

	private void validateOverThousand() {
		if (this.inputMoney < PRICE_ONE_LOTTO) {
			throw new IllegalArgumentException(ERROR_MESSAGE_MIN_MONEY);
		}
	}

	private void validateInteger(String inputMoney) {
		try {
			Integer.parseInt(inputMoney);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(ERROR_MESSAGE_NOT_INTEGER);
		}
	}

	public int divideThousand() {
		return inputMoney / PRICE_ONE_LOTTO;
	}

	public int calculateIncomeRate(long income) {
		return (int)income / inputMoney * TO_PERCENT_VALUE;
	}
}

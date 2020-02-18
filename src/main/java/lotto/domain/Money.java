package lotto.domain;

public class Money {
	private final int inputMoney;

	public Money(String inputMoney) {
		validateInteger(inputMoney);
		this.inputMoney = Integer.parseInt(inputMoney);
		validateOverThousand();
	}

	private void validateOverThousand() {
		if (this.inputMoney < 1000) {
			throw new IllegalArgumentException("");
		}
	}

	private void validateInteger(String inputMoney) {
		try {
			Integer.parseInt(inputMoney);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("");
		}
	}

	public int divideThousand() {
		return inputMoney / 1000;
	}
}

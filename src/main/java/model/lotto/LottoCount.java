package model.lotto;

public class LottoCount {
	private static final int STANDARD_CAN_MAKE = 0;
	private static final int UNIT = 1000;
	private static final String LOTTO_COUNT_UNIT_ERROR_MESSAGE = "[Error]: 금액은 천원 단위여야 합니다.";

	private int count;

	// public LottoCount(String money) {
	// 	InputValidateUtils.inputBlank(money, LOTTO_COUNT_BLANK_ERROR_MESSAGE);
	// 	InputValidateUtils.inputNumber(money, LOTTO_COUNT_NUMBER_ERROR_MESSAGE);
	// 	this.count = makeMoneyToNumber(money);
	// }

	public LottoCount(int money) {
		validateThousandUnitInputMoney(money);
		this.count = money / UNIT;
	}

	private void validateThousandUnitInputMoney(int money) {
		if (money % UNIT != 0 || money == 0) {
			throw new IllegalArgumentException(LOTTO_COUNT_UNIT_ERROR_MESSAGE);
		}
	}

	public boolean haveRemainToMake() {
		return count > STANDARD_CAN_MAKE;
	}

	public void reduceCountOfRemain() {
		if (haveRemainToMake()) {
			count--;
		}
	}

	public int getCount() {
		return Integer.valueOf(count);
	}
}

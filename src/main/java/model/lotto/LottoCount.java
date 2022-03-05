package model.lotto;

import utils.InputValidateUtils;

public class LottoCount {
	public static final String CANT_MAKE_AMOUNT_ERROR_MESSAGE = "[Error]: 이 만큼의 로또를 생성할 수 없습니다.";

	private int count;

	public LottoCount(int count) {
		InputValidateUtils.checkInputIsNegative(count);
		this.count = count;
	}

	public void decreaseCount(int count) {
		checkCanDecrease(count);
		this.count -= count;
	}

	private void checkCanDecrease(int count) {
		if (this.count < count) {
			throw new IllegalArgumentException(CANT_MAKE_AMOUNT_ERROR_MESSAGE);
		}
	}

	public int getCount() {
		return count;
	}
}

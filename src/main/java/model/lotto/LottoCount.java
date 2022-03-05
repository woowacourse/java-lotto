package model.lotto;

import utils.InputValidateUtils;

public class LottoCount {
	public static final String CANT_MAKE_AMOUNT_ERROR_MESSAGE = "[Error]: 이 만큼의 로또를 생성할 수 없습니다.";

	private int count;
	//private int madeCount;

	public LottoCount(int count) {
		InputValidateUtils.inputNegative(count);
		this.count = count;
		//madeCount = 0;
	}

	// public boolean haveRemainToMake() {
	// 	return count > madeCount;
	// }
	//
	// public void increaseMadeLottoCount() {
	// 	if (haveRemainToMake()) {
	// 		madeCount++;
	// 	}
	// }

	public void deductCountForManual(int count) {
		checkCanMake(count);
		this.count -= count;
	}

	private void checkCanMake(int count) {
		if (this.count < count) {
			throw new IllegalArgumentException(CANT_MAKE_AMOUNT_ERROR_MESSAGE);
		}
	}

	public int getCount() {
		return count;
	}

	//public int getMadeCount() {
	// 	return madeCount;
	// }
}

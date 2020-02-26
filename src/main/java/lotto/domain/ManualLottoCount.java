package lotto.domain;

import static lotto.domain.LottoMoney.*;

public class ManualLottoCount {
	private final int count;

	public ManualLottoCount(String inputCount, int purchasedLottoCount) {
		int count = parseToInt(inputCount);
		validateNegative(count);
		validateBound(count, purchasedLottoCount);
		this.count = count;
	}

	private void validateBound(int count, int purchasedLottoCount) {
		if (count > purchasedLottoCount) {
			throw new InvalidManualLottoException(InvalidManualLottoException.OUT_OF_BOUND);
		}
	}

	private void validateNegative(int count) {
		if (count < ZERO) {
			throw new InvalidManualLottoException(InvalidManualLottoException.NOT_POSITIVE);
		}
	}

	private int parseToInt(String inputCount) {
		try {
			return Integer.parseInt(inputCount);
		} catch (NumberFormatException ne) {
			throw new InvalidManualLottoException(InvalidManualLottoException.NOT_INTEGER);
		}
	}

	public int getCount() {
		return count;
	}
}

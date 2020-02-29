package lotto.domain;

import lotto.domain.exception.InvalidLottoBuyCountException;

public class LottoBuyCount {
	private static final int MIN = 0;

	private final int manual;
	private final int auto;

	public LottoBuyCount(int manual, int auto) {
		validate(manual, auto);
		this.manual = manual;
		this.auto = auto;
	}

	private void validate(int manual, int auto) {
		if (manual < MIN || auto < MIN) {
			throw new InvalidLottoBuyCountException();
		}
	}

	public int getManual() {
		return manual;
	}

	public int getAuto() {
		return auto;
	}
}

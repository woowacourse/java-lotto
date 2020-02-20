package lotto.domain;

import java.util.Arrays;

public enum WinningType {
	FIRST_PLACE(6, false, 2000000000),
	SECOND_PLACE(5, true, 30000000),
	THIRD_PLACE(5, false, 1500000),
	FOURTH_PLACE(4, false, 50000),
	FIFTH_PLACE(3, false, 5000),
	NONE(-1, false, 0);

	private int sameNumberCount;
	private boolean bonus;
	private int winningAmount;

	WinningType(int sameNumberCount, boolean bonus, int winningAmount) {
		this.sameNumberCount = sameNumberCount;
		this.bonus = bonus;
		this.winningAmount = winningAmount;
	}

	public static WinningType getWinningType(int sameNumberCount, boolean bonus) {
		if (sameNumberCount == 5) {
			return distinguishSECOND_PLACEOrTHIRD_PLACE(bonus);
		}
		return Arrays.stream(WinningType.values())
				.filter(t -> t.sameNumberCount == sameNumberCount)
				.findFirst()
				.orElse(NONE);
	}

	private static WinningType distinguishSECOND_PLACEOrTHIRD_PLACE(boolean bonus) {
		if (bonus) {
			return SECOND_PLACE;
		}
		return THIRD_PLACE;
	}
}

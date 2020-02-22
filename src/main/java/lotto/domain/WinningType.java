package lotto.domain;

import java.util.Arrays;

public enum WinningType {
	FIRST_PLACE(6, BonusType.NO_MATTER, 2000000000),
	SECOND_PLACE(5, BonusType.TRUE, 30000000),
	THIRD_PLACE(5, BonusType.FALSE, 1500000),
	FOURTH_PLACE(4, BonusType.NO_MATTER, 50000),
	FIFTH_PLACE(3, BonusType.NO_MATTER, 5000),
	NONE(-1, BonusType.NO_MATTER, 0);

	private int sameNumberCount;
	private BonusType bonusType;
	private int winningAmount;

	WinningType(int sameNumberCount, BonusType bonusType, int winningAmount) {
		this.sameNumberCount = sameNumberCount;
		this.bonusType = bonusType;
		this.winningAmount = winningAmount;
	}

	public static WinningType getWinningType(int sameNumberCount, boolean isBonusMatching) {
		return Arrays.stream(WinningType.values())
				.filter(t -> (t.sameNumberCount == sameNumberCount)
						&& (t.bonusType.isMatching(isBonusMatching)))
				.findFirst()
				.orElse(NONE);
	}

	public boolean isBonusTRUE() {
		return bonusType == BonusType.TRUE;
	}

	public boolean isWinning() {
		return this != NONE;
	}

	public double calculateEarning(int num) {
		return winningAmount * num;
	}

	public int getSameNumberCount() {
		return sameNumberCount;
	}

	public int getWinningAmount() {
		return winningAmount;
	}
}

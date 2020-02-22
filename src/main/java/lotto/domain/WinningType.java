package lotto.domain;

import java.util.Arrays;

public enum WinningType {
	FIRST_PLACE(6, BonusRequirement.NO_MATTER, 2_000_000_000),
	SECOND_PLACE(5, BonusRequirement.TRUE, 30_000_000),
	THIRD_PLACE(5, BonusRequirement.FALSE, 1_500_000),
	FOURTH_PLACE(4, BonusRequirement.NO_MATTER, 50_000),
	FIFTH_PLACE(3, BonusRequirement.NO_MATTER, 5_000),
	NONE(-1, BonusRequirement.NO_MATTER, 0);

	private int sameNumberCount;
	private BonusRequirement bonusRequirement;
	private int winningAmount;

	WinningType(int sameNumberCount, BonusRequirement bonusRequirement, int winningAmount) {
		this.sameNumberCount = sameNumberCount;
		this.bonusRequirement = bonusRequirement;
		this.winningAmount = winningAmount;
	}

	public static WinningType getWinningType(int sameNumberCount, boolean isContainsBonus) {
		return Arrays.stream(WinningType.values())
				.filter(t -> (t.sameNumberCount == sameNumberCount)
						&& (t.bonusRequirement.isSatisfiedBy(isContainsBonus)))
				.findFirst()
				.orElse(NONE);
	}

	public boolean isBonusTRUE() {
		return bonusRequirement == BonusRequirement.TRUE;
	}

	public boolean isNONE() {
		return this == NONE;
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

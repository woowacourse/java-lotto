package lotto.domain;

import java.util.Arrays;

public enum WinningType {
	FIRST_PLACE(6, BonusRequirement.NO_MATTER, 2000000000),
	SECOND_PLACE(5, BonusRequirement.TRUE, 30000000),
	THIRD_PLACE(5, BonusRequirement.FALSE, 1500000),
	FOURTH_PLACE(4, BonusRequirement.NO_MATTER, 50000),
	FIFTH_PLACE(3, BonusRequirement.NO_MATTER, 5000),
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

	public int getSameNumberCount() {
		return sameNumberCount;
	}

	public BonusRequirement getBonusRequirement() {
		return bonusRequirement;
	}

	public int getWinningAmount() {
		return winningAmount;
	}

	public boolean isBonusTRUE() {
		if (bonusRequirement == BonusRequirement.TRUE) {
			return true;
		}
		return false;
	}
}

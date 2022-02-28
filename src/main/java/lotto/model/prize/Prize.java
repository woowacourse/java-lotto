package lotto.model.prize;

import java.util.Arrays;

public enum Prize {
	FIRST(6, 2_000_000_000, false),
	SECOND(5, 30_000_000, true),
	THIRD(5, 1_500_000, false),
	FOURTH(4, 50_000, false),
	FIFTH(3, 5_000, false),
	NONE(0, 0, false);

	private final int matchCount;
	private final int amount;
	private final boolean bonus;

	Prize(int matchCount, int amount, boolean bonus) {
		this.matchCount = matchCount;
		this.amount = amount;
		this.bonus = bonus;
	}

	public static Prize getPrize(MatchResult matchResult) {
		return Arrays.stream(values())
				.filter(prize -> matchResult.isCount(prize.matchCount) && matchResult.isBonus() == prize.bonus)
				.findFirst()
				.orElse(NONE);
	}

	public int pickAmount(int count) {
		return this.amount * count;
	}

	public int getMatchCount() {
		return matchCount;
	}

	public int getAmount() {
		return amount;
	}

	public boolean isBonus() {
		return bonus;
	}
}

package domain;

public enum WinningStatus {

	NOTHING(0, 0, false),
	THREE(3, 5000, false),
	FOUR(4, 50000, false),
	FIVE(5, 1500000, false),
	FIVE_AND_BONUS(5, 30000000, true),
	SIX(6, 2000000000, false);

	private final int numberMatches;
	private final int profit;
	private final boolean hitBonus;

	WinningStatus(int numberMatches, int profit, boolean hitBonus) {
		this.numberMatches = numberMatches;
		this.profit = profit;
		this.hitBonus = hitBonus;
	}

	public int getNumberMatches() {
		return this.numberMatches;
	}

	public boolean getHitBonus() {
		return this.hitBonus;
	}

	public int getProfit() {
		return this.profit;
	}

	public static WinningStatus of(int numberMatches, boolean hitBonus) {
		if (numberMatches == 5) {
			return withBonus(hitBonus);
		}
		if (numberMatches > 5) {
			return moreThanFive();
		}
		return lessThanFive(numberMatches);
	}

	private static WinningStatus withBonus(boolean hitBonus) {
		if (hitBonus) {
			return FIVE_AND_BONUS;
		}
		return FIVE;
	}

	private static WinningStatus moreThanFive() {
		return SIX;
	}

	private static WinningStatus lessThanFive(int numberMatches) {
		if (numberMatches == 4) {
			return FOUR;
		}
		if (numberMatches == 3) {
			return THREE;
		}
		return NOTHING;
	}
}

package domain;

import java.util.List;
import java.util.stream.Collectors;

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
		List<WinningStatus> filteredByNumberMatches = generatedByNumberMatch(numberMatches);
		if (filteredByNumberMatches.isEmpty()) {
			return NOTHING;
		}
		if (filteredByNumberMatches.size() > 1) {
			return generatedByHitBonus(filteredByNumberMatches, hitBonus);
		}
		return filteredByNumberMatches.get(0);
	}

	public static List<WinningStatus> getValues() {
		return List.of(NOTHING, THREE, FOUR, FIVE, FIVE_AND_BONUS, SIX);
	}

	private static List<WinningStatus> generatedByNumberMatch(int numberMatches) {
		return WinningStatus.getValues()
			.stream()
			.filter(winningStatus -> winningStatus.numberMatches == numberMatches)
			.collect(Collectors.toList());
	}

	private static WinningStatus generatedByHitBonus(List<WinningStatus> filteredByNumberMatches, boolean hitBonus) {
		return filteredByNumberMatches
			.stream()
			.filter(winningStatus -> winningStatus.hitBonus == hitBonus)
			.findFirst()
			.get();
	}
}

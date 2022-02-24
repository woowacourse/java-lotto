package domain;

import java.util.ArrayList;
import java.util.Arrays;
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
		List<WinningStatus> filteredByNumberMatches = Arrays.stream(WinningStatus.values())
			.filter(winningStatus -> winningStatus.numberMatches == numberMatches).collect(Collectors.toList());
		if (filteredByNumberMatches.isEmpty()) {
			return NOTHING;
		}
		if (filteredByNumberMatches.size() > 1) {
			return filteredByNumberMatches.stream().filter(winningStatus -> winningStatus.hitBonus == hitBonus)
				.findFirst().get();
		}
		return filteredByNumberMatches.get(0);
	}

	public static List<WinningStatus> getValues() {
		List<WinningStatus> winningStatuses = new ArrayList<>();
		winningStatuses.add(NOTHING);
		winningStatuses.add(THREE);
		winningStatuses.add(FOUR);
		winningStatuses.add(FIVE);
		winningStatuses.add(FIVE_AND_BONUS);
		winningStatuses.add(SIX);
		return winningStatuses;
	}
}

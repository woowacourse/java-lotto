package lotto.domain;

import java.util.Arrays;

public enum Rank {
	NO_WIN(0, 0),
	FIFTH(5_000, 3),
	FOURTH(50_000, 4),
	THIRD(1_500_000, 5),
	SECOND(30_000_000, 5),
	FIRST(2_000_000_000, 6);

	private final long reward;
	private final int hitCount;

	Rank(long reward, int hitCount) {
		this.reward = reward;
		this.hitCount = hitCount;
	}

	public static Rank findRank(int count, boolean isMatchBonus) {
		Rank rank = Arrays.stream(values())
			.filter(x -> x.hitCount == count)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException());
		if (rank.isSecond(isMatchBonus)) {
			rank = SECOND;
		}
		return rank;
	}

	private boolean isSecond(boolean isMatchBonus) {
		return this == THIRD && isMatchBonus;
	}

	public static Rank[] valuesOnlyWin() {
		return new Rank[] {FIFTH, FOURTH, THIRD, SECOND, FIRST};
	}

	public long getReward() {
		return reward;
	}

	public int getHitCount() {
		return hitCount;
	}

}

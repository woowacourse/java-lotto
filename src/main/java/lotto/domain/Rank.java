package lotto.domain;

import java.util.stream.Stream;

public enum Rank {
	FIRST(6, 2_000_000_000),
	SECOND(5, 30_000_000),
	THIRD(5, 1_500_000),
	FOURTH(4, 50_000),
	FIFTH(3, 5_000);

	private int hitCount;
	private int reward;

	Rank(int hitCount, int reward) {
		this.hitCount = hitCount;
		this.reward = reward;
	}

	public static Rank getRank(int hitCount, boolean hasBonusNumber) {
		Rank rank = Stream.of(Rank.values())
			.filter(x -> x.getHitCount() == hitCount)
			.findFirst()
			.orElseThrow(IllegalAccessError::new);

		if (rank == SECOND && !hasBonusNumber) {
			return THIRD;
		}

		return rank;
	}

	public int getHitCount() {
		return hitCount;
	}

	public int getReward() {
		return reward;
	}
}

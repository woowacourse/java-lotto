package domain;

import java.util.Arrays;

public enum Rank {
	NOTHING(0, 0, false),
	FIFTH(5_000, 3, false),
	FOURTH(50_000, 4, false),
	THIRD(150_000, 5, false),
	SECOND(30_000_000, 5, true),
	FIRST(2_000_000_000, 6, false);

	private final int money;
	private final int match;
	private final boolean bonus;

	Rank(int money, int match, boolean bonus) {
		this.money = money;
		this.match = match;
		this.bonus = bonus;
	}

	public static Rank of(int targetMatch, boolean bonus) {
		return Arrays.stream(Rank.values())
			.filter(rank -> rank.match == targetMatch)
			.filter(rank -> rank != Rank.THIRD || !bonus)
			.findFirst()
			.orElse(NOTHING);
	}

	public boolean isNothing() {
		return this == NOTHING;
	}

	public int calculateMoney(int count) {
		return this.money * count;
	}

	public boolean isBonus() {
		return this.bonus;
	}

	public int getMoney() {
		return money;
	}

	public int getMatch() {
		return match;
	}
}

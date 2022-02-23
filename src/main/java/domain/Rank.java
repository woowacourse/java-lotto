package domain;

import java.util.Arrays;

public enum Rank {
	NOTHING(0, 0, false),
	FIFTH(5000, 3, false),
	FOURTH(50000, 4, false),
	THIRD(150000, 5, false),
	SECOND(30000000, 5, true),
	FIRST(2000000000, 6, false);

	private final int money;
	private final int match;
	private final boolean bonus;

	Rank(int money, int match, boolean bonus) {
		this.money = money;
		this.match = match;
		this.bonus = bonus;
	}

	public static Rank getRank(int target, boolean bonus) {
		return Arrays.stream(Rank.values())
			.filter(rank -> rank.match == target)
			.filter(rank -> rank.bonus == bonus)
			.findFirst()
			.orElse(NOTHING);
	}

	public static boolean isNotNothing(Rank rank) {
		return rank != Rank.NOTHING;
	}

	public int getMoney() {
		return money;
	}

	public int getMatch() {
		return match;
	}
}

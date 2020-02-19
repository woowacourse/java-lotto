package lotto.domain.result;

import java.util.Arrays;

public enum Rank {
	DEFAULT(0, 0),
	THREE(3, 5000),
	FOUR(4, 50000),
	FIVE(5, 150000),
	BONUS(5, 3000000),
	SIX(6, 2000000000);

	private final int matchingNumbers;
	private final int prize;
	private int count = 0;

	Rank(int matchingNumbers, int prize) {
		this.matchingNumbers = matchingNumbers;
		this.prize = prize;
	}

	public static Rank getRank(int numberOfMatch) {
		return Arrays.stream(values())
			.filter(rank -> rank.matchingNumbers == numberOfMatch)
			.findFirst()
			.orElse(DEFAULT);
	}

	public void count() {
		this.count++;
	}
}

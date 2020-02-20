package lotto.domain.result;

import java.util.Arrays;


public enum Statistic {
	THREE(3, 5000),
	FOUR(4, 50000),
	FIVE(5, 150000),
	BONUS(5, 3000000),
	SIX(6, 2000000000);

	private final int matchingNumbers;
	private final int prize;
	private int count = 0;

	Statistic(int matchingNumbers, int prize) {
		this.matchingNumbers = matchingNumbers;
		this.prize = prize;
	}

	public static Statistic getRank(int numberOfMatch) throws Exception {
		return Arrays.stream(values())
			.filter(statistic -> statistic.matchingNumbers == numberOfMatch)
			.findFirst()
			.orElseThrow(Exception::new);
	}

	public void count() {
		this.count++;
	}

	public double getProfit() {
		return this.prize * this.count;
	}

	public int getMatchingNumbers() {
		return matchingNumbers;
	}

	public int getPrize() {
		return prize;
	}

	public int getCount() {
		return count;
	}
}

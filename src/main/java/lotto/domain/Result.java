package lotto.domain;

import java.util.Arrays;
import java.util.List;

public enum Result {
	FIVE(5000, 3, false),
	FOUR(50000, 4, false),
	THREE(1500000, 5, false),
	TWO(30000000, 5, true),
	ONE(2000000000, 6, false);

	private long reward;
	private int hitCount;
	private boolean isBonus;

	Result(long reward, int hitCount, boolean isBonus) {
		this.reward = reward;
		this.hitCount = hitCount;
		this.isBonus = isBonus;
	}

	public static Result of(int count, boolean isBonus) {
		return Arrays.stream(values())
			.filter(x -> x.hitCount == count && x.isBonus == isBonus)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(""));
	}

	public static long sum(List<Result> results) {
		return results.stream()
			.map(x -> x.reward)
			.reduce((x, y) -> x + y).get();
	}

}

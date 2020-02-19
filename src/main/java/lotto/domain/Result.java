package lotto.domain;

import java.util.Arrays;

public enum Result {
	FIVE(0, 5000, 3),
	FOUR(0, 50000, 4),
	THREE(0, 1500000, 5),
	TWO(0, 30000000, 5),
	ONE(0, 2000000000, 6);

	private int count;
	private long reward;
	private int hitCount;

	Result(int count, long reward, int hitCount) {
		this.count = count;
		this.reward = reward;
		this.hitCount = hitCount;
	}

	public static Result of(int count) {
		return Arrays.stream(values())
			.filter(x -> x.hitCount == count)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(""));
	}

	public void countPlus() {
		this.count += 1;
	}
}

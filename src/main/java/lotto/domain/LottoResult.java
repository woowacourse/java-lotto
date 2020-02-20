package lotto.domain;

import java.util.Arrays;

public enum LottoResult {
	FIVE(0, 5000, 3),
	FOUR(0, 50000, 4),
	THREE(0, 1500000, 5),
	TWO(0, 30000000, 5),
	ONE(0, 2000000000, 6);

	private int count;
	private long reward;
	private int hitCount;

	LottoResult(int count, long reward, int hitCount) {
		this.count = count;
		this.reward = reward;
		this.hitCount = hitCount;
	}

	public static LottoResult of(int count) {
		return Arrays.stream(values())
			.filter(x -> x.hitCount == count)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(""));
	}

	public static long calculateTotalReward() {
		return Arrays.stream(values())
			.map(x -> x.reward * x.count)
			.reduce((x, y) -> x + y)
			.get();
	}

	public void countPlus() {
		this.count++;
	}

	public int getCount() {
		return count;
	}

	public long getReward() {
		return reward;
	}

	public int getHitCount() {
		return hitCount;
	}
}

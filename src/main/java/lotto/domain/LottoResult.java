package lotto.domain;

import java.util.Arrays;

public enum LottoResult {
	FIFTH(0, 5000, 3),
	FOURTH(0, 50000, 4),
	THIRD(0, 1500000, 5),
	SECOND(0, 30000000, 5),
	FIRST(0, 2000000000, 6);

	public static final String ERROR_MESSAGE_NOT_FOUND_RANK = "당첨되지 않았습니다.";

	private int count;
	private long reward;
	private int hitCount;

	LottoResult(int count, long reward, int hitCount) {
		this.count = count;
		this.reward = reward;
		this.hitCount = hitCount;
	}

	public static LottoResult findRank(int count) {
		return Arrays.stream(values())
			.filter(x -> x.hitCount == count)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE_NOT_FOUND_RANK));
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

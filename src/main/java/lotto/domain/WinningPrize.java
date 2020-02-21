package lotto.domain;

import java.util.Arrays;

public enum WinningPrize {
	NO_PRIZE(0, 0, "미당첨"),
	FIFTH(3, 5_000, "5등"),
	FOURTH(4, 50_000, "4등"),
	THIRD(5, 1_500_000, "3등"),
	SECOND(5, 30_000_000, "2등(보너스볼 일치)"),
	FIRST(6, 2_000_000_000, "1등");

	private int matchCount;
	private int prize;
	private String description;

	WinningPrize(int matchCount, int prize, String description) {
		this.matchCount = matchCount;
		this.prize = prize;
		this.description = description;
	}

	public static WinningPrize of(int matchCount, boolean bonusMatch) {
		if (matchCount == 5 && bonusMatch) {
			return SECOND;
		}
		return Arrays.stream(values())
			.filter(prize -> prize.matchCount == matchCount)
			.findFirst()
			.orElse(NO_PRIZE);
	}

	public int getMatchCount() {
		return matchCount;
	}

	public int getPrize() {
		return prize;
	}

	public String getDescription() {
		return description;
	}
}

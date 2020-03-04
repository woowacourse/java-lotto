package lotto.domain.result;

import java.util.stream.Stream;

public enum Rank {
	FIRST_PLACE(6, BonusType.NO_MATTER, 2_000_000_000, "6개 일치 (2000000000원)"),
	SECOND_PLACE(5, BonusType.TRUE, 300_000_000, "5개 일치, 보너스볼 일치 (300000000원)"),
	THIRD_PLACE(5, BonusType.FALSE, 15_000_000, "5개 일치 (15000000원)"),
	FOURTH_PLACE(4, BonusType.NO_MATTER, 50_000, "4개 일치 (50000원)"),
	FIFTH_PLACE(3, BonusType.NO_MATTER, 5_000, "3개 일치 (5000원)"),
	NONE(-1, BonusType.NO_MATTER, 0, "0~2개 일치 (0원)");

	private int matchingNumberCount;
	private BonusType bonus;
	private int reward;
	private String information;

	Rank(int matchingNumberCount, BonusType bonus, int reward, String information) {
		this.matchingNumberCount = matchingNumberCount;
		this.bonus = bonus;
		this.reward = reward;
		this.information = information;
	}

	public static Rank matching(int matchingNumberCount, boolean isBonusMatching) {
		return Stream.of(Rank.values())
				.filter(rank -> rank.matchingNumberCount == matchingNumberCount)
				.filter(rank -> rank.bonus.isEqualTo(isBonusMatching))
				.findFirst()
				.orElse(NONE);
	}

	public int calculateReward(int count) {
		return reward * count;
	}

	public boolean isWinning() {
		return this != NONE;
	}

	public String getInformation() {
		return information;
	}
}

package lotto.domain;

import java.util.Arrays;

/**
 * Rank 클래스
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public enum Rank {
    EIGHTH(0, false, 0),
    SEVENTH(1, false, 0),
    SIXTH(2, false, 0),
    FIFTH(3, false, 5_000),
    FOURTH(4, false, 50_000),
    THIRD(5, false, 1_500_000),
    SECOND(5, true, 30_000_000),
    FIRST(6, false, 2_000_000_000);

	private static final int MATCH_COUNT_RELATED_TO_BONUS = 5;

    private int matchCounts;
	private boolean hasBonus;
	private int reward;

    Rank(int matchCounts, boolean hasBonus, final int reward) {
		this.matchCounts = matchCounts;
		this.hasBonus = hasBonus;
		this.reward = reward;
	}

	public static Rank getRank(int matchCount, boolean hasBonus) {
        if (hasBonus && matchCount == 5) {
			return Rank.SECOND;
		}
        return getRankNotRelatedToBonus(matchCount);
	}

	private static Rank getRankNotRelatedToBonus(int matchCount) {
		return Arrays.stream(Rank.values())
            .filter(value -> value.matchCounts == matchCount)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("로또의 등수를 구할 수 없습니다."));
	}

	public int getReward() {
		return this.reward;
	}

    public int getMatchCounts() {
		return this.matchCounts;
	}
}

package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Rank 클래스
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public enum Rank {
	FIRST(new ArrayList<Integer>(Collections.singletonList(6)), false, 2000000000),
	SECOND(new ArrayList<Integer>(Collections.singletonList(5)), true, 30000000),
	THIRD(new ArrayList<Integer>(Collections.singletonList(5)), false, 1500000),
	FOURTH(new ArrayList<Integer>(Collections.singletonList(4)), false, 50000),
	FIFTH(new ArrayList<Integer>(Collections.singletonList(3)), false, 5000),
	SIXTH(new ArrayList<Integer>(Arrays.asList(0, 1, 2)), false, 0);

	private static final int MATCH_COUNT_RELATED_TO_BONUS = 5;

	private List<Integer> matchCounts;
	private boolean hasBonus;
	private int reward;

	Rank(List<Integer> matchCounts, boolean hasBonus, final int reward) {
		this.matchCounts = matchCounts;
		this.hasBonus = hasBonus;
		this.reward = reward;
	}

	public static Rank getRank(int matchCount, boolean hasBonus) {
		if (matchCount != MATCH_COUNT_RELATED_TO_BONUS) {
 			return getRankNotRelatedToBonus(matchCount);
		}

		if (hasBonus) {
			return Rank.SECOND;
		}
		return Rank.THIRD;
	}

	private static Rank getRankNotRelatedToBonus(int matchCount) {
		return Arrays.stream(Rank.values())
				.filter(value -> value.matchCounts.contains(matchCount))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("로또의 등수를 구할 수 없습니다."));
	}

	public int getReward() {
		return this.reward;
	}

	public List<Integer> getMatchCounts() {
		return this.matchCounts;
	}
}

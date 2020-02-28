package lotto.domain.result;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 로또 결과에 해당되는 등수를 나타내는 이넘
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public enum Rank {
	FIRST(new ArrayList<>(Collections.singletonList(6)),2_000_000_000),
	SECOND(new ArrayList<>(Collections.singletonList(5)), 30_000_000),
	THIRD(new ArrayList<>(Collections.singletonList(5)), 1_500_000),
	FOURTH(new ArrayList<>(Collections.singletonList(4)), 50_000),
	FIFTH(new ArrayList<>(Collections.singletonList(3)), 5_000),
	SIXTH(new ArrayList<>(Arrays.asList(0, 1, 2)), 0);

	private static final int MATCH_COUNT_RELATED_TO_BONUS = 5;
	private static final String CAN_NOT_GET_RANK_EXCEPTION_MESSAGE = "로또의 등수를 구할 수 없습니다.";

	private List<Integer> matchCounts;
	private int reward;

	Rank(List<Integer> matchCounts, final int reward) {
		this.matchCounts = matchCounts;
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
				.orElseThrow(() -> new IllegalArgumentException(CAN_NOT_GET_RANK_EXCEPTION_MESSAGE));
	}

	public static List<Rank> getReversedProfitableValues() {
		return Lists.reverse(Stream.of(values()).limit(5).collect(Collectors.toList()));
	}

	public int getReward() {
		return this.reward;
	}

	public int getMatchCounts() {
		return this.matchCounts.get(0);
	}
}

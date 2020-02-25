package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

/**
 * 로또 순위 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/19
 */
public enum LottoRank {
	FIRST(MatchCount.of(6), new LottoPurchaseMoney(2_000_000_000L),
			(matchCount, isBonus) -> MatchCount.of(6).equals(matchCount) && !isBonus),
	SECOND(MatchCount.of(5), new LottoPurchaseMoney(30_000_000L),
			(matchCount, isBonus) -> MatchCount.of(5).equals(matchCount) && isBonus),
	THIRD(MatchCount.of(5), new LottoPurchaseMoney(1_500_000L)),
	FOURTH(MatchCount.of(4), new LottoPurchaseMoney(50_000L)),
	FIFTH(MatchCount.of(3), new LottoPurchaseMoney(5_000L)),
	MISS(MatchCount.of(0), new LottoPurchaseMoney(0), (matchCount, isBonus) -> true);

	public static final String INVALID_RANK_MESSAGE = "식별할 수 없는 순위입니다.";

	private final MatchCount matchCount;
	private final LottoPurchaseMoney winnings;
	private final BiPredicate<MatchCount, Boolean> match;

	LottoRank(MatchCount matchCount, LottoPurchaseMoney winnings) {
		this(matchCount, winnings, (count, isBonus) -> matchCount.equals(count));
	}

	LottoRank(MatchCount matchCount, LottoPurchaseMoney winnings, BiPredicate<MatchCount, Boolean> match) {
		this.matchCount = matchCount;
		this.winnings = winnings;
		this.match = match;
	}

	public static LottoRank of(MatchCount matchCount, boolean isBonus) {
		return Arrays.stream(values())
				.filter(rank -> rank.isMatch(matchCount, isBonus))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(INVALID_RANK_MESSAGE));
	}

	public static List<LottoRank> valuesAsReverse() {
		return Lists.reverse(Stream.of(values()).collect(Collectors.toList()));
	}

	private boolean isMatch(MatchCount matchCount, boolean isBonus) {
		return match.test(matchCount, isBonus);
	}

	public long calculateTotalWinnings(long amount) {
		return winnings.multiply(amount);
	}

	public MatchCount getMatchCount() {
		return matchCount;
	}

	public LottoPurchaseMoney getWinnings() {
		return winnings;
	}
}

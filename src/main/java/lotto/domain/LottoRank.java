package lotto.domain;

import static lotto.domain.BonusBallStatus.*;
import static lotto.domain.MatchCount.*;

import java.util.Arrays;

public enum LottoRank {
	FIFTH(THREE, INCLUDING_OR_NOT, Prize.of(5000)),
	FOURTH(FOUR, INCLUDING_OR_NOT, Prize.of(50000)),
	THIRD(FIVE, NOT_INCLUDING, Prize.of(1_500_000)),
	SECOND(FIVE, INCLUDING, Prize.of(30_000_000)),
	FIRST(SIX, NOT_INCLUDING, Prize.of(2_000_000_000));

	private static final String THERE_IS_NON_RANK_EXCEPTION_MESSAGE = "꽝!";

	private final MatchCount matchCount;
	private final BonusBallStatus bonusBallStatus;
	private final Prize prize;

	LottoRank(MatchCount matchCount, BonusBallStatus bonusBallStatus, Prize prize) {
		this.matchCount = matchCount;
		this.bonusBallStatus = bonusBallStatus;
		this.prize = prize;
	}

	public static boolean isValidMatchCount(int matchCount) {
		return Arrays.stream(values())
			.anyMatch(rank -> rank.isMatch(matchCount));
	}

	public static LottoRank findRank(int matchCount, boolean isBonusBall) {
		return Arrays.stream(values())
			.filter(rank -> rank.isMatch(matchCount))
			.filter(rank -> rank.isRightBonusBallStatus(isBonusBall))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(THERE_IS_NON_RANK_EXCEPTION_MESSAGE));
	}

	private boolean isMatch(int count) {
		return matchCount.isSameMatch(count);
	}

	private boolean isRightBonusBallStatus(boolean isBonusBall) {
		return bonusBallStatus.contains(isBonusBall);
	}

	public Prize calculateTotalPrize(long count) {
		return prize.multiply(count);
	}

	public MatchCount getMatchCount() {
		return matchCount;
	}

	public Prize getPrize() {
		return prize;
	}
}

package lotto.domain;

import static lotto.domain.IncludingBonusBallCondition.*;
import static lotto.domain.MatchCount.*;

import java.util.Arrays;

public enum LottoRank {
	FIFTH(THREE, NO_MATTER, Money.valueOf(5_000)),
	FOURTH(FOUR, NO_MATTER, Money.valueOf(50_000)),
	THIRD(FIVE, MUST_NOT_INCLUDE, Money.valueOf(1_500_000)),
	SECOND(FIVE, MUST_INCLUDE, Money.valueOf(30_000_000)),
	FIRST(SIX, MUST_NOT_INCLUDE, Money.valueOf(2_000_000_000));

	private static final String THERE_IS_NON_RANK_EXCEPTION_MESSAGE = "꽝!";

	private final MatchCount matchCount;
	private final IncludingBonusBallCondition includingBonusBallCondition;
	private final Money money;

	LottoRank(MatchCount matchCount, IncludingBonusBallCondition includingBonusBallCondition, Money money) {
		this.matchCount = matchCount;
		this.includingBonusBallCondition = includingBonusBallCondition;
		this.money = money;
	}

	public static boolean isValidMatchCount(int matchCount) {
		return Arrays.stream(values())
			.anyMatch(rank -> rank.isMatch(matchCount));
	}

	public static LottoRank findRank(int matchCount, boolean isBonusBall) {
		return Arrays.stream(values())
			.filter(rank -> rank.isMatch(matchCount) && rank.isRightBonusBallCondition(isBonusBall))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(THERE_IS_NON_RANK_EXCEPTION_MESSAGE));
	}

	public static boolean isNotRightResultSize(int size) {
		return values().length != size;
	}

	private boolean isMatch(int count) {
		return matchCount.isSameMatch(count);
	}

	private boolean isRightBonusBallCondition(boolean isBonusBall) {
		return includingBonusBallCondition.isAcceptableBonusCondition(isBonusBall);
	}

	public Money calculateTotalMoney(long count) {
		return money.multiply(count);
	}

	public MatchCount getMatchCount() {
		return matchCount;
	}

	public Money getMoney() {
		return money;
	}
}

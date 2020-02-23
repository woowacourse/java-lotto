package lotto.domain;

import static lotto.domain.IncludingBonusBallCondition.*;
import static lotto.domain.MatchNumberCount.*;

import java.util.Arrays;

public enum LottoRank {
	MISSING(ZERO, NO_MATTER, Money.valueOf(0)),
	FIFTH(THREE, NO_MATTER, Money.valueOf(5_000)),
	FOURTH(FOUR, NO_MATTER, Money.valueOf(50_000)),
	THIRD(FIVE, MUST_NOT_INCLUDE, Money.valueOf(1_500_000)),
	SECOND(FIVE, MUST_INCLUDE, Money.valueOf(30_000_000)),
	FIRST(SIX, MUST_NOT_INCLUDE, Money.valueOf(2_000_000_000));

	private final MatchNumberCount matchNumberCount;
	private final IncludingBonusBallCondition includingBonusBallCondition;
	private final Money money;

	LottoRank(MatchNumberCount matchNumberCount, IncludingBonusBallCondition includingBonusBallCondition, Money money) {
		this.matchNumberCount = matchNumberCount;
		this.includingBonusBallCondition = includingBonusBallCondition;
		this.money = money;
	}

	public static LottoRank findRank(int matchCount, boolean isBonusBall) {
		return Arrays.stream(values())
			.filter(rank -> rank.isMatch(matchCount) && rank.isRightBonusBallCondition(isBonusBall))
			.findFirst()
			.orElse(MISSING);
	}

	private boolean isMatch(int count) {
		return matchNumberCount.isSameMatch(count);
	}

	private boolean isRightBonusBallCondition(boolean isBonusBall) {
		return includingBonusBallCondition.isAcceptableBonusCondition(isBonusBall);
	}

	boolean isPrizingRank() {
		return this != MISSING;
	}

	public Money calculateTotalMoney(long count) {
		return money.multiply(count);
	}

	public MatchNumberCount getMatchNumberCount() {
		return matchNumberCount;
	}

	public Money getMoney() {
		return money;
	}
}

package lotto.domain;

import static lotto.domain.IncludingBonusBallCondition.*;

import java.util.Arrays;

public enum LottoRank {
	MISSING(0, NO_MATTER, Money.valueOf(0)),
	FIFTH(3, NO_MATTER, Money.valueOf(5_000)),
	FOURTH(4, NO_MATTER, Money.valueOf(50_000)),
	THIRD(5, MUST_NOT_INCLUDE, Money.valueOf(1_500_000)),
	SECOND(5, MUST_INCLUDE, Money.valueOf(30_000_000)),
	FIRST(6, MUST_NOT_INCLUDE, Money.valueOf(2_000_000_000));

	private final int matchCount;
	private final IncludingBonusBallCondition includingBonusBallCondition;
	private final Money money;

	LottoRank(int matchCount, IncludingBonusBallCondition includingBonusBallCondition, Money money) {
		this.matchCount = matchCount;
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
		return matchCount == count;
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

	public int getMatchCount() {
		return matchCount;
	}

	public Money getMoney() {
		return money;
	}
}

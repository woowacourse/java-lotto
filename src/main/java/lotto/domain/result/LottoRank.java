package lotto.domain.result;

import java.util.Arrays;

import lotto.domain.ticket.Money;

public enum LottoRank {
	MISSING(0, false, Money.valueOf(0)),
	FIFTH(3, false, Money.valueOf(5_000)),
	FOURTH(4, false, Money.valueOf(50_000)),
	THIRD(5, false, Money.valueOf(1_500_000)),
	SECOND(5, true, Money.valueOf(30_000_000)),
	FIRST(6, false, Money.valueOf(2_000_000_000));

	private final int matchCount;
	private final boolean hasToHaveBonusBall;
	private final Money prize;

	LottoRank(int matchCount, boolean hasToHaveBonusBall, Money prize) {
		this.matchCount = matchCount;
		this.hasToHaveBonusBall = hasToHaveBonusBall;
		this.prize = prize;
	}

	static LottoRank ofValue(int matchCount, boolean hasBonusBall) {
		return Arrays.stream(values())
			.filter(rank -> rank.isMatch(matchCount) && rank.hasRightBonusCondition(hasBonusBall))
			.findFirst()
			.orElse(MISSING);
	}

	private boolean isMatch(int count) {
		return matchCount == count;
	}

	private boolean hasRightBonusCondition(boolean hasBonusBall) {
		if (this == SECOND || this == THIRD) {
			return this.hasToHaveBonusBall == hasBonusBall;
		}
		return true;
	}

	boolean isPrizingRank() {
		return this != MISSING;
	}

	Money calculateTotalMoney(long count) {
		return prize.multiply(count);
	}

	public int getMatchCount() {
		return matchCount;
	}

	public Money getPrize() {
		return prize;
	}
}

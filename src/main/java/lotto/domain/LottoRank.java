package lotto.domain;

import static lotto.domain.BonusBallStatus.*;
import static lotto.domain.MatchCount.*;

import java.util.Arrays;

public enum LottoRank {
	FIFTH(THREE, INCLUDING_OR_NOT, Money.of(5000)),
	FOURTH(FOUR, INCLUDING_OR_NOT, Money.of(50000)),
	THIRD(FIVE, NOT_INCLUDING, Money.of(1_500_000)),
	SECOND(FIVE, INCLUDING, Money.of(30_000_000)),
	FIRST(SIX, NOT_INCLUDING, Money.of(2_000_000_000));

	private static final String THERE_IS_NON_RANK_EXCEPTION_MESSAGE = "꽝!";

	private final MatchCount matchCount;
	private final BonusBallStatus bonusBallStatus;
	private final Money money;

	LottoRank(MatchCount matchCount, BonusBallStatus bonusBallStatus, Money money) {
		this.matchCount = matchCount;
		this.bonusBallStatus = bonusBallStatus;
		this.money = money;
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

	public static boolean isNotRightResultSize(int size) {
		return values().length == size;
	}

	private boolean isMatch(int count) {
		return matchCount.isSameMatch(count);
	}

	private boolean isRightBonusBallStatus(boolean isBonusBall) {
		return bonusBallStatus.contains(isBonusBall);
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

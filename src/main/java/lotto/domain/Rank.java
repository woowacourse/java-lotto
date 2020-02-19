package lotto.domain;

import java.util.stream.Stream;

public enum Rank {
	FIRST(6, 2000000000),
	SECOND(5, 30000000),
	THIRD(5, 1500000),
	FOURTH(4, 50000),
	FIFTH(3, 5000),
	NONE(0, 0);

	public static final int SECOND_OR_THIRD_MATCH = 5;

	private final int matchCount;
	private final int amount;

	Rank(int matchCount, int amount) {
		this.matchCount = matchCount;
		this.amount = amount;
	}

	public static Rank of(LottoTicket lottoTicket, WinningNumbers winningNumbers) {
		final int matchCount = getMatchCount(lottoTicket, winningNumbers);
		if (isThirdPrizeCondition(lottoTicket, winningNumbers, matchCount)) {
			return THIRD;
		}
		return Stream.of(Rank.values())
			.filter(rank -> rank.matchCount == matchCount)
			.findFirst()
			.orElse(NONE);
	}

	private static boolean isThirdPrizeCondition(LottoTicket lottoTicket, WinningNumbers winningNumbers,
		int matchCount) {
		return matchCount == SECOND_OR_THIRD_MATCH &&
			lottoTicket.stream()
				.noneMatch(winningNumbers::isMatchWithBonus);
	}

	private static int getMatchCount(LottoTicket lottoTicket, WinningNumbers winningNumbers) {
		return (int)lottoTicket.stream()
			.filter(lottoNumber -> isMatch(winningNumbers, lottoNumber))
			.count();
	}

	private static boolean isMatch(WinningNumbers winningNumbers, LottoNumber lottoNumber) {
		return winningNumbers.getOrdinarys().contains(lottoNumber);
	}
}

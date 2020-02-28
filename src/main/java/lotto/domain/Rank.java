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

	Rank(final int matchCount, final int amount) {
		this.matchCount = matchCount;
		this.amount = amount;
	}

	public int getMatchCount() {
		return this.matchCount;
	}

	public int getAmount() {
		return this.amount;
	}

	public static Rank of(final LottoTicket lottoTicket, final WinningNumbers winningNumbers) {
		final int matchCount = getMatchCount(lottoTicket, winningNumbers);
		if (matchCount == SECOND_OR_THIRD_MATCH && isThirdPrizeCondition(lottoTicket, winningNumbers)) {
			return THIRD;
		}
		return Stream.of(Rank.values())
			.filter(rank -> rank.matchCount == matchCount)
			.findFirst()
			.orElse(NONE);
	}

	private static int getMatchCount(final LottoTicket lottoTicket, final WinningNumbers winningNumbers) {
		return (int)lottoTicket.stream()
			.filter(lottoNumber -> isMatch(winningNumbers, lottoNumber))
			.count();
	}

	private static boolean isMatch(final WinningNumbers winningNumbers, final LottoNumber lottoNumber) {
		return winningNumbers.getOrdinaries().contains(lottoNumber);
	}

	private static boolean isThirdPrizeCondition(final LottoTicket lottoTicket, final WinningNumbers winningNumbers) {
		return lottoTicket.stream()
				.noneMatch(winningNumbers::isMatchWithBonus);
	}

}

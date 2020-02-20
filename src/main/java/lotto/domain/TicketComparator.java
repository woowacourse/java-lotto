package lotto.domain;

import java.util.List;

public class TicketComparator {
	public static int getMatchCount(final LottoTicket lottoTicket, final WinningNumbers winningNumbers) {
		return (int)lottoTicket.stream()
			.filter(lottoNumber -> isMatch(winningNumbers, lottoNumber))
			.count();
	}

	private static boolean isMatch(final WinningNumbers winningNumbers, final LottoNumber lottoNumber) {
		final List<LottoNumber> ordinaries = winningNumbers.getOrdinaries();
		return ordinaries.contains(lottoNumber);
	}

	public static boolean isBonusNotMatch(final LottoTicket lottoTicket, final WinningNumbers winningNumbers) {
		return lottoTicket.stream()
			.noneMatch(winningNumbers::isMatchWithBonus);
	}
}

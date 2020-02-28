package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Ranks {
	private final List<Rank> ranks;

	public Ranks(LottoTickets lottoTickets, WinningNumbers winningNumbers) {
		this.ranks = ranks(lottoTickets, winningNumbers);
	}

	public int getCountOf(final Rank input) {
		return (int)ranks.stream()
			.filter(rank -> rank.equals(input))
			.count();
	}

	private List<Rank> ranks(LottoTickets lottoTickets, WinningNumbers winningNumbers) {
		return lottoTickets.stream().map(lottoTicket -> Rank.of(lottoTicket, winningNumbers))
				.collect(Collectors.toList());
	}

	public List<Rank> getRanks() {
		return this.ranks;
	}
}

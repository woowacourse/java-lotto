package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LottoTickets {
	private final List<LottoTicket> lottoTickets;

	public LottoTickets(final List<LottoTicket> lottoTickets) {
		this.lottoTickets = new ArrayList<>(lottoTickets);
	}

	public Ranks getRanksBy(WinningNumbers winningNumbers) {
		return new Ranks(
			lottoTickets.stream()
				.map(getRank(winningNumbers))
				.collect(Collectors.toList())
		);
	}

	private Function<LottoTicket, Rank> getRank(WinningNumbers winningNumbers) {
		return lottoTicket -> Rank.of(
			lottoTicket.getMatchCount(winningNumbers),
			lottoTicket.isBonusNotMatch(winningNumbers)
		);
	}

	public int size() {
		return lottoTickets.size();
	}

	public List<LottoTicket> tickets() {
		return Collections.unmodifiableList(lottoTickets);
	}

	public LottoTickets add(LottoTickets others) {
		List<LottoTicket> result = new ArrayList<>(this.lottoTickets);
		result.addAll(others.lottoTickets);
		return new LottoTickets(result);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LottoTickets that = (LottoTickets)o;
		return Objects.equals(lottoTickets, that.lottoTickets);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottoTickets);
	}
}

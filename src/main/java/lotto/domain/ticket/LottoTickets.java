package lotto.domain.ticket;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import lotto.domain.result.LottoRanks;
import lotto.domain.result.WinningLotto;

public class LottoTickets {
	private final List<LottoTicket> lottoTickets;

	public LottoTickets(List<LottoTicket> lottoTickets) {
		this.lottoTickets = Collections.unmodifiableList(new ArrayList<>(Objects.requireNonNull(lottoTickets)));
	}

	LottoTickets concat(LottoTickets other) {
		return Stream.of(lottoTickets, other.lottoTickets)
			.flatMap(List::stream)
			.collect(collectingAndThen(toList(), LottoTickets::new));
	}

	public LottoRanks matchLottoRanks(WinningLotto winningLotto) {
		return lottoTickets.stream()
			.map(winningLotto::calculateRank)
			.collect(collectingAndThen(toList(), LottoRanks::new));
	}

	public List<LottoTicket> getLottoTickets() {
		return lottoTickets;
	}
}

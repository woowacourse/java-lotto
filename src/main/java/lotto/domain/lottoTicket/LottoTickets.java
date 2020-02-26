package lotto.domain.lottoTicket;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lotto.domain.result.WinningLotto;
import lotto.domain.result.WinningResult;

public class LottoTickets {
	private final List<LottoTicket> lottoTickets;

	public LottoTickets(List<LottoTicket> lottoTickets) {
		this.lottoTickets = lottoTickets;
	}

	public WinningResult produceWinningResultBy(WinningLotto winningLotto) {
		return lottoTickets.stream()
			.map(winningLotto::match)
			.collect(Collectors.collectingAndThen(
				Collectors.groupingBy(Function.identity(), Collectors.counting()),
				WinningResult::new));
	}

	public LottoTickets concat(LottoTickets concatenatedLottoTickets) {
		return Stream.concat(lottoTickets.stream(), concatenatedLottoTickets.getLottoTickets().stream())
			.collect(Collectors.collectingAndThen(Collectors.toList(), LottoTickets::new));
	}

	public List<LottoTicket> getLottoTickets() {
		return lottoTickets;
	}

	public int getLottoTicketsSize() {
		return lottoTickets.size();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		LottoTickets that = (LottoTickets)object;
		return Objects.equals(lottoTickets, that.lottoTickets);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottoTickets);
	}
}

package lotto.domain.lottoTicket;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
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
			.collect(collectingAndThen(groupingBy(Function.identity(), counting()), WinningResult::new));
	}

	public LottoTickets concat(LottoTickets concatenatedLottoTickets) {
		return Stream.concat(lottoTickets.stream(), concatenatedLottoTickets.lottoTickets.stream())
			.collect(collectingAndThen(toList(), LottoTickets::new));
	}

	public List<LottoTicket> getLottoTickets() {
		return lottoTickets;
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

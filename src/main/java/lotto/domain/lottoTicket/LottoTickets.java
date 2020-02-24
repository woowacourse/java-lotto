package lotto.domain.lottoTicket;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

	public List<LottoTicket> getLottoTickets() {
		return lottoTickets;
	}
}

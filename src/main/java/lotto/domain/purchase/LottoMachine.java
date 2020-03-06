package lotto.domain.purchase;

import java.util.List;

import lotto.domain.lottoTicket.LottoTickets;
import lotto.domain.strategy.LottoTicketsGenerator;

public class LottoMachine {

	private final List<LottoTicketsGenerator> lottoTicketsGenerators;

	public LottoMachine(List<LottoTicketsGenerator> lottoTicketsGenerators) {
		this.lottoTicketsGenerators = lottoTicketsGenerators;
	}

	public LottoTickets purchaseLottoTickets(PurchasingCount totalPurchasingCount) {
		return lottoTicketsGenerators.stream()
			.map(lottoTicketsGenerator -> lottoTicketsGenerator.generate(totalPurchasingCount))
			.reduce(LottoTickets::concat)
			.orElseThrow(() -> new InvalidPurchasingCountException(InvalidPurchasingCountException.INVALID));
	}

}

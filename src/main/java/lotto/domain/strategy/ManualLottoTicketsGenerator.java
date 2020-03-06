package lotto.domain.strategy;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.stream.IntStream;

import lotto.domain.lottoTicket.LottoTicket;
import lotto.domain.lottoTicket.LottoTickets;
import lotto.domain.purchase.PurchasingCount;

public class ManualLottoTicketsGenerator implements LottoTicketsGenerator {

	private final List<String> inputManualLottoTickets;

	public ManualLottoTicketsGenerator(List<String> inputManualLottoTickets) {
		this.inputManualLottoTickets = inputManualLottoTickets;
	}

	@Override
	public LottoTickets generate(PurchasingCount purchasingCount) {
		return IntStream.range(0, purchasingCount.getPurchasingCount())
			.mapToObj(inputManualLottoTickets::get)
			.map(LottoTicket::valueOf)
			.collect(collectingAndThen(toList(), LottoTickets::new));
	}

}

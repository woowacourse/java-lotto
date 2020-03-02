package lotto.domain.lottoTicket;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.stream.IntStream;

import lotto.domain.purchase.PurchasingCount;

public class ManualLottoTicketsFactory implements LottoTicketsGeneratable {

	private final List<String> inputManualLottoTickets;

	public ManualLottoTicketsFactory(List<String> inputManualLottoTickets) {
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

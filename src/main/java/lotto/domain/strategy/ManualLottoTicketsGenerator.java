package lotto.domain.strategy;

import java.util.ArrayList;
import java.util.List;

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
		List<LottoTicket> lottoTickets = new ArrayList<>();

		for (String inputLottoTicket : inputManualLottoTickets) {
			purchasingCount.buyLottoTicket();
			lottoTickets.add(LottoTicket.valueOf(inputLottoTicket));
		}
		return new LottoTickets(lottoTickets);
	}

}

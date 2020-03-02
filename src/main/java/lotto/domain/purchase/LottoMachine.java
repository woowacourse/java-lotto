package lotto.domain.purchase;

import java.util.List;

import lotto.domain.lottoNumber.LottoNumberCache;
import lotto.domain.lottoTicket.AutoLottoTicketsFactory;
import lotto.domain.lottoTicket.LottoTickets;
import lotto.domain.lottoTicket.LottoTicketsGeneratable;
import lotto.domain.lottoTicket.ManualLottoTicketsFactory;

public class LottoMachine {

	private final LottoTicketsGeneratable manualLottoTicketFactory;
	private final LottoTicketsGeneratable autoLottoTicketFactory;

	public LottoMachine(List<String> inputManualLottoTickets) {
		this.manualLottoTicketFactory = new ManualLottoTicketsFactory(inputManualLottoTickets);
		this.autoLottoTicketFactory = new AutoLottoTicketsFactory(LottoNumberCache.values());
	}

	public LottoTickets purchaseLottoTickets(TotalPurchasingCount totalPurchasingCount) {
		LottoTickets manualLottoTickets =
			manualLottoTicketFactory.generate(totalPurchasingCount.getManualPurchasingCount());
		LottoTickets autoLottoTickets =
			autoLottoTicketFactory.generate(totalPurchasingCount.getAutoPurchasingCount());
		return manualLottoTickets.concat(autoLottoTickets);
	}

}

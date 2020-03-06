package lotto.domain.purchase;

import java.util.List;

import lotto.domain.lottoNumber.LottoNumberCache;
import lotto.domain.lottoTicket.LottoTickets;
import lotto.domain.strategy.AutoLottoTicketsGenerator;
import lotto.domain.strategy.LottoTicketsGenerator;
import lotto.domain.strategy.ManualLottoTicketsGenerator;

public class LottoMachine {

	private final LottoTicketsGenerator manualLottoTicketFactory;
	private final LottoTicketsGenerator autoLottoTicketFactory;

	public LottoMachine(List<String> inputManualLottoTickets) {
		this.manualLottoTicketFactory = new ManualLottoTicketsGenerator(inputManualLottoTickets);
		this.autoLottoTicketFactory = new AutoLottoTicketsGenerator(LottoNumberCache.values());
	}

	public LottoTickets purchaseLottoTickets(TotalPurchasingCount totalPurchasingCount) {
		LottoTickets manualLottoTickets =
			manualLottoTicketFactory.generate(totalPurchasingCount.getManualPurchasingCount());
		LottoTickets autoLottoTickets =
			autoLottoTicketFactory.generate(totalPurchasingCount.getAutoPurchasingCount());
		return manualLottoTickets.concat(autoLottoTickets);
	}

}

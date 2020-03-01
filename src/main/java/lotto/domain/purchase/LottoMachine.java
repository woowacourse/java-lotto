package lotto.domain.purchase;

import java.util.Arrays;
import java.util.List;

import lotto.domain.lottoTicket.AutoLottoTicketsFactory;
import lotto.domain.lottoTicket.LottoTickets;
import lotto.domain.lottoTicket.ManualLottoTicketsFactory;

public class LottoMachine {

	private final PurchasingCount purchasingCount;
	private final ManualLottoTicketCount manualLottoTicketCount;

	public LottoMachine(PurchasingCount purchasingCount, ManualLottoTicketCount manualLottoTicketCount) {
		this.purchasingCount = purchasingCount;
		this.manualLottoTicketCount = manualLottoTicketCount;
		purchasingCount.purchaseFor(manualLottoTicketCount);
	}

	public List<Integer> getNumberOfManualAndAutoLottoTickets() {
		return Arrays.asList(
			manualLottoTicketCount.getManualLottoTicketCount(),
			purchasingCount.getPurchasingCount());
	}

	public LottoTickets purchaseLottoTicketsManualAndAutoBy(List<String> inputManualLottoTickets) {
		validate(inputManualLottoTickets);
		LottoTickets manualLottoTickets = ManualLottoTicketsFactory.generate(inputManualLottoTickets);
		LottoTickets autoLottoTickets = AutoLottoTicketsFactory.generate(purchasingCount);
		return manualLottoTickets.concat(autoLottoTickets);
	}

	private void validate(List<String> inputManualLottoTickets) {
		if (inputManualLottoTickets.size() != manualLottoTicketCount.getManualLottoTicketCount()) {
			throw new InvalidLottoMachineException(InvalidLottoMachineException.INVALID);
		}
	}

}

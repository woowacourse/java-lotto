package lotto.domain;

import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoTicketFactory {
    public LottoTickets buyLottoTicket(PurchasingAmount purchasingAmount) {
        String manualTicketValue = InputView.inputManualTicketValue();
        ManualLottoTicketFactory manualLottoTicketFactory =
                new ManualLottoTicketFactory(manualTicketValue, purchasingAmount);
        RandomLottoTicketFactory randomLottoTicketFactory = new RandomLottoTicketFactory();

        LottoTickets lottoTickets = manualLottoTicketFactory.buyManualLottoTickets(purchasingAmount);
        lottoTickets = randomLottoTicketFactory.buyRandomLottoTickets(purchasingAmount,lottoTickets);
        OutputView.printLottoState(manualTicketValue, lottoTickets);
        return lottoTickets;
    }
}

package lotto.controller;

import lotto.domain.LottoSeller;
import lotto.domain.LottoTicketingMachine;
import lotto.domain.LottoTickets;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public void run() {
        LottoSeller lottoSeller = InputView.makeLottoSeller();
        LottoTickets lottoTickets = LottoTicketingMachine.generateLottoTickets(lottoSeller.getNumOfLotto());
        OutputView.showNumOfTicketsFrom(lottoSeller);
        OutputView.showAllOf(lottoTickets);
    }
}

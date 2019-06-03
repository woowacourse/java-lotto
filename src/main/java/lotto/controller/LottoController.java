package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public void run() {
        LottoSeller lottoSeller = InputView.makeLottoSeller();
        LottoTickets lottoTickets = LottoTicketingMachine.generateLottoTickets(lottoSeller.getNumOfLotto());

        OutputView.showNumOfTicketsFrom(lottoSeller);
        OutputView.showAllOf(lottoTickets);

        LottoTicket winningLotto = InputView.makeWinningLotto();

        LottoResult lottoResult = lottoTickets.makeResultWith(winningLotto);
        OutputView.showStatisticsOf(lottoResult);
    }
}

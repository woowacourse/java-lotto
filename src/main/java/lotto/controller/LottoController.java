package lotto.controller;

import lotto.domain.LottoResult;
import lotto.domain.lottoseller.LottoSeller;
import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.lottoticket.LottoTicketingMachine;
import lotto.domain.lottoticket.LottoTickets;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public void run() {
        LottoSeller lottoSeller = InputView.makeLottoSeller();
        LottoTickets lottoTickets = LottoTicketingMachine.generateLottoTickets(lottoSeller.getNumOfLotto());

        OutputView.showNumOfTicketsFrom(lottoSeller);
        OutputView.showAllOf(lottoTickets);

        LottoTicket winningLotto = InputView.makeWinningLotto();

        LottoResult lottoResult = LottoResult.of(lottoTickets, winningLotto);
        OutputView.showStatisticsOf(lottoResult);
    }
}

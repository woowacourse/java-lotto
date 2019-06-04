package lotto.controller;

import lotto.domain.LottoResult;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottoseller.LottoSeller;
import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.lottoticket.LottoTicketingMachine;
import lotto.domain.lottoticket.LottoTickets;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public void run() {
        LottoSeller lottoSeller = InputView.makeLottoSeller();
        long numOfPurchasedTickets = lottoSeller.getNumOfLotto();

        long numOfManualTickets = InputView.makeNumOfManualTickets(numOfPurchasedTickets);
        LottoTickets manualTickets = InputView.makeManualTickets(numOfManualTickets);

        long numOfAutomaticTickets = numOfPurchasedTickets - numOfManualTickets;
        LottoTickets automaticTickets = LottoTicketingMachine.generateLottoTickets(numOfAutomaticTickets);

        OutputView.showNumOfTicketsFrom(lottoSeller);
        OutputView.showAllOf(automaticTickets);

        LottoTicket winningLotto = InputView.makeWinningLotto();
        LottoNumber bonusBall = InputView.makeBonusBall(winningLotto);

        LottoResult lottoResult = LottoResult.of(automaticTickets, winningLotto, bonusBall);
        OutputView.showStatisticsOf(lottoResult);
    }
}

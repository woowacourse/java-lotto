package lotto.controller;

import lotto.domain.lottomoney.MoneyForLotto;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottoresult.LottoStatistics;
import lotto.domain.lottoresult.WinningLotto;
import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.lottoticket.LottoTickets;
import lotto.domain.lottoticket.ticketingmachine.LottoTicketingMachine;
import lotto.domain.lottoticket.ticketingmachine.RandomLottoNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public void run() {
        MoneyForLotto moneyForLotto = InputView.makeMoneyForLotto();
        long numOfPurchasedTickets = moneyForLotto.getNumOfLotto();

        long numOfManualTickets = InputView.makeNumOfManualTickets(numOfPurchasedTickets);
        LottoTickets manualTickets = InputView.makeManualTickets(numOfManualTickets);

        long numOfAutomaticTickets = numOfPurchasedTickets - numOfManualTickets;
        LottoTickets automaticTickets = LottoTicketingMachine.generateLottoTickets(numOfAutomaticTickets
                , new RandomLottoNumberGenerator());

        LottoTickets purchasedTickets = LottoTickets.join(manualTickets, automaticTickets);

        OutputView.showNumOfTicketsFrom(moneyForLotto, numOfManualTickets);
        OutputView.showAllOf(purchasedTickets);

        LottoTicket winningTicket = InputView.makeWinningTicket();
        LottoNumber bonusBall = InputView.makeBonusBall(winningTicket);

        WinningLotto winningLotto = WinningLotto.of(winningTicket, bonusBall);

        LottoStatistics lottoStatistics = LottoStatistics.of(purchasedTickets, winningLotto);
        OutputView.showStatisticsOf(lottoStatistics);
    }
}

package lotto.controller;

import lotto.domain.LottoResult;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottomoney.MoneyForLotto;
import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.lottoticket.LottoTicketingMachine;
import lotto.domain.lottoticket.LottoTickets;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public void run() {
        MoneyForLotto moneyForLotto = InputView.makeMoneyForLotto();
        long numOfPurchasedTickets = moneyForLotto.getNumOfLotto();

        long numOfManualTickets = InputView.makeNumOfManualTickets(numOfPurchasedTickets);
        LottoTickets manualTickets = InputView.makeManualTickets(numOfManualTickets);

        long numOfAutomaticTickets = numOfPurchasedTickets - numOfManualTickets;
        LottoTickets automaticTickets = LottoTicketingMachine.generateLottoTickets(numOfAutomaticTickets);

        LottoTickets purchasedTickets = LottoTickets.join(manualTickets, automaticTickets);

        OutputView.showNumOfTicketsFrom(moneyForLotto, numOfManualTickets);
        OutputView.showAllOf(purchasedTickets);

        LottoTicket winningLotto = InputView.makeWinningLotto();
        LottoNumber bonusBall = InputView.makeBonusBall(winningLotto);

        LottoResult lottoResult = LottoResult.of(purchasedTickets, winningLotto, bonusBall);
        OutputView.showStatisticsOf(lottoResult);
    }
}

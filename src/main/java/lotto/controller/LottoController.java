package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.stream.Collectors;

public class LottoController {
    public void run() {
        Money money = new Money(InputView.inputMoney());
        LottoTicketFactory lottoTicketFactory = new LottoTicketFactory();
        LottoTickets lottoTickets = new LottoTickets(lottoTicketFactory.buyLottoTickets(money));
        OutputView.printLottoTicketsCount(lottoTickets);
        OutputView.printLottoTickets(lottoTickets);
        LottoTicket winningTicket = new LottoTicket(
                InputView.inputWinningNumbers().stream()
                        .map(LottoNumber::new)
                        .collect(Collectors.toList()));
        LottoNumber bonusNumber = new LottoNumber(InputView.inputBonusNumber());
        WinningLotto winningLotto = new WinningLotto(winningTicket, bonusNumber);
        LottoResult lottoResult = new LottoResult(lottoTickets.checkWinningTickets(winningLotto));
        OutputView.printResultStatistic(lottoResult);
        OutputView.printProfitRate(lottoResult, money);
    }
}

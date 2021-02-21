package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.stream.Collectors;

public class LottoController {
    public void run() {
        Money money = new Money(InputView.inputMoney());
        LottoTickets lottoTickets = getLottoTickets(money);
        LottoTicket winningTicket = getWinningTicket(lottoTickets);
        LottoResult lottoResult = getLottoResult(lottoTickets, winningTicket);
        showResult(lottoResult, money);
    }

    private LottoTickets getLottoTickets(Money money) {
        LottoTicketFactory lottoTicketFactory = new LottoTicketFactory();
        return new LottoTickets(lottoTicketFactory.buyLottoTickets(money));
    }

    private LottoTicket getWinningTicket(LottoTickets lottoTickets) {
        OutputView.printLottoTicketsCount(lottoTickets);
        OutputView.printLottoTickets(lottoTickets);
        return new LottoTicket(
                InputView.inputWinningNumbers().stream()
                        .map(LottoNumber::new)
                        .collect(Collectors.toList()));
    }

    private LottoResult getLottoResult(LottoTickets lottoTickets, LottoTicket winningTicket) {
        LottoNumber bonusNumber = new LottoNumber(InputView.inputBonusNumber());
        WinningLotto winningLotto = new WinningLotto(winningTicket, bonusNumber);
        return new LottoResult(lottoTickets.checkWinningTickets(winningLotto));
    }

    private void showResult(LottoResult lottoResult, Money money) {
        OutputView.printResultStatistic(lottoResult);
        OutputView.printProfitRate(lottoResult, money);
    }
}

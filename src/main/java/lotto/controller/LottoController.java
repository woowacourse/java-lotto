package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.stream.Collectors;

public class LottoController {
    private final LottoTicketFactory lottoTicketFactory;

    public LottoController() {
        this.lottoTicketFactory = new LottoTicketFactory();
    }

    public void run() {
        Money money = new Money(InputView.inputMoney());
        LottoTickets lottoTickets = lottoTicketFactory.buyLottoTickets(money);
        LottoTicket winningTicket = getWinningTicket(lottoTickets);
        LottoResult lottoResult = getLottoResult(lottoTickets, winningTicket);
        showResult(lottoResult, money);
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

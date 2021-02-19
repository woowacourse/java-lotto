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
        Money inputMoney = new Money(InputView.inputMoney());
        LottoTickets lottoTickets = lottoTicketFactory.buyLottoTickets(inputMoney);
        WinningLotto winningLotto = getWinningLotto(lottoTickets);
        showResult(lottoTickets, winningLotto);
    }

    private WinningLotto getWinningLotto(LottoTickets lottoTickets) {
        OutputView.printLottoTicketsCount(lottoTickets);
        OutputView.printLottoTickets(lottoTickets);
        LottoTicket winningTicket = new LottoTicket(
                InputView.inputWinningNumbers()
                        .stream()
                        .map(LottoNumber::new)
                        .collect(Collectors.toList()));
        LottoNumber bonusNumber = new LottoNumber(InputView.inputBonusNumber());
        return new WinningLotto(winningTicket, bonusNumber);
    }

    private void showResult(LottoTickets lottoTickets, WinningLotto winningLotto) {
        LottoResult lottoResult = lottoTickets.checkPrizesByWinningTickets(winningLotto);
        OutputView.printResultStatistic(lottoResult);
        Money totalProfit = lottoResult.getTotalProfit();
        double profitRate = totalProfit.divide(lottoResult.lottoResult().size());
        OutputView.printProfitRate(profitRate);
    }
}

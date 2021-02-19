package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

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
        LottoTicket winningTicket = InputView.inputWinningNumbers().stream()
                .map(LottoNumber::new)
                .collect(collectingAndThen(toList(), LottoTicket::new));
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

package lotto.controller;

import lotto.domain.LottoService;
import lotto.domain.lotto.Lotto;
import lotto.domain.lottomachine.RandomLottoMachine;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.Money;
import lotto.domain.Ticket;
import lotto.domain.rating.RatingInfo;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void start() {
        LottoService lottoService = new LottoService(new RandomLottoMachine());
        Ticket ticket = buyLotto();

        OutputView.printBuyLotto(ticket.getCount());
        OutputView.printLottoResults(lottoService.getLotto(ticket));

        WinningLotto winningLotto = buyWinningLotto();
        RatingInfo ratingInfo = lottoService.scratchLotto(winningLotto);
        OutputView
            .printWinningStats(ratingInfo, lottoService.calculateEarningRate(ticket.getPrice()));
    }

    private Ticket buyLotto() {
        try {
            return tryBuyLotto();
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return buyLotto();
        }
    }

    private Ticket tryBuyLotto() {
        int money = InputView.getMoney();
        return new Ticket(new Money(money));
    }

    private WinningLotto buyWinningLotto() {
        try {
            return tryBuyWinningLotto();
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return buyWinningLotto();
        }
    }

    private WinningLotto tryBuyWinningLotto() {
        Lotto lotto = Lotto.from(InputView.getWinningNumbers());
        LottoNumber bonusNumber = new LottoNumber(InputView.getBonusBall());
        return new WinningLotto(lotto, bonusNumber);
    }
}

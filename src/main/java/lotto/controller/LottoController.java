package lotto.controller;

import lotto.domain.manager.LottoManager;
import lotto.domain.lotto.Lotto;
import lotto.domain.lottomachine.RandomLottoMachine;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.manager.Money;
import lotto.domain.manager.Ticket;
import lotto.domain.rating.RatingInfo;
import lotto.domain.statistics.LottoStatistics;
import lotto.domain.statistics.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void start() {
        LottoManager lottoManager = new LottoManager(new RandomLottoMachine());
        Ticket ticket = buyLotto();

        OutputView.printBuyLotto(ticket.getCount());
        OutputView.printLottoResults(lottoManager.buyLotto(ticket));

        WinningLotto winningLotto = buyWinningLotto();
        RatingInfo ratingInfo = lottoManager.scratchLotto(winningLotto);
        OutputView.printWinningStats(new LottoStatistics(ratingInfo), ticket.getPrice());
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
        Lotto lotto = Lotto.getInstanceByInt(InputView.getWinningNumbers());
        LottoNumber bonusNumber = new LottoNumber(InputView.getBonusBall());
        return new WinningLotto(lotto, bonusNumber);
    }
}

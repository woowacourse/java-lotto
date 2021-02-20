package lotto.controller;

import lotto.domain.LottoManager;
import lotto.domain.lotto.Lotto;
import lotto.domain.lottomachine.RandomLottoMachine;
import lotto.domain.primitive.LottoNumber;
import lotto.domain.primitive.Money;
import lotto.domain.primitive.Ticket;
import lotto.domain.rating.RatingCounter;
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
        RatingCounter ratingCounter = lottoManager.scratchLotto(winningLotto);
        OutputView.printWinningStats(new LottoStatistics(ratingCounter), ticket.getPrice());
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
        OutputView.getMessage("구입금액을 입력해 주세요.");
        int money = InputView.getInt();
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
        Lotto lotto = Lotto.createByInteger(InputView.getWinningNumbers());
        OutputView.getMessage("보너스 볼을 입력해 주세요.");
        LottoNumber bonusNumber = new LottoNumber(InputView.getInt());
        return new WinningLotto(lotto, bonusNumber);
    }
}

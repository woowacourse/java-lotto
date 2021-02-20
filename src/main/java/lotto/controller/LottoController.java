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

    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";

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
        OutputView.getMessage(INPUT_MONEY_MESSAGE);
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
        Lotto lotto = new Lotto(InputView.getWinningNumbers());
        OutputView.getMessage(INPUT_BONUS_BALL_MESSAGE);
        LottoNumber bonusNumber = new LottoNumber(InputView.getInt());
        return new WinningLotto(lotto, bonusNumber);
    }
}

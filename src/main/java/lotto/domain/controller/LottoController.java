package lotto.domain.controller;

import lotto.domain.*;
import lotto.domain.Number;
import lotto.domain.model.LottoGame;
import lotto.domain.result.GameResult;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public void run() {
        PurchaseMoney money = new PurchaseMoney(InputView.getMoney());
        LottoCount count = LottoCount.create(InputView.getCount(), money);
        Lottos lottos =
                LottoFactory.create(InputView.getManualLottosNumber(
                        count.getManualLottoCount()), count.getAutoLottoCount(money)
                );
        OutputView.printPieces(count.getManualLottoCount(), count.getAutoLottoCount(money));
        OutputView.printLottos(lottos);
        createResult(money, lottos, createWinningLotto());
    }

    private WinningLotto createWinningLotto() {
        Lotto winningNumbers = LottoFactory.create(InputView.getWinningNumbers());
        Number bonusNumber = LottoFactory.of(InputView.getBonusNumber());
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private void createResult(PurchaseMoney money, Lottos lottos, WinningLotto winningLotto) {
        LottoGame game = new LottoGame(lottos, winningLotto, money);
        GameResult gameResult = game.getResult();
        OutputView.printResult(gameResult, gameResult.calculateProfit(money));
    }
}

package controller;

import domain.*;
import domain.LottoResult;
import view.InputView;
import view.OutputView;

import java.util.Map;

public class LottoGameController {
    public void run() {
        final GameMoney gameMoney = makeGameMoney();
        final LottoBundle lottoBundle = makeLottoBundle(gameMoney);
        final WinningLotto winningLotto = makeWinningLotto();
        makeLottoResult(lottoBundle, winningLotto, gameMoney);
    }

    private GameMoney makeGameMoney() {
        OutputView.printGameMoneyRequest();
        int userGameMoney = InputView.getGameMoney();
        return new GameMoney(userGameMoney);
    }

    private LottoBundle makeLottoBundle(final GameMoney gameMoney) {
        final LottoBundle lottoBundle = gameMoney.buyLotto();
        OutputView.printLottoBought(lottoBundle);
        return lottoBundle;
    }

    private WinningLotto makeWinningLotto() {
        OutputView.printWinningLottoRequest();
        Lotto winningLotto = InputView.getWinningLotto();
        OutputView.printBonusBallRequest();
        LottoBall bonusBall = InputView.getBonusBall();
        return new WinningLotto(winningLotto, bonusBall);
    }

    private void makeLottoResult(final LottoBundle lottoBundle, final WinningLotto winningLotto, final GameMoney gameMoney) {
        final LottoResult lottoResult = new LottoResult();

        final Map<LottoRank, Integer> gameResult = lottoResult.checkResult(lottoBundle, winningLotto);
        OutputView.printLottoResult(gameResult);

        final double profitRate = lottoResult.checkProfitRate(gameResult);
        OutputView.printProfitRate(profitRate);
    }
}

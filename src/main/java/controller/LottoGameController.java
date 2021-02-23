package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        try {
            final Lotto winningLotto = makeLottoWinningLotto();
            final LottoBall bonusBall = makeBonusBall();
            return new WinningLotto(winningLotto, bonusBall);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return makeWinningLotto();
        }
    }

    private Lotto makeLottoWinningLotto() {
        try {
            OutputView.printWinningLottoRequest();
            final List<Integer> winningLottoNumber = InputView.getWinningLotto();
            return new Lotto(winningLottoNumber.stream()
                    .map(number -> LottoBall.valueOf(number))
                    .collect(Collectors.toList()));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return makeLottoWinningLotto();
        }
    }

    private LottoBall makeBonusBall() {
        try {
            OutputView.printBonusBallRequest();
            final int bonusBall = InputView.getBonusBall();
            return LottoBall.valueOf(bonusBall);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return makeBonusBall();
        }
    }

    private void makeLottoResult(final LottoBundle lottoBundle, final WinningLotto winningLotto, final GameMoney gameMoney) {
        final LottoResult lottoResult = new LottoResult();

        final Map<LottoRank, Integer> gameResult = lottoResult.checkResult(lottoBundle, winningLotto);
        OutputView.printLottoResult(gameResult);

        final double profitRate = lottoResult.checkProfitRate(gameResult);
        OutputView.printProfitRate(profitRate);
    }
}

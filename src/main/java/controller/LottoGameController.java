package controller;

import domain.lotto.Lotto;
import domain.lotto.LottoBall;
import domain.lotto.LottoBundle;
import domain.money.GameMoney;
import domain.result.LottoRank;
import domain.result.LottoResult;
import domain.result.WinningResult;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoGameController {
    public void run() {
        final GameMoney gameMoney = makeGameMoney();

        final LottoBundle lottoBundle = makeLottoBundle(gameMoney);

        final WinningResult winningResult = makeWinningResult();

        makeLottoResult(lottoBundle, winningResult, gameMoney);
    }

    private GameMoney makeGameMoney() {
        try {
            OutputView.printGameMoneyRequest();
            int userGameMoney = InputView.getGameMoney();
            return new GameMoney(userGameMoney);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return makeGameMoney();
        }
    }

    private LottoBundle makeLottoBundle(final GameMoney gameMoney) {
        final LottoBundle lottoBundle = gameMoney.buyLotto();
        OutputView.printLottoBought(lottoBundle);
        return lottoBundle;
    }

    private WinningResult makeWinningResult() {
        try {
            final Lotto winningLotto = makeWinningLotto();
            final LottoBall bonusBall = makeBonusBall();
            return new WinningResult(winningLotto, bonusBall);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return makeWinningResult();
        }
    }

    private Lotto makeWinningLotto() {
        try {
            OutputView.printWinningLottoRequest();
            final List<Integer> winningLottoNumber = InputView.getWinningLotto();
            return new Lotto(winningLottoNumber.stream()
                    .map(number -> LottoBall.valueOf(number))
                    .collect(Collectors.toList()));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return makeWinningLotto();
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

    private void makeLottoResult(final LottoBundle lottoBundle, final WinningResult winningResult, final GameMoney gameMoney) {
        final LottoResult lottoResult = new LottoResult();

        final Map<LottoRank, Integer> gameResult = lottoResult.checkResult(lottoBundle, winningResult);
        OutputView.printLottoResult(gameResult);

        final double profitRate = lottoResult.checkProfitRate(gameResult);
        OutputView.printProfitRate(profitRate);
    }
}

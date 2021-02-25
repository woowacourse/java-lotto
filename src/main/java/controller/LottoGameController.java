package controller;

import domain.lotto.Lotto;
import domain.lotto.LottoBall;
import domain.lotto.LottoBundle;
import domain.money.GameMoney;
import domain.result.LottoResult;
import domain.result.WinningResult;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoGameController {
    public void run() {
        final GameMoney gameMoney = makeGameMoney();

        final List<Lotto> manualLotto = makeManualLotto(gameMoney);

        final LottoBundle lottoBundle = makeLottoBundle(gameMoney, manualLotto);

        final WinningResult winningResult = makeWinningResult();

        makeLottoResult(lottoBundle, winningResult);
    }

    private GameMoney makeGameMoney() {
        try {
            OutputView.printGameMoneyRequest();
            final int userGameMoney = InputView.getGameMoney();
            return new GameMoney(userGameMoney);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return makeGameMoney();
        }
    }

    private List<Lotto> makeManualLotto(final GameMoney gameMoney) {
        try {
            final int manualLottoAmount = makeManualLottoAmount(gameMoney);
            if (manualLottoAmount > 0) {
                OutputView.printManualLottoRequest();
            }
            final List<List<Integer>> manualLotto = InputView.getManualLotto(manualLottoAmount);
            return manualLotto.stream()
                    .map(numbers -> Lotto.of(numbers))
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return makeManualLotto(gameMoney);
        }
    }

    private int makeManualLottoAmount(final GameMoney gameMoney) {
        try {
            OutputView.printManualLottoAmountRequest();
            final int manualLottoAmount = InputView.getManualLottoNumber();
            gameMoney.checkManualBuyingAvailable(manualLottoAmount);
            return manualLottoAmount;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return makeManualLottoAmount(gameMoney);
        }
    }

    private LottoBundle makeLottoBundle(final GameMoney gameMoney, final List<Lotto> manualLotto) {
        final LottoBundle lottoBundle = gameMoney.buyLottoManually(manualLotto);
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
            return Lotto.of(winningLottoNumber);
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

    private void makeLottoResult(final LottoBundle lottoBundle, final WinningResult winningResult) {
        final LottoResult lottoResult = lottoBundle.checkResult(winningResult);
        OutputView.printLottoResult(lottoResult);

        final double profitRate = lottoResult.checkProfitRate();
        OutputView.printProfitRate(profitRate);
    }
}

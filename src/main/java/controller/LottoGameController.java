package controller;

import domain.lotto.Lotto;
import domain.lotto.LottoBall;
import domain.lotto.LottoBundle;
import domain.money.GameMoney;
import domain.number.LottoNumberGenerator;
import domain.number.RandomLottoNumberGenerator;
import domain.result.LottoResult;
import domain.result.WinningResult;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoGameController {
    private static final int MINIMUM_AMOUNT = 1;

    public void run() {
        final GameMoney gameMoney = makeGameMoney();

        final LottoBundle manualLottoBundle = makeManualLottoBundle(gameMoney);
        final LottoBundle autoLottoBundle = makeAutoLottoBundle(gameMoney);
        OutputView.printLottoBought(manualLottoBundle, autoLottoBundle);

        final WinningResult winningResult = makeWinningResult();

        makeLottoResult(manualLottoBundle, autoLottoBundle, winningResult);
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

    private LottoBundle makeManualLottoBundle(final GameMoney gameMoney) {
        try {
            final int manualLottoAmount = makeManualLottoAmount(gameMoney);
            checkManualLottoBuying(manualLottoAmount);
            final List<List<Integer>> manualLottoNumberBundle = InputView.getManualLotto(manualLottoAmount);
            final LottoBundle manualLottoBundle = LottoBundle.of(manualLottoNumberBundle);
            gameMoney.buyLotto(manualLottoNumberBundle.size());
            return manualLottoBundle;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return makeManualLottoBundle(gameMoney);
        }
    }

    private int makeManualLottoAmount(final GameMoney gameMoney) {
        try {
            OutputView.printManualLottoAmountRequest();
            final int manualLottoAmount = InputView.getManualLottoAmount();
            gameMoney.checkManualBuyingAvailable(manualLottoAmount);
            return manualLottoAmount;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return makeManualLottoAmount(gameMoney);
        }
    }

    private void checkManualLottoBuying(final int manualLottoAmount) {
        if (manualLottoAmount >= MINIMUM_AMOUNT) {
            OutputView.printManualLottoRequest();
        }
    }

    private LottoBundle makeAutoLottoBundle(final GameMoney gameMoney) {
        LottoNumberGenerator lottoNumberGenerator = new RandomLottoNumberGenerator();
        final int autoLottoAmount = gameMoney.checkMaxLottoAvailable();
        final List<List<Integer>> autoLottoNumberBundle = lottoNumberGenerator.createBundle(autoLottoAmount);
        gameMoney.buyLotto(autoLottoNumberBundle.size());
        return LottoBundle.of(autoLottoNumberBundle);
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
            final List<Integer> winningLottoNumber = InputView.getLotto();
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

    private void makeLottoResult(final LottoBundle manualLottoBundle, final LottoBundle autoLottoBundle,
                                 final WinningResult winningResult) {
        final LottoResult lottoResult = manualLottoBundle.checkResult(winningResult);
        final LottoResult autoLottoResult = autoLottoBundle.checkResult(winningResult);
        lottoResult.combineResult(autoLottoResult);
        OutputView.printLottoResult(lottoResult);

        final double profitRate = lottoResult.checkProfitRate();
        OutputView.printProfitRate(profitRate);
    }
}

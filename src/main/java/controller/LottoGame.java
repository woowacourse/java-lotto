package controller;

import domain.*;
import view.InputView;
import view.OutputView;

public class LottoGame {
    private Lottos lottos;
    private WinningNumber winningNumber;
    private LottoResult lottoResult;

    public LottoGame(LottoCount lottoCount) {
        this.lottos = LottosGenerator.generateTotal(lottoCount);
        OutputView.printLottos(lottoCount, lottos);
        this.winningNumber = inputWinningNumberWithValidation();
        this.lottoResult = new LottoResult();
    }

    private static WinningNumber inputWinningNumberWithValidation() {
        try {
            return new WinningNumber(InputView.inputWinningNumbers(), InputView.inputBonusNumber());
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e);
            return inputWinningNumberWithValidation();
        }
    }

    public void calculateResults() {
        lottoResult.countWinningLotto(lottos, winningNumber);
    }

    public void showResult(Money money) {
        OutputView.printResult(lottoResult);
        OutputView.printProfitRatio(money.calculateProfitRatio(lottoResult));
    }
}

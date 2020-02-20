package controller;

import domain.*;
import view.InputView;
import view.OutputView;

public class LottoGame {

    public static void main(String[] args) {
        int lottoCount = purchaseLotto();

        LottoDummy lottoDummy = createLottoDummy(lottoCount);

        WinningNumber winningNumber = inputWinningNumber();

        LottoResult lottoResult = countWinningLottos(lottoDummy, winningNumber);

        int profitRatio = calculateProfitRatio(lottoCount, lottoResult);

        printFinalResult(lottoResult, profitRatio);
    }

    private static int purchaseLotto() {
        PurchaseAmount amount = inputPurchaseAmount();
        int lottoCount = amount.getCount();
        OutputView.printPurchaseCountMessage(lottoCount);
        return lottoCount;
    }

    private static PurchaseAmount inputPurchaseAmount() {
        try {
            return new PurchaseAmount(InputView.inputPurchaseAmount());
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
        }
        return inputPurchaseAmount();
    }

    private static LottoDummy createLottoDummy(int lottoCount) {
        LottoDummy lottoDummy = new LottoDummy(lottoCount);
        OutputView.printLottoDummy(lottoDummy);
        return lottoDummy;
    }

    private static WinningNumber inputWinningNumber() {
        try {
            return new WinningNumber(InputView.inputWinningNumbers(), InputView.inputBonusNumber());
        } catch(IllegalArgumentException | NullPointerException e){
            OutputView.printExceptionMessage(e);
        }
        return inputWinningNumber();
    }

    private static LottoResult countWinningLottos(LottoDummy lottoDummy, WinningNumber winningNumber) {
        LottoResult lottoResult = new LottoResult();
        lottoDummy.countWinningLotto(winningNumber, lottoResult);
        return lottoResult;
    }

    private static int calculateProfitRatio(int lottoCount, LottoResult lottoResult) {
        Profit profit = new Profit();
        return profit.calculateProfitRatio(lottoResult.calculateProfit(), lottoCount);
    }

    private static void printFinalResult(LottoResult lottoResult, int profitRatio) {
        OutputView.printResult(lottoResult);
        OutputView.printProfitRatio(profitRatio);
    }
}

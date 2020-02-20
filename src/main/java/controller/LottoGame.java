package controller;

import domain.*;
import view.InputView;
import view.OutputView;

public class LottoGame {

    public static void main(String[] args) {
        PurchaseAmount amount = inputPurchaseAmount();
        int lottoCount = amount.getCount();
        OutputView.printPurchaseCountMessage(lottoCount);

        LottoDummy lottoDummy = new LottoDummy(lottoCount);
        OutputView.printLottoDummy(lottoDummy);

        WinningNumber winningNumber = inputWinningNumber();

        LottoResult lottoResult = new LottoResult();
        lottoDummy.countWinningLotto(winningNumber, lottoResult);

        Profit profit = new Profit();
        int totalProfit = lottoResult.calculateProfit();
        int profitRatio = profit.calculateProfitRatio(totalProfit, lottoCount);

        OutputView.printResult(lottoResult);
        OutputView.printProfitRatio(profitRatio);
    }

    private static WinningNumber inputWinningNumber() {
        try {
            return new WinningNumber(InputView.inputWinningNumbers(), InputView.inputBonusNumber());
        } catch(IllegalArgumentException | NullPointerException e){
            OutputView.printExceptionMessage(e);
        }
        return inputWinningNumber();
    }

    private static PurchaseAmount inputPurchaseAmount() {
        try {
            return new PurchaseAmount(InputView.inputPurchaseAmount());
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
        }
        return inputPurchaseAmount();
    }
}

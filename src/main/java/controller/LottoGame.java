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
        WinningNumber winningNumber = new WinningNumber(InputView.inputWinningNumbers(), InputView.inputBonusNumber());
        LottoResult lottoResult = new LottoResult();
        lottoDummy.countWinningLottoRank(winningNumber, lottoResult);

        Profit profit = new Profit();
        int totalProfit = lottoResult.calculateProfit();
        int profitRatio = profit.calculateProfitRatio(totalProfit, lottoCount);

        OutputView.printResult(lottoResult);
        OutputView.printProfitRatio(profitRatio);
    }

    private static PurchaseAmount inputPurchaseAmount() {
        try {
            return new PurchaseAmount(InputView.inputPurchaseAmount());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return inputPurchaseAmount();
    }
}

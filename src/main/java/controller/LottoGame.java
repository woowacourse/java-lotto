package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.*;

public class LottoGame {
    private static Map<LottoResult, Integer> result = new HashMap<LottoResult, Integer>(){{
        put(LottoResult.FIRST, 0);
        put(LottoResult.SECOND, 0);
        put(LottoResult.THIRD, 0);
        put(LottoResult.FOURTH, 0);
        put(LottoResult.FIFTH, 0);
    }};

    public static void main(String[] args) {
        PurchaseAmount amount = inputPurchaseAmount();
        int lottoCount = amount.getCount();
        OutputView.printPurchaseCountMessage(lottoCount);

        LottoDummy lottoDummy = new LottoDummy(lottoCount);
        OutputView.printLottoDummy(lottoDummy);
        WinningNumber winningNumber = new WinningNumber(InputView.inputWinningNumbers(), InputView.inputBonusNumber());
        lottoDummy.countWinningLottoRank(winningNumber, result);

        Profit profit = new Profit();
        int profitRatio = profit.calculateProfitRatio(result, amount.getCount());
        OutputView.printResult(result);
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

package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.*;

public class LottoGame {
    private static Map<LottoRank, Integer> result = new HashMap<LottoRank, Integer>(){{
        put(LottoRank.FIRST, 0);
        put(LottoRank.SECOND, 0);
        put(LottoRank.THIRD, 0);
        put(LottoRank.FOURTH, 0);
        put(LottoRank.FIFTH, 0);
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

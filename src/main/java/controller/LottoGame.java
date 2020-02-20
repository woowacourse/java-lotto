package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.*;

public class LottoGame {
    private static PurchaseAmount amount;
    private static List<Lotto> lottoDummy = new ArrayList<>();
    private static Map<LottoResult, Integer> result = new HashMap<LottoResult, Integer>(){{
        put(LottoResult.FIRST, 0);
        put(LottoResult.SECOND, 0);
        put(LottoResult.THIRD, 0);
        put(LottoResult.FOURTH, 0);
        put(LottoResult.FIFTH, 0);
    }};

    public static void main(String[] args) {

        amount = new PurchaseAmount(InputView.inputPurchaseAmount());
        int lottoCount = amount.getCount();
        OutputView.printPurchaseCountMessage(lottoCount);

        for (int index = 0; index < lottoCount; index++){
            lottoDummy.add(LottoFactory.createOneLotto());
        }
        OutputView.printLottoDummy(lottoDummy);
        WinningNumber winningNumber = new WinningNumber(InputView.inputWinningNumbers(), InputView.inputBonusNumber());

        Profit profit = new Profit();
        for (Lotto lotto : lottoDummy){
            LottoResult rank = winningNumber.findRank(lotto);
            if (rank != null){
                result.put(rank, result.get(rank) + 1);
            }
        }

        int profitRatio = profit.calculateProfitRatio(result, amount.getCount());
        OutputView.printResult(result);
        OutputView.printProfitRatio(profitRatio);
    }
}

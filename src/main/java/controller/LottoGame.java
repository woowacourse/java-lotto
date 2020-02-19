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
        int lottoCount = amount.calculateCount();
        OutputView.printPurchaseCountMessage(lottoCount);

        for (int index = 0; index < lottoCount; index++){
            lottoDummy.add(LottoFactory.createOneLotto());
        }
        OutputView.printLottoDummy(lottoDummy);
        WinningNumber winningNumber = new WinningNumber(InputView.inputWinningNumbers(), InputView.inputBonusNumber());

        for (Lotto lotto : lottoDummy){
            LottoResult rank = winningNumber.findRank(lotto);
            result.put(rank, result.getOrDefault(rank, 0) + 1);
        }

        OutputView.printResult(result);
    }
}

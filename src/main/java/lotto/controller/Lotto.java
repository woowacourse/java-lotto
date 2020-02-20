package lotto.controller;

import lotto.model.*;
import lotto.view.OutputView;

public class Lotto {

    private static final String BONUS_KEY = "5+";

    public static void lottoGame() {
        Initializer.initialize();
        for (AutoTicket autoTicket : AutoTickets.getAutoTickets()) {
            int count = isInWinNumber(autoTicket);
            checkCountOverThree(autoTicket, count);
        }
        printCorrectResults();
        new Prize();
        OutputView.printYield(Prize.countYieldMoney());
    }

    public static int isInWinNumber(AutoTicket autoTicket) {
        return (int) autoTicket.getAutoTicket()
            .stream()
            .filter(x -> WinNumber.winNumbers.contains(x))
            .count();
    }

    private static void checkCountOverThree(AutoTicket autoTicket, int count) {
        if (count >= LottoResult.THREE.getCorrect()) {
            String correctCount = Integer.toString(count);
            checkCount(autoTicket, count, correctCount);
        }
    }

    private static void checkCount(AutoTicket autoTicket, int count, String correctCount) {
        if (count == LottoResult.FIVE.getCorrect()) {
            isSecondWin(autoTicket, correctCount);
            return;
        }
        LottoResultMap.resultCount.get(correctCount).setCount();
    }

    private static void isSecondWin(AutoTicket autoTicket, String correctCount) {
        if (autoTicket.getAutoTicket().contains(BonusBall.bonusNo)) {
            LottoResultMap.resultCount.get(BONUS_KEY).setCount();
            return;
        }
        LottoResultMap.resultCount.get(correctCount).setCount();
    }

    private static void printCorrectResults() {
        OutputView.printResult();
        for (String key : LottoResultMap.resultCount.keySet()) {
            LottoResult lottoResult = LottoResultMap.resultCount.get(key);
            OutputView.printCorrectResult(lottoResult.getCorrect(), lottoResult.getPrize(),
                lottoResult.getCount());
        }
    }
}
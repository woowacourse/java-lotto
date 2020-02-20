package lotto.controller;

import lotto.model.*;
import lotto.view.OutputView;

public class Lotto {

    public static void lottoGame() {
        Initializer.initialize();
        for (AutoTicket autoTicket : AutoTickets.getAutoTickets()) {
            int count = isInWinNumber(autoTicket);
            checkCountOverThree(autoTicket, count);
        }
        printCorrectResults();
        OutputView.printYield(YieldMoney.countYieldMoney(Prize.sumPrize()));
    }

    public static int isInWinNumber(AutoTicket autoTicket) {
        return (int) autoTicket.getAutoTicket()
            .stream()
            .filter(x -> WinNumber.winNumbers.contains(x))
            .count();
    }

    private static void checkCountOverThree(AutoTicket autoNum, int count) {
        if (count >= LottoResult.THREE.getCorrect()) {
            String correctCount = Integer.toString(count);
            checkCount(autoNum, count, correctCount);
        }
    }

    private static void checkCount(AutoTicket autoTicket,
        int count, String correctCount) {
        if (count == LottoResult.FIVE.getCorrect()) {
            isSecondWin(autoTicket, correctCount);
            return;
        }
        LottoResultMap.resultCount.get(correctCount).setCount();
    }

    private static void isSecondWin(AutoTicket autoTicket,
        String correctCount) {
        if (autoTicket.getAutoTicket().contains(BonusBall.bonusNo)) {
            LottoResultMap.resultCount.get(correctCount).setCount();
            return;
        }
        LottoResultMap.resultCount.get(correctCount).setCount();
    }

    private static void printCorrectResults() {
        OutputView.printResult();
        OutputView.printCorrectResult(LottoResult.THREE.getCorrect(), LottoResult.THREE.getPrize(),
            LottoResult.THREE.getCount());
        OutputView.printCorrectResult(LottoResult.FOUR.getCorrect(), LottoResult.FOUR.getPrize(),
            LottoResult.FOUR.getCount());
        OutputView.printCorrectResult(LottoResult.FIVE.getCorrect(), LottoResult.FIVE.getPrize(),
            LottoResult.FIVE.getCount());
        OutputView.printBonusCorrectResult(LottoResult.FIVE_BONUS.getCorrect(),
            LottoResult.FIVE_BONUS.getPrize(), LottoResult.FIVE_BONUS.getCount());
        OutputView.printCorrectResult(LottoResult.SIX.getCorrect(), LottoResult.SIX.getPrize(),
            LottoResult.SIX.getCount());
    }
}
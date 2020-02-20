package lotto.controller;

import lotto.model.*;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Lotto {
    public static final String THREE = "3";
    public static final String FOUR = "4";
    public static final String FIVE = "5";
    public static final String FIVE_BONUS = "5+";
    public static final String SIX = "6";
    public static final int WINNING_COUNT = 1;

    public static void lottoGame() {

        List<AutoNumber> autoNumbers = new ArrayList<>();
        HashMap<String, Integer> resultCount = new HashMap<>();
        Initializer.initialize(autoNumbers, resultCount);
        for (AutoNumber autoNum : autoNumbers) {
            int count = isInWinNumber(autoNum);
            checkCountOverThree(resultCount, autoNum, count);
        }
        OutputView.printResult();
        printCorrectResults(resultCount);
        OutputView.printYield(YieldMoney.countYieldMoney(Prize.sumPrize(resultCount)));
    }

    public static int isInWinNumber(AutoNumber autoNumbers) {
        return (int) autoNumbers.getAutoNumber()
                .stream()
                .filter(x -> WinNumber.winNumbers.contains(x))
                .count();
    }

    private static void checkCountOverThree(HashMap<String, Integer> resultCount, AutoNumber autoNum, int count) {
        if (count >= LottoResult.THREE.getCorrect()) {
            String correctCount = Integer.toString(count);
            checkCount(resultCount, autoNum, count, correctCount);
        }
    }

    private static void checkCount(HashMap<String, Integer> resultCount, AutoNumber autoNumber, int count, String correctCount) {
        if (count == LottoResult.FIVE.getCorrect()) {
            isSecondWin(resultCount, autoNumber, correctCount);
            return;
        }
        resultCount.put(correctCount, resultCount.get(correctCount) + WINNING_COUNT);
    }

    private static void isSecondWin(HashMap<String, Integer> resultCount, AutoNumber autoNumber, String correctCount) {
        if (autoNumber.getAutoNumber().contains(BonusBall.bonusNo)) {
            resultCount.put(FIVE_BONUS, resultCount.get(FIVE_BONUS) + WINNING_COUNT);
            return;
        }
        resultCount.put(correctCount, resultCount.get(correctCount) + WINNING_COUNT);
    }

    public static void printCorrectResults(HashMap<String, Integer> resultCount) {
        OutputView.printCorrectResult(resultCount.get(THREE), LottoResult.THREE.getCorrect(), LottoResult.THREE.getPrize());
        OutputView.printCorrectResult(resultCount.get(FOUR), LottoResult.FOUR.getCorrect(), LottoResult.FOUR.getPrize());
        OutputView.printCorrectResult(resultCount.get(FIVE), LottoResult.FIVE.getCorrect(), LottoResult.FIVE.getPrize());
        OutputView.printBonusCorrectResult(resultCount.get(FIVE_BONUS));
        OutputView.printCorrectResult(resultCount.get(SIX), LottoResult.SIX.getCorrect(), LottoResult.SIX.getPrize());
    }
}
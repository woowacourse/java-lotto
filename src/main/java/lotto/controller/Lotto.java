package lotto.controller;

import lotto.model.*;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Lotto {
    public static void lottoGame() {
        List<AutoNumber> autoNumbers = new ArrayList<>();
        HashMap<String, Integer> resultCount = new HashMap<>();
        Initializer.initialize(autoNumbers, resultCount);
        for (AutoNumber autoNum : autoNumbers) {
            int count = isInWinNumber(autoNum);
            checkCountOverThree(resultCount, autoNum, count);
        }
        printCorrectResults(resultCount);
        OutputView.printYield(YieldMoney.countYieldMoney(Prize.sumPrize(resultCount)));
    }

    public static int isInWinNumber(AutoNumber autonumbers) {
        int count = 0;
        for (int autoNumber : autonumbers.getAutoNumber()) {
            count += checkInWinNumber(autoNumber);
        }
        return count;
    }

    private static int checkInWinNumber(int autoNumber) {
        if (WinNumber.winNumbers.contains(autoNumber)) {
            return 1;
        }
        return 0;
    }

    private static void checkCountOverThree(HashMap<String, Integer> resultCount, AutoNumber autoNum, int count) {
        if (count >= 3) {
            String correctCount = Integer.toString(count);
            checkCount(resultCount, autoNum, count, correctCount);
        }
    }

    private static void checkCount(HashMap<String, Integer> resultCount, AutoNumber autoNumber, int count, String correctCount) {
        if (count == 5) {
            isSecondWin(resultCount, autoNumber, correctCount);
            return;
        }
        resultCount.put(correctCount, resultCount.get(correctCount) + 1);
    }

    private static void isSecondWin(HashMap<String, Integer> resultCount, AutoNumber autoNumber, String correctCount) {
        if (autoNumber.getAutoNumber().contains(BonusBall.bonusNo)) {
            resultCount.put("5+", resultCount.get("5+") + 1);
            return;
        }
        resultCount.put(correctCount, resultCount.get(correctCount) + 1);
    }

    private static void printCorrectResults(HashMap<String, Integer> resultCount) {
        OutputView.printCorrectResult(resultCount.get("3"), LottoResult.THREE.getCorrect(), LottoResult.THREE.getPrize());
        OutputView.printCorrectResult(resultCount.get("4"), LottoResult.FOUR.getCorrect(), LottoResult.FOUR.getPrize());
        OutputView.printCorrectResult(resultCount.get("5"), LottoResult.FIVE.getCorrect(), LottoResult.FIVE.getPrize());
        OutputView.printBonusCorrectResult(resultCount.get("5+"));
        OutputView.printCorrectResult(resultCount.get("6"), LottoResult.SIX.getCorrect(),LottoResult.SIX.getPrize());
    }
}